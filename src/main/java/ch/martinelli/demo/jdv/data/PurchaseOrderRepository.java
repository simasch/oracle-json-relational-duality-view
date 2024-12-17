package ch.martinelli.demo.jdv.data;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PurchaseOrderRepository {

    private final JdbcClient jdbcClient;

    public PurchaseOrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Transactional(readOnly = true)
    public List<Object> findAll() {
        return jdbcClient.sql("select data from purchase_order_view").query().singleColumn();
    }
}
