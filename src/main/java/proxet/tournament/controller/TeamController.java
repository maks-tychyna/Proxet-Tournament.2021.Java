package proxet.tournament.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proxet.tournament.model.TeamGeneratorResult;
import proxet.tournament.service.TeamGeneratorService;

@Slf4j
@Validated
@Tag(name = "team")
@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamController {

  private final TeamGeneratorService teamGeneratorService;

  @Operation(summary = "Generate new team")
  @PostMapping("/generate")
  @Valid
  public TeamGeneratorResult generateTeam() {
    log.info("Handling request to generate new team");
    return teamGeneratorService.generateTeam();
  }
}
