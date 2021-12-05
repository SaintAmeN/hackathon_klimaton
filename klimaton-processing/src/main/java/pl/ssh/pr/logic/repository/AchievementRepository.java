package pl.ssh.pr.logic.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pl.ssh.pr.common.model.achievement.Achievement;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Reactive CyView user repository responsible for interaction with database (ApplicationUser entity)
 */
@Repository
public interface AchievementRepository extends ReactiveMongoRepository<Achievement, String> {
}
