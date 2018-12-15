package server.crm.modules.storages;

import org.springframework.data.jpa.repository.JpaRepository;
import server.crm.entities.Image;

/**
 * @author: khoa1
 * @create: 05/11/2018
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
