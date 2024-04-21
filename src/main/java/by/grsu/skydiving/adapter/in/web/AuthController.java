package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.dto.SignInRequest;
import by.grsu.skydiving.adapter.in.web.dto.SignInResponse;
import by.grsu.skydiving.adapter.in.web.mapper.AuthMapper;
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
    private final AuthMapper authMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(@RequestBody
                                 SignInRequest request) {
        return authMapper.toResponse(
                signInUseCase.signIn(authMapper.toQuery(request))
        );
    }
}
