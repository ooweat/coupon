package kr.co.ooweat.auth.application;

import kr.co.ooweat.auth.support.JwtTokenProvider;
import kr.co.ooweat.auth.ui.dto.LoginResponse;
import kr.co.ooweat.exception.auth.ExpiredTokenException;
import kr.co.ooweat.exception.auth.InvalidTokenException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @DisplayName("로그인 시 토큰이 발급된다.")
    @Test
    void login() {
        // given

        // when
        LoginResponse response = authService.login("twkim326", "1234");

        // then
        assertThat(response.getToken()).isNotEmpty();
    }

    @DisplayName("최초 로그인 시 isFirstLogin true, 두번째 이후부턴 false가 반환되어야 한다")
    @Test
    void firstLogin() {
        // given

        // when
        LoginResponse firstLoginResponse = authService.login("twkim326", "1234");
        LoginResponse secondLoginResponse = authService.login("twkim326", "1234");

        // then
        assertAll(
                () -> assertThat(firstLoginResponse.getToken()).isNotEmpty(),
                () -> assertThat(firstLoginResponse.isFirstLogin()).isTrue(),
                () -> assertThat(secondLoginResponse.getToken()).isNotEmpty(),
                () -> assertThat(secondLoginResponse.isFirstLogin()).isFalse()
        );
    }

    @DisplayName("유효한 토큰을 검증한다.")
    @Test
    void verifyToken() {
        // given
        String token = jwtTokenProvider.createToken("1");

        // when & then
        assertDoesNotThrow(() -> authService.verifyToken(token));
    }

    @DisplayName("유효하지 않은 토큰을 검증한다.")
    @Test
    void verifyInvalidToken() {
        // given
        String token = "invalid token";

        // when & then
        assertThatThrownBy(() -> authService.verifyToken(token))
                .isInstanceOf(InvalidTokenException.class);
    }

    @DisplayName("만료된 토큰을 검증한다.")
    @Test
    void verifyExpiredToken() {
        // given
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(secretKey, 0);
        String token = expiredJwtTokenProvider.createToken("1");

        // when & then
        assertThatThrownBy(() -> authService.verifyToken(token))
                .isInstanceOf(ExpiredTokenException.class);
    }

    @DisplayName("시그니처가 다른 토큰을 검증한다.")
    @Test
    void verifyDifferentSignatureToken() {
        // given
        JwtTokenProvider otherJwtTokenProvider = new JwtTokenProvider("another secret key 123467891236789231", 600000);
        String token = otherJwtTokenProvider.createToken("1");

        // when & then
        assertThatThrownBy(() -> authService.verifyToken(token))
                .isInstanceOf(InvalidTokenException.class);
    }
}
