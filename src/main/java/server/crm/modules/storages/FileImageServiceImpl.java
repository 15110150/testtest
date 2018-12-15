package server.crm.modules.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import server.crm.core.model.ImageEditorResponse;
import server.crm.entities.Image;
import server.crm.exceptions.ApiRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileImageServiceImpl implements FileImageService {
    private static final String BASEDIR = "/upload-dir/";
    private static final String BASEURI = "/files/";
    private final Path rootLocation;
    @Autowired
    private ImageRepository fileImageRepository;

    public FileImageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new ApiRuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public Image store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new ApiRuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new ApiRuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            Image fileImage = new Image();
            fileImage.setFileName(filename);
            fileImage.setFilePath(BASEDIR + filename);
            fileImage.setImgPath(BASEURI + filename);

            return fileImageRepository.save(fileImage);
        } catch (IOException e) {
            throw new ApiRuntimeException("Failed to store file " + filename, e);
        }
    }

    @Override
    public ImageEditorResponse uploadFileFromEditor(MultipartFile file) {
        return new ImageEditorResponse("http://localhost:8080" + this.store(file).getImgPath());
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ApiRuntimeException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new ApiRuntimeException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
