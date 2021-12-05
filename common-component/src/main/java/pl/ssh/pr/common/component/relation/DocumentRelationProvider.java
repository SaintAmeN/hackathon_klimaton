package pl.ssh.pr.common.component.relation;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;
import org.springframework.stereotype.Component;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class that enables us to put multiple entities that inherits from certain base blass to the same collection in database
 */
@AllArgsConstructor
public class DocumentRelationProvider<T> extends EvoInflectorLinkRelationProvider {
    private final Class<T> classType;

    @Override
    public LinkRelation getCollectionResourceRelFor(Class<?> type) {
        return super.getCollectionResourceRelFor(classType);
    }

    @Override
    public LinkRelation getItemResourceRelFor(Class<?> type) {
        return super.getItemResourceRelFor(classType);
    }

    @Override
    public boolean supports(LookupContext delimiter) {
        return classType.isAssignableFrom(delimiter.getType());
    }
}
