package server.crm.modules.invoice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.entities.Invoice;
import server.crm.modules.invoice.repositories.InvoiceRepository;
import server.crm.modules.invoice.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Page<Invoice> findAllInvoicePaginated(int page, int size, Sort sortBy) {
        return invoiceRepository.findAllByStatusIsTrue(PageRequest.of(page, size, sortBy));
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        invoice.setStatus(false);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Page<Invoice> findAllInvoiceByKeyword(String keyword, int page, int size, Sort sortBy) {
        return invoiceRepository.findAllByContentContainingAndStatusIsTrueIgnoreCase(PageRequest.of(page, size, sortBy), keyword);
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        return invoiceRepository.findById(id).get();
    }
}
