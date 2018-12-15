package server.crm.modules.contact.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import server.crm.entities.Contact;

public interface ContactService {
    Page<Contact> findAllContactPaginated(int page, int size, Sort sortBy);

    Contact createContact(Contact contact);

    Contact updateContact(Contact contact);

    Contact deleteContact(Long id);

    Page<Contact> findAllContactByKeyword(String keyword, int page, int size, Sort sortBy);

    Contact findContactById(Long id);
}
