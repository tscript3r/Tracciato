package pl.tscript3r.tracciato.user;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import pl.tscript3r.tracciato.infrastructure.response.error.FailureResponse;
import pl.tscript3r.tracciato.user.api.UserDto;

import java.util.UUID;

@RequiredArgsConstructor
class UserRegistration {

    private final UserRepositoryAdapter userRepositoryAdapter;
    private final UserValidator userValidator;
    private final PasswordEncrypt passwordEncoder;

    synchronized Either<FailureResponse, UserDto> register(UserDto userDto) {
        return userValidator.validate(userDto)
                .map(this::createUserEntity)
                .map(userRepositoryAdapter::save)
                .map(UserMapper::map);
    }

    private UserEntity createUserEntity(UserDto userDto) {
        var userEntity = UserMapper.map(userDto);
        userEntity.setPassword(
                passwordEncoder.encryptPassword(userEntity.getPassword())
        );
        userEntity.setUuid(UUID.randomUUID());
        return userEntity;
    }

}