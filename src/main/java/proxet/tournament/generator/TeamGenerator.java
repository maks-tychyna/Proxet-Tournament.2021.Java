package proxet.tournament.generator;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import org.apache.commons.lang3.tuple.Pair;
import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.TeamGeneratorResult;
import proxet.tournament.reader.PlayersStatFileReader;

public class TeamGenerator {

  private static final int TEAM_SIZE = 9;
  private static final int TEAMS_COUNT = 2;
  private static final int VEHICLE_TYPE_COUNT_PER_TEAM = 3;
  private static final int MAX_QUEUE_SIZE = TEAM_SIZE * TEAMS_COUNT / VEHICLE_TYPE_COUNT_PER_TEAM;

  public TeamGeneratorResult generateTeams(String filePath) {
    var players = getTopPlayersPerVehicleType(filePath);
    var teamPlayers = splitIntoTeams(players);

    return new TeamGeneratorResult(teamPlayers.getLeft(), teamPlayers.getRight());
  }

  private Map<Integer, List<Player>> getTopPlayersPerVehicleType(String filePath) {
    var playersReader = new PlayersStatFileReader(filePath);
    var playersPerVehicleType = new HashMap<Integer, Queue<Player>>();

    playersReader.each(player -> {
      var players = playersPerVehicleType.computeIfAbsent(
          player.getVehicleType(), forEachVehicleType -> new PriorityQueue<>());

      players.add(player);
      if (players.size() > MAX_QUEUE_SIZE) {
        players.poll();
      }
    });

    return playersPerVehicleType.entrySet().stream()
        .collect(
            toMap(
                Entry::getKey,
                entry -> List.copyOf(entry.getValue())));
  }

  private Pair<List<Player>, List<Player>> splitIntoTeams(Map<Integer, List<Player>> players) {
    var firstTeamPlayers = new ArrayList<Player>();
    var secondTeamPlayers = new ArrayList<Player>();

    for (List<Player> playersPerVehicleType : players.values()) {
      for (int index = 0; index <= MAX_QUEUE_SIZE - TEAMS_COUNT; index += TEAMS_COUNT) {
        firstTeamPlayers.add(playersPerVehicleType.get(index));
        secondTeamPlayers.add(playersPerVehicleType.get(index + 1));
      }
    }

    return Pair.of(firstTeamPlayers, secondTeamPlayers);
  }
}
