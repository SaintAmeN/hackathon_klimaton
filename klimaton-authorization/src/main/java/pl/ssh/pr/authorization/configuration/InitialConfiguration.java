package pl.ssh.pr.authorization.configuration;

import pl.ssh.pr.common.model.user.ApplicationUser;
import pl.ssh.pr.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Scan for all components needed for application to run
 * Init database with entities which must always be present
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitialConfiguration {
    private final UserService userService;

    /**
     * Users which must always be present in database.
     */
    private final List<ApplicationUser> initialUsers = Arrays.asList(
            new ApplicationUser(null, "admin", "admin", Arrays.asList("ROLE_ADMIN", "ROLE_USER"), null),
            new ApplicationUser(null, "user", "user", Collections.singletonList("ROLE_USER"), null)
    );

    /**
     * When application context is loaded, insert missing entities into database.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initDatabase() {
        log.debug("Filling database with ADMIN and USER (if necessary).");
        initialUsers.stream().filter(
                initialUser -> Objects.requireNonNull(userService.findAllUsers().collectList().block()).stream().noneMatch(
                        user -> user.getName().equals(initialUser.getName())
                )
        ).forEach(userService::saveUser);
    }
}
