package by.grsu.skydiving.adapter.in.scheduler;

import by.grsu.skydiving.application.port.in.ChangeCompetitionsStatusUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChangeCompetitionStatusScheduler {
    private final ChangeCompetitionsStatusUseCase changeCompetitionsStatusUseCase;

    @Scheduled(cron = "${competition.update-competition-status-cron}")
    private void updateCompetitionStatus() {
        log.info("Start updating competitions statuses.");

        List<Long> updatedCompetitionsIds = changeCompetitionsStatusUseCase.updateCompetitionsStatus();

        log.info("End updating competitions statuses.");
        log.info("Competitions statuses updated: {}.", updatedCompetitionsIds);
    }
}
