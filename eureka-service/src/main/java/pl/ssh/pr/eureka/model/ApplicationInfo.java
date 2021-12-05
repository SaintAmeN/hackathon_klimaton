package pl.ssh.pr.eureka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
public class ApplicationInfo {
    private String name;
    private List<InstanceInfo> instances;
}
