package c07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class MyDocsConfiguration {
  @Bean
  SearchEngine getEngine(DocumentRepository repository) {
    return new RepositorySearchEngine(repository);
  }
}
