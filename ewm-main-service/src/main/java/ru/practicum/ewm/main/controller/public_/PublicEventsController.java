package ru.practicum.ewm.main.controller.public_;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main.dto.EventFullDto;
import ru.practicum.ewm.main.dto.EventShortDto;
import ru.practicum.ewm.main.service.EventService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
public class PublicEventsController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> search(@RequestParam(required = false) String text,
                                      @RequestParam(required = false) List<Long> categories,
                                      @RequestParam(required = false) Boolean paid,
                                      @RequestParam(required = false) String rangeStart, // "yyyy-MM-dd HH:mm:ss"
                                      @RequestParam(required = false) String rangeEnd,   // "yyyy-MM-dd HH:mm:ss"
                                      @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                      @RequestParam(required = false, defaultValue = "EVENT_DATE") String sort,
                                      @RequestParam(defaultValue = "0") @Min(0) int from,
                                      @RequestParam(defaultValue = "10") @Min(1) int size,
                                      HttpServletRequest request) {
        return eventService.searchPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable,
                sort, from, size, request.getRemoteAddr(), request.getRequestURI());
    }

    @GetMapping("/{eventId}")
    public EventFullDto getById(@PathVariable long eventId, HttpServletRequest request) {
        return eventService.getPublicEvent(eventId, request.getRemoteAddr(), request.getRequestURI());
    }
}