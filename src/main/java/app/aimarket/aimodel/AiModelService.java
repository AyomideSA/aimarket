package app.aimarket.aimodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 08/02/2023, Wednesday
 **/

@Service
public class AiModelService {

  private final AiModelRepository aiModelRepository;

  @Autowired
  public AiModelService(AiModelRepository aiModelRepository) {
    this.aiModelRepository = aiModelRepository;
  }

  public List<AiModel> getAvailableModels() {
    return aiModelRepository.getAvailableModels();
  }

  public AiModel findByName(String name) {
    return aiModelRepository.findByName(name);
  }

}
