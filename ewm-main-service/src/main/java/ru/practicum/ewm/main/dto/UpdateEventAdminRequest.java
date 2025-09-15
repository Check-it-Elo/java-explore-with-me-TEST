package ru.practicum.ewm.main.dto;

import lombok.*;

@Getter
@Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class UpdateEventAdminRequest {
    private String annotation;
    private Long category;
    private String description;
    private String eventDate;
    private LocationDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    /** "PUBLISH_EVENT" | "REJECT_EVENT" */
    private String stateAction;
    private String title;
}