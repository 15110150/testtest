package server.crm.modules.invoice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import server.crm.entities.Invoice;
import server.crm.modules.invoice.services.InvoiceService;

@RestController
@RequestMapping("api/v1/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(params = {"page", "size", "sortBy"})
    public Page<Invoice> getAllInvoice(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        return invoiceService.findAllInvoicePaginated(page, size, Sort.by(sortBy));
    }

    @GetMapping(value = "/search", params = {"page", "size", "sortBy", "keyword"})
    public Page<Invoice> getAllInvoiceByKeyword(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("keyword") String keyword) {
        return invoiceService.findAllInvoiceByKeyword(keyword, page, size, Sort.by(sortBy));
    }

    @GetMapping(params = {"id"})
    public Invoice getDetailInvoice(
            @RequestParam("id") Long id) {
        return invoiceService.findInvoiceById(id);
    }

    @PostMapping(params = {"lecturer"})
    public Invoice createInvoice(
            @RequestBody Invoice invoice) {
        return invoiceService.createInvoice(invoice);
    }

    @PutMapping
    public Invoice updateInvoice(
            @RequestBody Invoice invoice) {
        return invoiceService.updateInvoice(invoice);
    }

    @DeleteMapping(params = {"id"})
    public Invoice deleteInvoice(
            @RequestParam("id") Long id) {
        return invoiceService.deleteInvoice(id);
    }
}
