package kr.co.ooweat.member.domain;

/*import kr.co.ooweat.exception.member.MemberInvalidThumbnailUrlException;
import kr.co.ooweat.exception.member.MemberInvalidUsernameException;
import kr.co.ooweat.workspace.domain.Workspace;*/
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    //int seq, authSeq, companySeq;
    //String id, pass, name, phone, email;


    @Column(name = "user_id", length = 15, nullable = false, unique = true)
    private String userId;

    @Column(name = "user_pass", length = 20, nullable = false)
    private String userPass;

    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "user_phone", length = 13, nullable = false)
    private String userPhone;

    @Column(name = "user_email", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "first_login", nullable = false)
    private boolean isFirstLogin = true;

    @Column(name = "token", length = 256, unique = true)
    private String token;

    protected Member() {
    }

    public Member(final String userId, final String username) {
        this.userId = userId;
        this.userName = username;
    }

    public void firstLogin(final String token) {
        this.isFirstLogin = false;
        this.token = token;
    }
}
