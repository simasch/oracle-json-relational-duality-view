package ch.martinelli.demo.jdv.domain;

import ch.martinelli.demo.jdv.common.BaseRepository;
import com.oracle.spring.json.jsonb.JSONB;
import com.oracle.spring.json.jsonb.JSONBRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseOrderRepository extends BaseRepository<PurchaseOrder, String> {

    public PurchaseOrderRepository(JdbcClient jdbcClient, JSONB jsonb) {
        super(jdbcClient, jsonb, new JSONBRowMapper<>(jsonb, PurchaseOrder.class), "purchase_order_view");
    }

}
