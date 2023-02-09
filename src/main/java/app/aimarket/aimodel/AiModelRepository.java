package app.aimarket.aimodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 08/02/2023, Wednesday
 **/
public interface AiModelRepository
    extends JpaRepository<AiModel, Long> {
  @Query("select m from AiModel m where m.availability = TRUE")
  List<AiModel> getAvailableModels();
}
