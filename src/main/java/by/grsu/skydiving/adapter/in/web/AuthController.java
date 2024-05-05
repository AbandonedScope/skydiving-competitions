package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.dto.SignInRequest;
import by.grsu.skydiving.adapter.in.web.dto.SignInResponse;
import by.grsu.skydiving.adapter.in.web.dto.SignUpRequest;
import by.grsu.skydiving.adapter.in.web.dto.SignUpResponse;
import by.grsu.skydiving.adapter.in.web.mapper.AuthMapper;
import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.port.in.SignUpUserUseCase;
import by.grsu.skydiving.application.port.in.SignInUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@WebAdapter
@RequiredArgsConstructor
public class AuthController {
    private final SignInUseCase signInUseCase;
    private final SignUpUserUseCase signUpUserUseCase;
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
        UserInfo signedUpUser = signUpUserUseCase.signUp(authMapper.toCommand(request));
        return authMapper.toResponse(signedUpUser);
    }
}
