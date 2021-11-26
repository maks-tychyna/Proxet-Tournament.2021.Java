package proxet.tournament.dto;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class PlayerDto implements Serializable {

  @NotBlank private String nickname;

  @Min(1)
  @Max(3)
  private Integer vehicleType;
}
