package proxet.tournament.reader;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import proxet.tournament.generator.dto.Player;

@RequiredArgsConstructor
public class PlayersStatFileReader {

  private final String filePath;

  @SneakyThrows
  public void each(Consumer<Player> playerCallback) {
    try(var fileReader = new FileReader(filePath)) {
      var csvToBean = new CsvToBeanBuilder<Player>(fileReader)
          .withType(Player.class)
          .withSeparator('\t')
          .withIgnoreEmptyLine(true)
          .withIgnoreLeadingWhiteSpace(true)
          .build();

      csvToBean.stream().forEach(playerCallback);
    }
  }
}
