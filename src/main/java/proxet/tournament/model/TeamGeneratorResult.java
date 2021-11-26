package proxet.tournament.model;

import java.util.List;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import proxet.tournament.dto.PlayerDto;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class TeamGeneratorResult {

  @Size(min = 9, max = 9)
  private List<PlayerDto> firstTeam;

  @Size(min = 9, max = 9)
  private List<PlayerDto> secondTeam;
}
