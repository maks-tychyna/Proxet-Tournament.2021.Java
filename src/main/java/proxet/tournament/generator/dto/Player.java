package proxet.tournament.generator.dto;

import static java.lang.Integer.compare;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Player implements Comparable<Player> {

    @CsvBindByPosition(position = 0)
    private String nickname;

    @CsvBindByPosition(position = 1)
    private int waitTime;

    @CsvBindByPosition(position = 2)
    private int vehicleType;

    @Override
    public int compareTo(Player other) {
        return compare(waitTime, other.waitTime);
    }
}
