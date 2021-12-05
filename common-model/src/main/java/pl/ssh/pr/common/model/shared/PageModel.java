package pl.ssh.pr.common.model.shared;

import lombok.*;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageModel<T> {
    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
}
