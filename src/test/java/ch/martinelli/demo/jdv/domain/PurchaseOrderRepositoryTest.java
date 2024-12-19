package ch.martinelli.demo.jdv.domain;

import ch.martinelli.demo.jdv.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.UncategorizedSQLException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PurchaseOrderRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(PurchaseOrderRepositoryTest.class);

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Test
    void findUpdateCreate() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();

        assertThat(purchaseOrders).hasSize(2);

        log.info(purchaseOrders.toString());

        PurchaseOrder purchaseOrder = purchaseOrders.getFirst();
        purchaseOrder.getItems().getFirst().setQuantity(3);

        purchaseOrderRepository.update(purchaseOrder, purchaseOrder.get_id());

        PurchaseOrder newPurchaseOrder = new PurchaseOrder();
        newPurchaseOrder.set_id(UUID.randomUUID().toString());
        newPurchaseOrder.setOrderDate(LocalDateTime.now());
        newPurchaseOrder.setCustomer(purchaseOrder.getCustomer());

        purchaseOrderRepository.insert(newPurchaseOrder);

        purchaseOrders = purchaseOrderRepository.findAll();

        assertThat(purchaseOrders).hasSize(3);

        log.info(purchaseOrders.toString());
    }

    @Test
    void updateReadOnlyField() {
        Optional<PurchaseOrder> optionalPurchaseOrder = purchaseOrderRepository.findById("550e8400-e29b-41d4-a716-446655440006");
        assertThat(optionalPurchaseOrder).isPresent();

        PurchaseOrder purchaseOrder = optionalPurchaseOrder.get();
        purchaseOrder.setOrderDate(LocalDateTime.now());

        try {
            purchaseOrderRepository.update(purchaseOrder, purchaseOrder.get_id());
        } catch (UncategorizedSQLException e) {
            assertThat(e.getSQLException().getMessage()).startsWith("ORA-40940: Cannot update field 'orderDate' corresponding to column 'ORDER_DATE' of table 'PURCHASE_ORDER' in JSON Relational Duality View 'PURCHASE_ORDER_VIEW': Missing UPDATE annotation or NOUPDATE annotation specified.");
        }
    }
}