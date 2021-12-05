package pl.ssh.pr.eureka.model;

import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 *
 * Model representing information about certain application-type
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstanceInfo {
    private String instanceID;
    private InstanceStatus status;
    private String lastUpdateTime;
}
