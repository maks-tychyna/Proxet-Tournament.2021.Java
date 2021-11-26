package proxet.tournament.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import proxet.tournament.dto.PlayerDto;
import proxet.tournament.service.PlayerService;

@WebMvcTest(LobbyController.class)
class LobbyControllerTest {

  @MockBean private PlayerService playerService;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private MockMvc mvc;

  @Test
  void createPlayerLobby() throws Exception {
    var request = new PlayerDto().setNickname("testNickname").setVehicleType(1);
    var response = new PlayerDto().setNickname("testNickname").setVehicleType(1);

    when(playerService.createLobby(request)).thenReturn(response);

    mvc.perform(
            post("/api/v1/lobby")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(response)));
  }
}
