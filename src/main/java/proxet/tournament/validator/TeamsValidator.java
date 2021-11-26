package proxet.tournament.validator;

import static java.util.function.Predicate.not;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static proxet.tournament.service.TeamGeneratorService.TEAMS_COUNT;
import static proxet.tournament.service.TeamGeneratorService.VEHICLE_TYPE_COUNT_PER_TEAM;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import proxet.tournament.entity.Player;
import proxet.tournament.exception.BadRequestException;

@Service
public class TeamsValidator {

  private static final Integer PLAYERS_PER_VEHICLE_TYPE = VEHICLE_TYPE_COUNT_PER_TEAM * TEAMS_COUNT;

  public void validate(Map<Integer, List<Player>> players) {
    var isValid =
        isNotEmpty(players)
            && players.size() == VEHICLE_TYPE_COUNT_PER_TEAM
            && players.values().stream()
                .map(List::size)
                .noneMatch(not(PLAYERS_PER_VEHICLE_TYPE::equals));

    if (!isValid) {
      throw new BadRequestException("Teams cannot be generated from such players: " + players);
    }
  }
}
