package app.aimarket.aimodel;

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
  private Long id;
  @Column private String name;
  @Column private Double untrainedprice;
  @Column private Double trainedprice;
  @Size(max = 1000)
  @Column private String description;
  @Column private boolean availability;
  @Column private String imageurl;

  public AiModel() {
  }

  public AiModel(Long id, String name,
                 Double untrainedprice,
                 Double trainedprice,
                 String description,
                 boolean availability,
                 String imageurl) {
    this.id = id;
    this.name = name;
    this.untrainedprice = untrainedprice;
    this.trainedprice = trainedprice;
    this.description = description;
    this.availability = availability;
    this.imageurl = imageurl;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getUntrainedprice() {
    return untrainedprice;
  }

  public void setUntrainedprice(Double untrainedprice) {
    this.untrainedprice = untrainedprice;
  }

  public Double getTrainedprice() {
    return trainedprice;
  }

  public void setTrainedprice(Double trainedprice) {
    this.trainedprice = trainedprice;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isAvailability() {
    return availability;
  }

  public void setAvailability(boolean availability) {
    this.availability = availability;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }

  @Override
  public String toString() {
    return "AiModel{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", untrainedprice=" + untrainedprice +
        ", trainedprice=" + trainedprice +
        ", description='" + description + '\'' +
        ", availability=" + availability +
        ", imageurl='" + imageurl + '\'' +
        '}';
  }
}
