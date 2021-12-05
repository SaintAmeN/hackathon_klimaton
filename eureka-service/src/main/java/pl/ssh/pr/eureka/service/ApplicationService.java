package pl.ssh.pr.eureka.service;

import pl.ssh.pr.eureka.model.ApplicationInfo;
import pl.ssh.pr.eureka.model.InstanceInfo;
import pl.ssh.pr.eureka.model.PageModel;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {
    private final PeerAwareInstanceRegistry registry;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Get application information from PeerAwareInstanceRegistry, map it to desired model, collect and return
     *
     * @return collected information about registered application
     */
    public PageModel<ApplicationInfo> getAllAvailableApplications(Integer page, Integer perPage) {
        List<ApplicationInfo> applicationInfoList = registry
                .getApplications()
                .getRegisteredApplications()
                .stream()
                .map(this::extractApplicationInfo)
                .collect(Collectors.toList());
        return new PageModel<>(
                applicationInfoList.stream().skip(page * perPage).limit(perPage).collect(Collectors.toList()),
                page,
                perPage,
                applicationInfoList.size());

    }

    /**
     * Extract info about certain application (all of it's instances)
     *
     * @param application
     * @return applicationInfo
     */
    private ApplicationInfo extractApplicationInfo(Application application) {
        log.info("Extracting information from " + application.getName() + " application.");
        return new ApplicationInfo(
                application.getName(),
                application
                        .getInstances()
                        .stream()
                        .map(this::extractInstanceInfo)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Extract info about single instance of an application
     *
     * @param instance
     * @return instanceInfo
     */
    private InstanceInfo extractInstanceInfo(com.netflix.appinfo.InstanceInfo instance) {
        log.info("Extracting information from " + instance.getInstanceId() + " instance.");
        return new InstanceInfo(
                instance.getInstanceId(),
                instance.getStatus(),
                formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(instance.getLastUpdatedTimestamp()), ZoneId.systemDefault()))
        );
    }
}
