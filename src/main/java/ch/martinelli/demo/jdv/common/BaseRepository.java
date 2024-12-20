package ch.martinelli.demo.jdv.common;

import com.oracle.spring.json.jsonb.JSONB;
import com.oracle.spring.json.jsonb.JSONBRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public class BaseRepository<T, ID> {

    protected final JdbcClient jdbcClient;
    protected JSONB jsonb;
    protected final JSONBRowMapper<T> rowMapper;
    protected final String viewName;

    public BaseRepository(JdbcClient jdbcClient, JSONB jsonb, JSONBRowMapper<T> rowMapper, String viewName) {
        this.jdbcClient = jdbcClient;
        this.jsonb = jsonb;
        this.rowMapper = rowMapper;
        this.viewName = viewName;
    }

    public List<T> findAll(int offset, int limit) {
        return jdbcClient.sql("select v.data from %s v offset ? rows fetch next ? rows only".formatted(viewName))
                .param(1, offset)
                .param(2, limit)
                .query(rowMapper)
                .list();
    }

    public Optional<T> findById(ID id) {
        return jdbcClient.sql("""
                        select v.data from %s v where v.data."_id" = ?
                        """.formatted(viewName))
                .param(1, id)
                .query(rowMapper)
                .optional();
    }

    @Transactional
    public void insert(T object) {
        byte[] oson = jsonb.toOSON(object);
        jdbcClient.sql("insert into %s v (data) values (?)".formatted(viewName))
                .param(1, oson)
                .update();
    }

    @Transactional
    public void update(T object, ID id) {
        byte[] oson = jsonb.toOSON(object);
        jdbcClient.sql("""
                        update %s v
                        set v.data = ?
                        where v.data."_id" = ?""".formatted(viewName))
                .param(1, oson)
                .param(2, id)
                .update();
    }
}
