package pl.ssh.pr.authorization.service;

import pl.ssh.pr.authorization.repository.UserRepository;
import pl.ssh.pr.common.component.util.ParserTools;
import pl.ssh.pr.common.component.exception.*;
import pl.ssh.pr.common.model.shared.PageModel;
import pl.ssh.pr.common.model.user.ApplicationUser;
import pl.ssh.pr.common.model.user.UserPlatformSettings;
import pl.ssh.pr.common.model.user.mapper.ApplicationUserMapper;
import pl.ssh.pr.common.model.user.request.EditApplicationUserDto;
import pl.ssh.pr.common.model.user.request.RegisterApplicationUserRequestDto;
import pl.ssh.pr.common.model.user.request.UpdateUserPlatformSettingsRequestDto;
import pl.ssh.pr.common.model.user.response.ApplicationUserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationUserMapper userMapper;
    private final PasswordEncoder encoder;

    /**
     * Register user after validating data from dto
     *
     * @param dto
     * @return newUser
     */
    public Mono<ApplicationUserResponseDto> registerUser(RegisterApplicationUserRequestDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new PasswordNotMatchingException("Password and confirmed password are different!");
        }
        return userRepository
                .findByName(dto.getName()).map(user -> {
                    throw new UserAlreadyExists("User with that name already exists!");
                })
                .defaultIfEmpty(createApplicationUserEntity(dto))
                .flatMap(user -> userRepository.save((ApplicationUser) user))
                .map(userMapper::applicationUserToApplicationUserResponseDto);
    }

    /**
     * Find all users in database
     *
     * @return users
     */
    public Flux<ApplicationUser> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find user in database that has matching ID
     *
     * @param id
     * @return user
     */
    public Mono<ApplicationUser> findUserByID(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserWithProvidedIDDoesNotExist("User with ID: " + id + " does not exist!")));
    }

    /**
     * Select {perPage} users from database with offset = {page * perPage}
     *
     * @param page
     * @param perPage
     * @return pageOfUsers
     */
    public Mono<PageModel<ApplicationUser>> findPageOfUsers(Integer page, Integer perPage) {
        return userRepository
                .findAll()
                .collectList()
                .map(list -> new PageModel<>(
                        list
                                .stream()
                                .skip(page * perPage)
                                .limit(perPage)
                                .collect(Collectors.toList()),
                        page,
                        perPage,
                        (long) list.size()

                ));
    }

    /**
     * Save user into database
     *
     * @param user
     */
    public void saveUser(ApplicationUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user).subscribe(
                result -> log.debug("Successfully saved new user.")
        );
    }

    /**
     * Find user with provided ID in the database, change it's properties and save it back
     *
     * @param id
     * @param dto
     * @return editedUser
     */
    public Mono<ApplicationUserResponseDto> editUser(String id, EditApplicationUserDto dto) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserWithProvidedIDDoesNotExist("User with ID: " + id + " does not exist!")))
                .map(user -> {
                    if (validateUserPassword(dto.getCurrentPassword(), user.getPassword())) {
                        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                            if (!dto.getPassword().equals(dto.getConfirmPassword())) {
                                throw new PasswordNotMatchingException("Password and confirmed password are different!");
                            }
                            user.setName(dto.getName());
                            user.setPassword(encoder.encode(dto.getPassword()));
                        }
                        user.setRoles(dto.getRoles());
                        return user;
                    } else {
                        throw new PasswordNotMatchingException("Provided password is not matching the current one!");
                    }
                }).flatMap(userRepository::save)
                .map(userMapper::applicationUserToApplicationUserResponseDto);
    }

    /**
     * Delete user with provided ID from the database
     *
     * @param id
     * @return void
     */
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    /**
     * Create system representation of a user
     *
     * @param dto
     * @return user
     */
    private ApplicationUser createApplicationUserEntity(RegisterApplicationUserRequestDto dto) {
        ApplicationUser newUser = userMapper.applicationUserRequestDtoToApplicationUser(dto);
        newUser.setPassword(encoder.encode(dto.getPassword()));
        return newUser;
    }

    /**
     * Using encoder check if passwords provided as arguments are equal
     *
     * @param providedPassword
     * @param actualPassword
     * @return ifPasswordsMatching
     */
    private boolean validateUserPassword(String providedPassword, String actualPassword) {
        return encoder.matches(providedPassword, actualPassword);
    }

    /**
     * Find user platform settings in database.
     *
     * @param username - users username
     * @return string - platform settings given as json String
     */
    public Mono<UserPlatformSettings> findPlatformSettingsByUsername(String username) {
        return userRepository.findByName(username)
                .switchIfEmpty(
                        Mono.error(new EntityDoesNotExist("User with username: " + username + " does not exist!")))
                .map(applicationUser -> {
                    if (applicationUser.getPlatformSettings() == null) {
                        throw new EntityDoesNotExist(
                                "Configuration for user with username: " + username + " does not exist!");
                    }
                    return applicationUser.getPlatformSettings();
                });
    }

    /**
     * Find user platform settings in database.
     *
     * @param username - user name
     * @param settings - user settings string to overwrite
     * @return string - platform settings given as json String
     */
    public Mono<UserPlatformSettings> savePlatformSettingsByUsername(String username, UpdateUserPlatformSettingsRequestDto settings) {
        if (!ParserTools.isJSONValid(settings.getSettings())) {
            log.error("Platform settings should be provided as a JSON string.");
            throw new InvalidFieldValueException("Platform settings should be provided as a JSON string.");
        }
        return userRepository.findByName(username)
                .switchIfEmpty(Mono.error(
                        new EntityDoesNotExist("User with username: " + username + " does not exist!")))
                .map(user -> {
                    UserPlatformSettings platformSettings = user.getPlatformSettings();
                    if (platformSettings == null) {
                        platformSettings = new UserPlatformSettings();
                    }
                    platformSettings.setSettings(settings.getSettings());
                    user.setPlatformSettings(platformSettings);
                    return user;
                }).flatMap(userRepository::save)
                .map(ApplicationUser::getPlatformSettings);
    }
}
