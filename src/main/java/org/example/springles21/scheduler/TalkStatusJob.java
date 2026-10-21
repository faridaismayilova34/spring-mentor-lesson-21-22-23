package org.example.springles21.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springles21.enums.TalkStatus;
import org.example.springles21.entity.Talk;
import org.example.springles21.repository.TalkRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
    @Slf4j
    @RequiredArgsConstructor
    public class TalkStatusJob {

        private final TalkRepository talkRepository;

        @Scheduled(cron = "0 * * * * *", zone = "Asia/Baku")
        @Transactional
        public void finishPastTalks() {
            LocalDateTime now = LocalDateTime.now();
            List<Talk> scheduledTalks = talkRepository.findByStatus(TalkStatus.SCHEDULED);

            int updatedCount = 0;
            for (Talk talk : scheduledTalks) {
                if (talk.getScheduledAt() != null && talk.getScheduledAt().plusMinutes(talk.getDurationMinutes()).isBefore(now)) {
                    talk.setStatus(TalkStatus.FINISHED);
                    updatedCount++;
                }
            }

            if (updatedCount > 0) {
                talkRepository.saveAll(scheduledTalks);
                log.info("{} talks marked as FINISHED", updatedCount);
            } else {
                log.debug("No talks to mark as FINISHED");
            }
        }
    }

