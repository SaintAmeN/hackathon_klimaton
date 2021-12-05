package pl.ssh.pr.logic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ssh.pr.common.component.rest.ResponseFactory;
import pl.ssh.pr.common.model.shared.PageModel;
import pl.ssh.pr.common.model.achievement.request.CreateAchievementDto;
import pl.ssh.pr.common.model.achievement.response.AchievementResponseDto;
import pl.ssh.pr.logic.service.AchievementService;
import reactor.core.publisher.Mono;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class handling CRUD requests associated with wish/present entity
 */
@RestController
@RequestMapping("/processing/achievements")
@RequiredArgsConstructor
@Slf4j
public class AchievementController {
    private final AchievementService achievementService;
    private final ResponseFactory responseFactory;

    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ResponseEntity<AchievementResponseDto>> registerUser(@RequestBody CreateAchievementDto createAchievementDto, ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("Creating new wish with name " + createAchievementDto.getName() + " " + httpServletRequest.getHeaderNames());
        StringBuilder stringBuilder = new StringBuilder();
        while (httpServletRequest.getHeaderNames().hasMoreElements()) {
            String headerName = httpServletRequest.getHeaderNames().nextElement();
            stringBuilder.append(headerName).append("->").append(httpServletRequest.getHeader(headerName));
            stringBuilder.append("\n");
        }
        log.info("Headers: " + stringBuilder);

//        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, wishesService.createWish(createWishDto));
        return null;
    }

//    @GetMapping("")
//    @ResponseStatus(HttpStatus.OK)
//    public Flux<PageModel<WishResponseDto>> findAllUsers() {
//        log.debug("Getting all wishes");
//        return wishesService.findAllUsers();
//    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Mono<ResponseEntity<ApplicationUser>> findUserByID(@PathVariable(name = "id") String id) {
//        log.debug("Finding user with ID: " + id + ".");
//        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, wishesService.findUserByID(id));
//    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<PageModel<AchievementResponseDto>>> findPageOfUsers(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                                   @RequestParam(value = "perPage", required = false, defaultValue = "10") Integer perPage) {
        log.info("Finding page of wishes. Page: " + page + " . Per page: " + perPage + ".");
        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, achievementService.findPageOfAchievements(page, perPage));
    }

//    @PatchMapping("/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public Mono<ResponseEntity<ApplicationUserResponseDto>> editUser(@PathVariable(name = "id") String id, @RequestBody EditApplicationUserDto dto) {
//        log.debug("Editing user with ID: " + id + ".");
//        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, wishesService.editUser(id, dto));
//    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(name = "id") String id) {
//        log.debug("Deleting user with ID: " + id + ".");
//        return responseFactory.createReactiveResponseFromMono(HttpStatus.OK, wishesService.deleteUser(id));
//    }
}
