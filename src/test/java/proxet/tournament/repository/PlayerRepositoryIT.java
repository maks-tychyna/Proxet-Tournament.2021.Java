package proxet.tournament.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import proxet.tournament.entity.Player;

@DataJpaTest
@Transactional
class PlayerRepositoryIT {

  @Autowired private PlayerRepository playerRepository;

  @Test
  public void saveAndFindPlayers() {
    var players =
        List.of(
            new Player().setNickname("nickname1").setVehicleType(1),
            new Player().setNickname("nickname2").setVehicleType(2));

    playerRepository.saveAll(players);

    var foundPlayers = playerRepository.findAll();

    assertThat(foundPlayers)
        .hasSize(2)
        .extracting(Player::getNickname, Player::getVehicleType)
        .contains(tuple("nickname1", 1), tuple("nickname2", 2));
  }

  @Test
  @Sql("classpath:db/players.sql")
  void findTop6WaitingPlayersByVehicleType() {
    var foundPlayers = playerRepository.findTop6ByVehicleTypeOrderByCreationTimeAsc(3);

    assertThat(foundPlayers)
        .hasSize(6)
        .extracting(Player::getNickname, Player::getVehicleType)
        .containsExactly(
            tuple("##Abel}@4", 3),
            tuple("##Abel}@6", 3),
            tuple("##Abel}@5", 3),
            tuple("##Abel}@2", 3),
            tuple("##Abel}@", 3),
            tuple("##Abel}@1", 3));
  }
}
