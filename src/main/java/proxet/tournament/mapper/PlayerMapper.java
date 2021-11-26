package proxet.tournament.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import proxet.tournament.dto.PlayerDto;
import proxet.tournament.entity.Player;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper extends BaseMapper<Player, PlayerDto> {}
