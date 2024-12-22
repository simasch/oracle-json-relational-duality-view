package ch.martinelli.demo.jdv.domain;

import ch.martinelli.demo.jdv.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseOrderRepository extends BaseRepository<PurchaseOrder, Long> {

    public PurchaseOrderRepository() {
        super(PurchaseOrder.class, "purchase_order_view");
    }

    public List<PurchaseOrder> findByCustomer(Long customerId) {
        return jdbcClient.sql("""
                        select v.data from %s v where v.data.customer."_id" = ?
                        """.formatted(viewName))
                .param(1, customerId)
                .query(rowMapper)
                .list();
    }
}
