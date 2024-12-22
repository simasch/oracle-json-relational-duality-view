package ch.martinelli.demo.jdv.common;

import com.oracle.spring.json.jsonb.JSONB;
import com.oracle.spring.json.jsonb.JSONBRowMapper;
import jakarta.annotation.PostConstruct;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Base repository class for CRUD operations.
 *
 * @param <T>  The entity type.
 * @param <ID> The ID type of the entity.
 */
@Transactional(readOnly = true)
public class BaseRepository<T, ID> {

    private final Class<T> clazz;
    protected final String viewName;
    @Autowired
    protected DataSource dataSource;
    @Autowired
    protected JdbcClient jdbcClient;
    @Autowired
    protected JSONB jsonb;
    protected JSONBRowMapper<T> rowMapper;

    public BaseRepository(Class<T> clazz, String viewName) {
        this.clazz = clazz;
        this.viewName = viewName;
    }

    /**
     * Initialize the row mapper after the bean is created.
     */
    @PostConstruct
    public void init() {
        rowMapper = new JSONBRowMapper<>(jsonb, clazz);
    }

    /**
     * Find all entities.
     *
     * @return A list of entities.
     */
    public List<T> findAll(int offset, int limit) {
        return jdbcClient.sql("""
                        SELECT v.data FROM %s v OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                        """.formatted(viewName))
                .param(1, offset)
                .param(2, limit)
                .query(rowMapper)
                .list();
    }

    /**
     * Find an entity by its ID.
     *
     * @param id The ID of the entity.
     * @return An optional containing the entity if found.
     */
    public Optional<T> findById(ID id) {
        return jdbcClient.sql("""
                        SELECT v.data FROM %s v WHERE v.data."_id" = ?
                        """.formatted(viewName))
                .param(1, id)
                .query(rowMapper)
                .optional();
    }

    /**
     * Insert a new entity.
     *
     * @param object The entity to insert.
     * @return The ID of the inserted entity.
     */
    @Transactional
    public ID insert(T object) {
        try {
            byte[] oson = jsonb.toOSON(object);

            try (CallableStatement cs = dataSource.getConnection().prepareCall("""
                    BEGIN
                        INSERT INTO %s v (data) VALUES (?) RETURNING v.data."_id" INTO ?;
                    END;
                    """.formatted(viewName))) {
                cs.setObject(1, oson);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.execute();

                // Handle different ID types
                Object result = cs.getObject(2);
                if (result == null) {
                    return null;
                }

                @SuppressWarnings("unchecked")
                ID id = (ID) convertToIdType(result);
                return id;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert the ID to the correct type.
     *
     * @param value The ID value.
     * @return The ID value converted to the correct type.
     */
    private Object convertToIdType(Object value) {
        if (value instanceof BigDecimal bigDecimal) {
            // Common ID types conversion
            if (bigDecimal.scale() == 0) {
                if (bigDecimal.longValue() == bigDecimal.doubleValue()) {
                    return bigDecimal.longValue();
                }
                return bigDecimal.intValue();
            }
        }
        return value;
    }

    /**
     * Update an entity.
     *
     * @param object The entity to update.
     * @param id     The ID of the entity.
     */
    @Transactional
    public void update(T object, ID id) {
        byte[] oson = jsonb.toOSON(object);
        jdbcClient.sql("""
                        UPDATE %s v
                        SET v.data = ?
                        WHERE v.data."_id" = ?""".formatted(viewName))
                .param(1, oson)
                .param(2, id)
                .update();
    }

    @Transactional
    public void deleteById(ID id) {
        jdbcClient.sql("""
                        DELETE FROM %s v
                        WHERE v.data."_id" = ?""".formatted(viewName))
                .param(1, id)
                .update();
    }
}
