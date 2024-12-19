package ch.martinelli.demo.jdv.data;

import ch.martinelli.demo.jdv.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PurchaseOrderRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(PurchaseOrderRepositoryTest.class);

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Test
    void findAll() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();

        assertThat(purchaseOrders).hasSize(1);

        log.info(purchaseOrders.toString());


        PurchaseOrder purchaseOrder = purchaseOrders.getFirst();
        purchaseOrder.setOrderDate(LocalDateTime.now());

        purchaseOrder.getItems().getFirst().setQuantity(3);

        purchaseOrderRepository.save(purchaseOrder);
    }
}