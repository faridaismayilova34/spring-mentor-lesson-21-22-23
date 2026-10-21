package org.example.springles21.entity;

import jakarta.persistence.*;

import lombok.*;
import org.example.springles21.enums.TalkStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "talks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String room;
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TalkStatus status = TalkStatus.SCHEDULED;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "speaker_id", nullable = false)
    private Speaker speaker;

    private LocalDateTime scheduledAt;

}
