package pl.ssh.pr.authorization.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pl.ssh.pr.common.model.user.ApplicationUser;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<ApplicationUser, String> {
    Mono<ApplicationUser> findByName(String name);
}
