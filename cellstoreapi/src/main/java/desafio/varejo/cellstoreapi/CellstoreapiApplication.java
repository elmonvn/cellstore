package desafio.varejo.cellstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableMongoRepositories
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class CellstoreapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CellstoreapiApplication.class, args);
    }

}
