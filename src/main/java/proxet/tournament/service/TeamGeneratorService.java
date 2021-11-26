package proxet.tournament.service;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proxet.tournament.dto.PlayerDto;
import proxet.tournament.entity.Player;
import proxet.tournament.mapper.PlayerMapper;
import proxet.tournament.model.TeamGeneratorResult;
import proxet.tournament.repository.PlayerRepository;
import proxet.tournament.validator.TeamsValidator;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamGeneratorService {

  public static final int TEAM_SIZE = 9;
  public static final int TEAMS_COUNT = 2;
  public static final int VEHICLE_TYPE_COUNT_PER_TEAM = 3;
  public static final int TOTAL_TEAMS_SIZE = TEAM_SIZE * TEAMS_COUNT / VEHICLE_TYPE_COUNT_PER_TEAM;

  private final PlayerMapper playerMapper;
  private final PlayerService playerService;
  private final TeamsValidator teamsValidator;
  private final PlayerRepository playerRepository;

  public TeamGeneratorResult generateTeam() {
    var topWaitingPlayersPerVehicleType = getTopPlayersPerVehicleType();
    teamsValidator.validate(topWaitingPlayersPerVehicleType);

    var result = splitIntoTeams(topWaitingPlayersPerVehicleType);
    playerService.deleteLobby(topWaitingPlayersPerVehicleType);

    return result;
  }

  private Map<Integer, List<Player>> getTopPlayersPerVehicleType() {
    return IntStream.rangeClosed(1, VEHICLE_TYPE_COUNT_PER_TEAM)
        .boxed()
        .collect(
            toMap(
                Function.identity(),
                playerRepository::findTop6ByVehicleTypeOrderByCreationTimeAsc));
  }

  private TeamGeneratorResult splitIntoTeams(
      Map<Integer, List<Player>> topWaitingPlayersPerVehicleType) {
    var firstTeam = new ArrayList<PlayerDto>();
    var secondTeam = new ArrayList<PlayerDto>();

    for (var playersPerVehicleType : topWaitingPlayersPerVehicleType.values()) {
      for (var index = 0; index < TOTAL_TEAMS_SIZE; index += TEAMS_COUNT) {
        firstTeam.add(playerMapper.toDto(playersPerVehicleType.get(index)));
        secondTeam.add(playerMapper.toDto(playersPerVehicleType.get(index + 1)));
      }
    }

    return new TeamGeneratorResult().setFirstTeam(firstTeam).setSecondTeam(secondTeam);
  }
}
