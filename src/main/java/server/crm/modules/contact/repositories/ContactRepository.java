package server.crm.modules.contact.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.crm.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findAllByStatusIsTrue(Pageable pageresquest);

    Page<Contact> findByContactNameContainingAndStatusIsTrueIgnoreCase(Pageable pageresquest, String name);

    Contact findByStudents(Long studentId);
}
