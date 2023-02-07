package app.aimarket;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 07/02/2023, Tuesday
 **/
@Entity
@Table(name="aimodels")
public class AiModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column private String name;
  @Column private Double untrainedPrice;
  @Column private Double trainedPrice;
  @Size(max = 1000)
  @Column private String description;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getUntrainedPrice() {
    return untrainedPrice;
  }

  public void setUntrainedPrice(Double untrainedPrice) {
    this.untrainedPrice = untrainedPrice;
  }

  public Double getTrainedPrice() {
    return trainedPrice;
  }

  public void setTrainedPrice(Double trainedPrice) {
    this.trainedPrice = trainedPrice;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "AiModel{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", untrainedPrice=" + untrainedPrice +
        ", trainedPrice=" + trainedPrice +
        ", description='" + description + '\'' +
        '}';
  }
}
