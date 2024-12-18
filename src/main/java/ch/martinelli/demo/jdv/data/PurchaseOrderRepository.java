package ch.martinelli.demo.jdv.data;

import com.oracle.spring.json.jsonb.JSONB;
import com.oracle.spring.json.jsonb.JSONBRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PurchaseOrderRepository {

    private final JdbcClient jdbcClient;
    private final JSONBRowMapper<PurchaseOrder> rowMapper;

    public PurchaseOrderRepository(JdbcClient jdbcClient, JSONB jsonb) {
        this.jdbcClient = jdbcClient;
        rowMapper = new JSONBRowMapper<>(jsonb, PurchaseOrder.class);
    }

    @Transactional(readOnly = true)
    public List<PurchaseOrder> findAll() {
        return jdbcClient.sql("select data from purchase_order_view")
                .query(rowMapper)
                .list();
    }

    public void save(PurchaseOrder purchaseOrder) {

    }
}
