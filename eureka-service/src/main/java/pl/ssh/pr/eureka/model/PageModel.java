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
 * <p>
 * Class representing pageable model for any kind of data
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageModel<T> {
    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElements;
}
