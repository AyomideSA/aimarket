package app.aimarket.aimodel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 08/02/2023, Wednesday
 **/
@Configuration
public class AiModelConfig {
  @Bean
  CommandLineRunner commandLineRunner(AiModelRepository aiModelRepository) {
    return args -> {
      AiModel auto = new AiModel(
          1L,
          "Auto",
          50.00,
          70.00,
          "A pretty good ai",
          true,
          "/pictures/auto.jpg"
      );
      AiModel cortana = new AiModel(
          2L,
          "cortana",
          51.00,
          71.00,
          "A really good ai",
          false,
          "/pictures/cortana.jpeg"
      );
      aiModelRepository.saveAll(List.of(auto, cortana));
    };
  }
}
