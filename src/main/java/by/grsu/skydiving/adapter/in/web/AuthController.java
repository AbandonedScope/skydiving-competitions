package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.AuthMapper;
import by.grsu.skydiving.adapter.in.web.request.SignInRequest;
import by.grsu.skydiving.adapter.in.web.request.SignUpRequest;
import by.grsu.skydiving.adapter.in.web.response.SignInResponse;
import by.grsu.skydiving.adapter.in.web.response.SignUpResponse;
import by.grsu.skydiving.adapter.in.web.response.UserCredentialsResponse;
import by.grsu.skydiving.adapter.in.web.response.UserInfoResponse;
import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.port.in.GetUserByIdUseCase;
import by.grsu.skydiving.application.port.in.RegenerateUserCredentialsUseCase;
import by.grsu.skydiving.application.port.in.SignInUseCase;
import by.grsu.skydiving.application.port.in.SignUpUserUseCase;
import by.grsu.skydiving.common.WebAdapter;
import by.grsu.skydiving.common.config.security.UserDetailsServiceImpl.UserDetailsWithId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignInUseCase signInUseCase;
    private final SignUpUserUseCase signUpUserUseCase;
    private final RegenerateUserCredentialsUseCase userCredentialsUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final AuthMapper authMapper;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(@RequestBody
                                 SignInRequest request) {
        return authMapper.toResponse(
            signInUseCase.signIn(authMapper.toQuery(request))
        );
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public SignUpResponse singUp(@RequestBody
                                 SignUpRequest request) {
        UserAuthInfo signedUpUser = signUpUserUseCase.signUp(authMapper.toCommand(request));
        return authMapper.toResponse(signedUpUser);
    }

    @PostMapping("/regen/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserCredentialsResponse regen(@PathVariable
                                         long userId) {
        UserCredentials userCredentials = userCredentialsUseCase.regenerate(userId);

        return authMapper.toResponse(userCredentials);
    }

    @GetMapping("/user-info")
    public UserInfoResponse getUserInfo(Authentication authentication) {
        UserDetailsWithId userDetailsWithId = (UserDetailsWithId) authentication.getPrincipal();

        UserInfo userInfo = getUserByIdUseCase.getUserById(userDetailsWithId.getId());
        return authMapper.toResponse(userInfo);
    }
}
