package server.crm.modules.contact.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Contact;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.contact.repositories.ContactRepository;
import server.crm.modules.contact.services.ContactService;

import java.util.Date;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Page<Contact> findAllContactPaginated(int page, int size, Sort sortBy) {
        return contactRepository.findAllByStatusIsTrue(PageRequest.of(page, size, sortBy));
    }

    @Override
    public Contact createContact(Contact contact) {
        try {
            if (contactRepository.findById(contact.getId()).isPresent()) {
                throw new ApiRuntimeException("Contact is already exist");
            }
            contact.setStatus(true);
            contact.setCreatedBy(LoggedUser.get());
            contact.setCreatedDate(new Date());
            return contactRepository.save(contact);
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Unsuccessful");
        }
    }

    @Override
    public Contact updateContact(Contact contact) {
        try {
            Optional<Contact> targetContact = contactRepository.findById(contact.getId());
            if (targetContact.isPresent()) {
                contact.setCreatedDate(targetContact.get().getCreatedDate());
                contact.setCreatedBy(targetContact.get().getCreatedBy());
                contact.setUpdatedBy(LoggedUser.get());
                contact.setUpdatedDate(new Date());
                return contactRepository.save(contact);
            }
            throw new ApiRuntimeException("Can't find class");
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save class");
        }
    }

    @Override
    public Contact deleteContact(Long id) {
        Contact contact = contactRepository.findById(id).get();
        contact.setStatus(false);
        return contactRepository.save(contact);
    }

    @Override
    public Page<Contact> findAllContactByKeyword(String keyword, int page, int size, Sort sortBy) {
        return contactRepository.findByContactNameContainingAndStatusIsTrueIgnoreCase(PageRequest.of(page, size, sortBy), keyword);
    }

    @Override
    public Contact findContactById(Long id) {
        return contactRepository.findById(id).get();
    }
}
