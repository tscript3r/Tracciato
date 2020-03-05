package pl.tscript3r.tracciato.route.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tscript3r.tracciato.duration.provider.DurationProvider;
import pl.tscript3r.tracciato.route.RouteFacade;
import pl.tscript3r.tracciato.route.schedule.scheduler.RouteSchedulerFacade;

import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
class RouteScheduleSpringConfiguration {

    private final RouteFacade routeFacade;
    private final DurationProvider durationProvider;

    @Bean
    public RouteScheduleFacade getRouteArrangeFacade() {
        var scheduler = new RouteSchedulerFacade(durationProvider, Executors.newFixedThreadPool(10));
        return new RouteScheduleFacade(routeFacade, scheduler);
    }

}