package server.crm.modules.invoice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import server.crm.entities.Invoice;

public interface InvoiceService {
    Page<Invoice> findAllInvoicePaginated(int page, int size, Sort sortBy);

    Invoice createInvoice(Invoice invoice);

    Invoice updateInvoice(Invoice invoice);

    Invoice deleteInvoice(Long id);

    Page<Invoice> findAllInvoiceByKeyword(String keyword, int page, int size, Sort sortBy);

    Invoice findInvoiceById(Long id);
}
