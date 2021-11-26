package proxet.tournament.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proxet.tournament.dto.PlayerDto;
import proxet.tournament.model.TeamGeneratorResult;
import proxet.tournament.service.TeamGeneratorService;

@WebMvcTest(TeamController.class)
class TeamControllerTest {

  @MockBean private TeamGeneratorService teamGeneratorService;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private MockMvc mvc;

  @Test
  void generateTeam() throws Exception {
    var firstTeam = generateTestTeam();
    var secondTeam = generateTestTeam();
    var response = new TeamGeneratorResult().setFirstTeam(firstTeam).setSecondTeam(secondTeam);

    when(teamGeneratorService.generateTeam()).thenReturn(response);

    mvc.perform(post("/api/v1/team/generate"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)));
  }

  private List<PlayerDto> generateTestTeam() {
    return List.of(
        new PlayerDto().setNickname("nickname1").setVehicleType(1),
        new PlayerDto().setNickname("nickname2").setVehicleType(2),
        new PlayerDto().setNickname("nickname3").setVehicleType(3),
        new PlayerDto().setNickname("nickname4").setVehicleType(1),
        new PlayerDto().setNickname("nickname5").setVehicleType(2),
        new PlayerDto().setNickname("nickname6").setVehicleType(3),
        new PlayerDto().setNickname("nickname7").setVehicleType(1),
        new PlayerDto().setNickname("nickname8").setVehicleType(2),
        new PlayerDto().setNickname("nickname9").setVehicleType(3));
  }
}
