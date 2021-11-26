package proxet.tournament.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proxet.tournament.dto.PlayerDto;
import proxet.tournament.entity.Player;
import proxet.tournament.exception.BadRequestException;
import proxet.tournament.mapper.PlayerMapper;
import proxet.tournament.repository.PlayerRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

  private final PlayerMapper playerMapper;
  private final PlayerRepository playerRepository;

  public PlayerDto createLobby(PlayerDto playerDto) {
    return Optional.ofNullable(playerDto)
        .map(playerMapper::toEntity)
        .map(playerRepository::save)
        .map(playerMapper::toDto)
        .orElseThrow(() -> new BadRequestException("Request body must not be empty"));
  }

  public void deleteLobby(Map<Integer, List<Player>> players) {
    var playersBatch = players.values().stream().flatMap(List::stream).collect(toList());
    playerRepository.deleteAllInBatch(playersBatch);
  }
}
