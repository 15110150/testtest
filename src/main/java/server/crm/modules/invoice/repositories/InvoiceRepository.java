package server.crm.modules.invoice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.crm.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findAllByStatusIsTrue(Pageable pageresquest);

    Page<Invoice> findAllByContentContainingAndStatusIsTrueIgnoreCase(Pageable pageresquest, String name);
}
