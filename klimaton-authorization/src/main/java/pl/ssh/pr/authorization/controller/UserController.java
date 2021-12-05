package pl.ssh.pr.authorization.controller;

import pl.ssh.pr.common.component.rest.ResponseFactory;
import pl.ssh.pr.common.model.shared.PageModel;
import pl.ssh.pr.common.model.user.ApplicationUser;
import pl.ssh.pr.common.model.user.request.EditApplicationUserDto;
import pl.ssh.pr.common.model.user.request.RegisterApplicationUserRequestDto;
import pl.ssh.pr.common.model.user.response.ApplicationUserResponseDto;
import pl.ssh.pr.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class handling CRUD requests associated with user entity
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final ResponseFactory responseFactory;

    /**
     * Register new user to the system
     *
     * @param dto
     * @return applicationUserResponseDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ResponseEntity<ApplicationUserResponseDto>> registerUser(@RequestBody RegisterApplicationUserRequestDto dto) {
        log.debug("Registering new user with name " + dto.getName() + ".");
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, userService.registerUser(dto));
    }

    /**
     * Find all users registered to the system
     *
     * @return users
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ApplicationUser> findAllUsers() {
        log.debug("Finding all users.");
        return userService.findAllUsers();
    }

    /**
     * Find user which has ID provided in a request path
     *
     * @param id
     * @return user
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<ApplicationUser>> findUserByID(@PathVariable(name = "id") String id) {
        log.debug("Finding user with ID: " + id + ".");
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, userService.findUserByID(id));
    }

    /**
     * Find page number {page} of size {perPage} containing users registered to the system
     *
     * @param page
     * @param perPage
     * @return pageOfUsers
     */
    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<PageModel<ApplicationUser>>> findPageOfUsers(@RequestParam("page") Integer page, @RequestParam("perPage") Integer perPage) {
        log.debug("Finding page of users. Page: " + page + " . Per page: " + perPage + ".");
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, userService.findPageOfUsers(page, perPage));
    }

    /**
     * Edit user with provided ID and new properties specified in dto
     *
     * @param id
     * @param dto
     * @return applicationUserResponseDto
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ResponseEntity<ApplicationUserResponseDto>> editUser(@PathVariable(name = "id") String id, @RequestBody EditApplicationUserDto dto) {
        log.debug("Editing user with ID: " + id + ".");
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, userService.editUser(id, dto));
    }

    /**
     * Delete user from the system and, after all from the database
     *
     * @param id
     * @return void
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(name = "id") String id) {
        log.debug("Deleting user with ID: " + id + ".");
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, userService.deleteUser(id));
    }
}
