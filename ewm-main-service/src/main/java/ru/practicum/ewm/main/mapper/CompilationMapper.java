package ru.practicum.ewm.main.mapper;

import org.mapstruct.*;
import ru.practicum.ewm.main.dto.CompilationDto;
import ru.practicum.ewm.main.dto.EventShortDto;
import ru.practicum.ewm.main.dto.NewCompilationDto;
import ru.practicum.ewm.main.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main.mapper.config.CentralMapperConfig;
import ru.practicum.ewm.main.model.Compilation;
import ru.practicum.ewm.main.model.Event;

import java.util.Set;

@Mapper(config = CentralMapperConfig.class, uses = {EventMapper.class})
public interface CompilationMapper {

    // Для отдачи наружу нам нужны ShortDto событий — их будет собирать сервис.
    // Здесь просто принимаем уже собранный список.
    @Mapping(target = "events", source = "eventsShort")
    CompilationDto toDto(Compilation compilation, java.util.List<EventShortDto> eventsShort);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", source = "events")
    Compilation fromNew(NewCompilationDto dto, Set<Event> events);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "events", source = "events")
        // полная замена, если передали
    void update(UpdateCompilationRequest dto, @MappingTarget Compilation comp, Set<Event> events);
}