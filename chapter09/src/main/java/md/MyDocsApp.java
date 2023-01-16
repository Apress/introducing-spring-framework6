package md;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

import java.util.UUID;
import java.util.stream.IntStream;

@EnableJpaRepositories
@EnableJms
@SpringBootApplication
public class MyDocsApp {
  public static void main(String[] args) {
    SpringApplication.run(MyDocsApp.class, args);
  }

  @Bean
  CommandLineRunner send(JmsPublisher publisher) {
    return args -> {
      IntStream.rangeClosed(1, 20).forEach(
        (value) -> {
          var uuid=UUID.randomUUID().toString();
          publisher.send(
            new Document(
              "document " + uuid,
              "/documents/doc" + uuid
            ));
        }
      );
    };
  }

  @Bean
  CommandLineRunner summarize(DocumentRepository dao) {
    return args->{
      Thread.sleep(2000);
      for(var document:dao.findAll()) {
        System.out.println(document);
      }
    };
  }
}
