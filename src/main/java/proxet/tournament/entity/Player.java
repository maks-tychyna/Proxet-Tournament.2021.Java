package proxet.tournament.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Accessors(chain = true)
@Entity
@Table(
    name = "players",
    indexes = @Index(name = "player_waiting_time_index", columnList = "vehicleType, creationTime"))
public class Player {

  @Id @NotBlank private String nickname;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime creationTime;

  @Min(1)
  @Max(3)
  @Column(nullable = false)
  private Integer vehicleType;
}
