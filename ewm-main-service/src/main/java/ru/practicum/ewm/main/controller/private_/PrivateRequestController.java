package ru.practicum.ewm.main.controller.private_;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main.dto.ParticipationRequestDto;
import ru.practicum.ewm.main.service.RequestService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
@Validated
public class PrivateRequestController {

    private final RequestService requestService;

    // GET /users/{userId}/requests — список своих заявок
    @GetMapping
    public List<ParticipationRequestDto> getUserRequests(@PathVariable long userId) {
        return requestService.getUserRequests(userId);
    }

    // POST /users/{userId}/requests?eventId=... — создать заявку на участие
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addRequest(@PathVariable long userId,
                                              @RequestParam long eventId) {
        return requestService.addRequest(userId, eventId);
    }

    // PATCH /users/{userId}/requests/{requestId}/cancel — отменить свою заявку
    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable long userId,
                                                 @PathVariable long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }
}