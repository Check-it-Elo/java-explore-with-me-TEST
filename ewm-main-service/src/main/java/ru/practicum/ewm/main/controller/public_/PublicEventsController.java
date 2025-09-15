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
    public List<EventShortDto> search(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) String rangeStart,   // "yyyy-MM-dd HH:mm:ss"
            @RequestParam(required = false) String rangeEnd,     // "yyyy-MM-dd HH:mm:ss"
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(defaultValue = "EVENT_DATE") String sort,
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            HttpServletRequest request
    ) {
        if (rangeStart == null || rangeStart.isBlank()) {
            rangeStart = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        if (rangeEnd != null && !rangeEnd.isBlank()) {
            java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            java.time.LocalDateTime start = java.time.LocalDateTime.parse(rangeStart, fmt);
            java.time.LocalDateTime end = java.time.LocalDateTime.parse(rangeEnd, fmt);
            if (end.isBefore(start)) {
                throw new ru.practicum.ewm.main.exception.BadRequestException("rangeEnd must be after or equal to rangeStart");
            }
        }

        String normalizedSort = ("VIEWS".equalsIgnoreCase(sort)) ? "VIEWS" : "EVENT_DATE";

        return eventService.searchPublic(
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable,
                normalizedSort, from, size, request.getRemoteAddr(), request.getRequestURI()
        );
    }

    @GetMapping("/{eventId}")
    public EventFullDto getById(@PathVariable long eventId, HttpServletRequest request) {
        return eventService.getPublicEvent(eventId, request.getRemoteAddr(), request.getRequestURI());
    }
}
