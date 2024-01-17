package kr.co.ooweat.auth.application;

import kr.co.ooweat.auth.support.JwtTokenProvider;
import kr.co.ooweat.auth.ui.dto.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
public class AuthService {
    
    private final MemberRepository members;
    private final ExternalClient externalClient;
    private final JwtTokenProvider jwtTokenProvider;
    
    public AuthService(final MemberRepository members, final ExternalClient externalClient,
        final JwtTokenProvider jwtTokenProvider) {
        this.members = members;
        this.externalClient = externalClient;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    //토크노학인
    public void verifyToken(final String token) {
        jwtTokenProvider.validateToken(token);
    }
    
    @Transactional
    public LoginResponse login(final String code) {
        MemberInfoDto memberInfoDto = externalClient.callMemberInfo(code);
        return login(memberInfoDto.getSlackId(), memberInfoDto.getUserToken());
    }
    
    @Transactional
    public LoginResponse login(final OAuthAccessInfoDto oAuthAccessInfoDto) {
        return login(oAuthAccessInfoDto.getUserSlackId(), oAuthAccessInfoDto.getUserToken());
    }
    
    private LoginResponse login(final String memberSlackId, final String memberToken) {
        Member member = members.getBySlackId(memberSlackId);
        
        boolean isFirstLogin = member.isFirstLogin();
        member.firstLogin(memberToken);
        
        return LoginResponse.builder()
            .token(jwtTokenProvider.createToken(String.valueOf(member.getId())))
            .firstLogin(isFirstLogin)
            .build();
    }
}
