package server.crm.modules.storages;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import server.crm.core.model.ImageEditorResponse;
import server.crm.entities.Image;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileImageService {
    void init();

    Image store(MultipartFile file);

    ImageEditorResponse uploadFileFromEditor(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
