package app.aimarket.aimodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AiModelRepository
    extends JpaRepository<AiModel, Long> {
  @Query("select m from AiModel m where m.availability = TRUE")
  List<AiModel> getAvailableModels();
  AiModel findByName(String name);
}
