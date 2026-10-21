package org.example.springles21.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalkRequestDto {
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 150, message = "Title must be 5-150 characters")
    private String title;

    @NotBlank(message = "Room is required")
    private String room;

    @NotNull(message = "Duration is required")
    @Min(value = 15, message = "Duration must be at least 15 minutes")
    @Max(value = 90, message = "Duration must be at most 90 minutes")
    private Integer durationMinutes;

    @NotNull(message = "Talk must be scheduled in the future")
    @Future(message = "Talk must be scheduled in the future")
    private LocalDateTime scheduledAt;

    @NotNull(message = "Speaker ID is required")
    @Positive(message = "Speaker id must be a positive number")
    private Long speakerId;


}