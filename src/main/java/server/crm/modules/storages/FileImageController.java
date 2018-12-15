package server.crm.modules.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.crm.core.model.ImageEditorResponse;
import server.crm.entities.Image;

@RestController
public class FileImageController {

    @Autowired
    private FileImageService fileImageService;

    @PostMapping("/upload")
    public Image upload(@RequestParam("file") MultipartFile file) {
        return fileImageService.store(file);
    }

    @PostMapping("/upload-ngx-editor")
    public ImageEditorResponse uploadEditor(@RequestParam("file") MultipartFile file) {
        return fileImageService.uploadFileFromEditor(file);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = fileImageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
