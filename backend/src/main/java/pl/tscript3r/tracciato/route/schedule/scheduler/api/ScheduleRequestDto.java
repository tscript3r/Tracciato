package pl.tscript3r.tracciato.route.schedule.scheduler.api;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ScheduleRequestDto {

    private UUID requestUuid;
    private UUID routeUuid;
    private final LocalDateTime creationTimestamp = LocalDateTime.now();

}
