//package ru.practicum.ewm.main.controller.private_;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import ru.practicum.ewm.main.dto.EventRequestStatusUpdateRequest;
//import ru.practicum.ewm.main.dto.EventRequestStatusUpdateResult;
//import ru.practicum.ewm.main.dto.ParticipationRequestDto;
//import ru.practicum.ewm.main.service.RequestService;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//
//import jakarta.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@Validated
//@ConditionalOnExpression("false")
//public class PrivateRequestsController {
//
//    private final RequestService requestService;
//
//    // Заявки текущего пользователя
//    @GetMapping("/users/{userId}/requests")
//    public List<ParticipationRequestDto> getUserRequests(@PathVariable long userId) {
//        return requestService.getUserRequests(userId);
//    }
//
//    @PostMapping("/users/{userId}/requests")
//    public ParticipationRequestDto addRequest(@PathVariable long userId,
//                                              @RequestParam long eventId) {
//        return requestService.addRequest(userId, eventId);
//    }
//
//    @PatchMapping("/users/{userId}/requests/{requestId}/cancel")
//    public ParticipationRequestDto cancelRequest(@PathVariable long userId,
//                                                 @PathVariable long requestId) {
//        return requestService.cancelRequest(userId, requestId);
//    }
//
//    // Управление заявками на своём событии
//    @GetMapping("/users/{userId}/events/{eventId}/requests")
//    public List<ParticipationRequestDto> getEventRequests(@PathVariable long userId,
//                                                          @PathVariable long eventId) {
//        return requestService.getEventRequests(userId, eventId);
//    }
//
//    @PatchMapping("/users/{userId}/events/{eventId}/requests")
//    public EventRequestStatusUpdateResult updateEventRequests(@PathVariable long userId,
//                                                              @PathVariable long eventId,
//                                                              @RequestBody
//@Valid EventRequestStatusUpdateRequest body) {
//        return requestService.updateEventRequests(userId, eventId, body);
//    }
//}