package ch.martinelli.demo.jdv.domain;

import com.oracle.spring.json.jsonb.JSONB;
import com.oracle.spring.json.jsonb.JSONBRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PurchaseOrderRepository {

    private final JdbcClient jdbcClient;
    private final JSONB jsonb;
    private final JSONBRowMapper<PurchaseOrder> rowMapper;

    public PurchaseOrderRepository(JdbcClient jdbcClient, JSONB jsonb) {
        this.jdbcClient = jdbcClient;
        this.jsonb = jsonb;
        rowMapper = new JSONBRowMapper<>(jsonb, PurchaseOrder.class);
    }

    @Transactional(readOnly = true)
    public List<PurchaseOrder> findAll() {
        return jdbcClient.sql("select v.data from purchase_order_view v")
                .query(rowMapper)
                .list();
    }

    public void save(PurchaseOrder purchaseOrder) {
        byte[] oson = jsonb.toOSON(purchaseOrder);
        jdbcClient.sql("""
                        update purchase_order_view v 
                        set v.data = ?
                        where v.data."_id" = ?""")
                .param(1, oson)
                .param(2, purchaseOrder.get_id())
                .update();
    }
}
