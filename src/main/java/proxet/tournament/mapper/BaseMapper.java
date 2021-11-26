package proxet.tournament.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

import java.util.List;
import org.mapstruct.IterableMapping;

public interface BaseMapper<Entity, DTO> {

  DTO toDto(Entity entity);

  Entity toEntity(DTO dto);

  @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
  List<DTO> toDto(List<Entity> entities);

  @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
  List<Entity> toEntity(List<DTO> entities);
}
