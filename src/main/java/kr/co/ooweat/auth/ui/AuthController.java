package kr.co.ooweat.auth.ui;

import javax.servlet.http.HttpServletRequest;
import kr.co.ooweat.auth.application.AuthService;
import kr.co.ooweat.auth.support.AuthorizationExtractor;
import kr.co.ooweat.auth.ui.dto.LoginResponse;
import javax.validation.constraints.NotEmpty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * author: ooweat
 * DESC:
 * 1. 사용자 로그인
 * 2. 사용자 확인(계쩡 여부)
 * 3. 정상: Access Token 발급, 오류: Error
 * 4. 응답(정상: Access Token)
 * 5. 데이터요청 JWT
 * 6. Access Token 검증
 * 7. 응답(요청 데이터)
 * */

@RestController
@RequestMapping("/api")
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/certification")
    public void verifyToken(final HttpServletRequest request) {
        String token = AuthorizationExtractor.extract(request);
        authService.verifyToken(token);
    }
    
    @GetMapping("/login")
    public LoginResponse login(@RequestParam @NotEmpty final String userId, final String userPass) {
        return authService.login(userId, userPass);
    }
    
}
