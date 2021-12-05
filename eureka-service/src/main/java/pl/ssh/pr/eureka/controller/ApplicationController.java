package pl.ssh.pr.eureka.controller;

import pl.ssh.pr.eureka.model.ApplicationInfo;
import pl.ssh.pr.eureka.model.PageModel;
import pl.ssh.pr.eureka.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * RestController mapping requests about applications registered to eureka server
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/status")
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;

    /**
     * Provide information about application registered to eureka
     *
     * @return registered applications information
     */
    @GetMapping
    public ResponseEntity<PageModel<ApplicationInfo>> getAllAvailableApplications(@RequestParam(value = "page") Integer page,
                                                                                  @RequestParam(value = "perPage") Integer perPage) {
        log.info("Fetch available application information.");
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getAllAvailableApplications(page, perPage));
    }
}
