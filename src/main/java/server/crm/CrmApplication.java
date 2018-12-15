package server.crm;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.crm.modules.storages.FileImageService;
import server.crm.modules.storages.StorageProperties;
import server.crm.responses.base.BaseResponse;
import server.crm.responses.ServerInfo;
import server.crm.utils.log.LogType;
import server.crm.utils.log.Logger;

import static server.crm.utils.Paths.ROOT;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@RestController
@RequestMapping(value = ROOT)
@EnableConfigurationProperties({StorageProperties.class})
public class CrmApplication implements CommandLineRunner {

    private final Environment env;
    private static String IS_LOCAL = "";
    private final Logger LOGGER;

    @Autowired
    public CrmApplication(Environment env, Logger logger) {
        this.env = env;
        LOGGER = logger;
    }

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileImageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @GetMapping(value = "info")
    public ResponseEntity<Object> getServerInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse()
                        .setResult(
                                new ServerInfo()
                                        .setName(env.getProperty("server.name"))
                                        .setVersion(env.getProperty("server.version"))
                                        .setBuild_no(env.getProperty("server.build"))
                                        .setIp(env.getProperty("server.ip"))
                                        .setPort(env.getProperty("server.port"))
                                        .setDatabase_host(env.getProperty("server.database.host"))
                                        .setDatabase_name(env.getProperty("server.database.name"))
                        )
                );
    }

    public static boolean IS_DEBUG() {
        return IS_LOCAL.equals("local") || IS_LOCAL.equals("");

    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.with(LogType.SERVER).info("server started!");
        try {
            IS_LOCAL = env.getProperty("server.name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
