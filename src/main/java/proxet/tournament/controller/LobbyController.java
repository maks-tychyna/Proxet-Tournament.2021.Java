package proxet.tournament.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import proxet.tournament.dto.PlayerDto;
import proxet.tournament.service.PlayerService;

@Slf4j
@Validated
@Tag(name = "lobby")
@RestController
@RequestMapping("/api/v1/lobby")
@RequiredArgsConstructor
public class LobbyController {

  private final PlayerService playerService;

  @Operation(summary = "Add new player to the lobby")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PlayerDto createLobby(@Valid @RequestBody PlayerDto playerDto) {
    log.info("Handling request to add new player to the lobby: {}", playerDto);
    return playerService.createLobby(playerDto);
  }
}
