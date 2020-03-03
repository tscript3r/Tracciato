package pl.tscript3r.tracciato.route.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tscript3r.tracciato.infrastructure.response.ResponseResolver;
import pl.tscript3r.tracciato.infrastructure.response.error.GlobalFailureResponse;

import java.util.UUID;

import static pl.tscript3r.tracciato.infrastructure.EndpointsMappings.*;
import static pl.tscript3r.tracciato.infrastructure.spring.security.SecurityConstants.TOKEN_HEADER;

@RestController
@RequestMapping(ROUTE_SCHEDULE_MAPPING)
@RequiredArgsConstructor
class RouteScheduleSpringController {

    private final ResponseResolver<ResponseEntity<?>> responseResolver;
    private final RouteScheduleFacade routeScheduleFacade;

    @GetMapping(ROUTE_SCHEDULE_VALIDATION_MAPPING)
    public HttpEntity<?> validate(@RequestHeader(TOKEN_HEADER) String token,
                                  @PathVariable(ROUTE_UUID_VARIABLE) UUID routeUuid) {
        return responseResolver.resolve(routeScheduleFacade.validate(token, routeUuid));
    }

    @PostMapping(value = ROUTE_SCHEDULE_MAPPING)
    public HttpEntity<?> schedule(@RequestHeader(TOKEN_HEADER) String token,
                                  @PathVariable(ROUTE_UUID_VARIABLE) UUID routeUuid) {
        return responseResolver.resolve(GlobalFailureResponse.NOT_IMPLEMENTED_ERROR);
    }

}
