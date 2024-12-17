package ch.martinelli.demo.jdv.data;

import java.time.OffsetDateTime;

public record PurchaseOrder(Long id, OffsetDateTime timestamp) {
}
