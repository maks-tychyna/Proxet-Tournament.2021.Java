package proxet.tournament.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import proxet.tournament.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  List<Player> findTop6ByVehicleTypeOrderByCreationTimeAsc(Integer vehicleType);
}
