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
          false,
          "/pictures/auto.jpg"
      );
      AiModel cortana = new AiModel(
          2L,
          "cortana",
          51.00,
          71.00,
          "A really good ai",
          true,
          "/pictures/cortana.jpeg"
      );
      AiModel irobot = new AiModel(
          3L,
          "irobot",
          22.00,
          33.00,
          "A really good ai model",
          true,
          "/pictures/irobot.png"
      );
      AiModel jarvis = new AiModel(
          4L,
          "jarvis",
          30.00,
          40.00,
          "The Best AI model",
          true,
          "/pictures/jarvis.png"
      );
      AiModel stockai = new AiModel(
          5L,
          "jarvis",
          30.00,
          40.00,
          "The Best AI stock",
          true,
          "/pictures/stockai.jpg"
      );
      AiModel ultron = new AiModel(
          6L,
          "ultron",
          40.00,
          50.00,
          "ULTRON",
          true,
          "/pictures/ultron.jpg"
      );
      aiModelRepository.saveAll(List.of(auto, cortana, irobot, jarvis, stockai, ultron));
    };
  }
}
