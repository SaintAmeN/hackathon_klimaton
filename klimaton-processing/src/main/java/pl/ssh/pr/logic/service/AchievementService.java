package pl.ssh.pr.logic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.ssh.pr.common.model.shared.PageModel;
import pl.ssh.pr.common.model.achievement.mapper.AchievementsMapper;
import pl.ssh.pr.common.model.achievement.response.AchievementResponseDto;
import pl.ssh.pr.logic.repository.AchievementRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Class responsible for performing operations including application users management.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final AchievementsMapper achievementsMapper;


//    Add Wish
//    public Mono<ApplicationUserResponseDto> registerUser(RegisterApplicationUserRequestDto dto) {
//        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
//            throw new PasswordNotMatchingException("Password and confirmed password are different!");
//        }
//        return wishesRepository
//                .findByName(dto.getName()).map(user -> {
//                    throw new UserAlreadyExists("User with that name already exists!");
//                })
//                .defaultIfEmpty(createApplicationUserEntity(dto))
//                .flatMap(user -> wishesRepository.save((ApplicationUser) user))
//                .map(userMapper::applicationUserToApplicationUserResponseDto);
//    }

//    Get All
//    public Flux<ApplicationUser> findAllUsers() {
//        return wishesRepository.findAll();
//    }

//    Find details by id
//    public Mono<ApplicationUser> findUserByID(String id) {
//        return wishesRepository.findById(id)
//                .switchIfEmpty(Mono.error(new UserWithProvidedIDDoesNotExist("User with ID: " + id + " does not exist!")));
//    }

    //    Page of Wishes
    public Mono<PageModel<AchievementResponseDto>> findPageOfAchievements(Integer page, Integer perPage) {
        return achievementRepository
                .findAll(PageRequest.of(page, perPage).getSort())
                .collectList()
                .map(list -> {
                    List<AchievementResponseDto> listContent = list.stream().map(achievementsMapper::achievementToResponseDto).collect(Collectors.toList());
                    return new PageModel<>(listContent, perPage, page, achievementRepository.count().block());
                });
    }

//    Save wish
//    public void saveUser(ApplicationUser user) {
//        user.setPassword(encoder.encode(user.getPassword()));
//        wishesRepository.save(user).subscribe(
//                result -> log.debug("Successfully saved new user.")
//        );
//    }

//    Edit Wish
//    public Mono<ApplicationUserResponseDto> editUser(String id, EditApplicationUserDto dto) {
//        return wishesRepository.findById(id)
//                .switchIfEmpty(Mono.error(new UserWithProvidedIDDoesNotExist("User with ID: " + id + " does not exist!")))
//                .map(user -> {
//                    if (validateUserPassword(dto.getCurrentPassword(), user.getPassword())) {
//                        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
//                            if (!dto.getPassword().equals(dto.getConfirmPassword())) {
//                                throw new PasswordNotMatchingException("Password and confirmed password are different!");
//                            }
//                            user.setName(dto.getName());
//                            user.setPassword(encoder.encode(dto.getPassword()));
//                        }
//                        user.setRoles(dto.getRoles());
//                        return user;
//                    } else {
//                        throw new PasswordNotMatchingException("Provided password is not matching the current one!");
//                    }
//                }).flatMap(wishesRepository::save)
//                .map(userMapper::applicationUserToApplicationUserResponseDto);
//    }

//    Delete wish
//    public Mono<Void> deleteUser(String id) {
//        return wishesRepository.deleteById(id);
//    }

//    Validate content
//    private boolean validateUserPassword(String providedPassword, String actualPassword) {
//        return encoder.matches(providedPassword, actualPassword);
//    }
}
