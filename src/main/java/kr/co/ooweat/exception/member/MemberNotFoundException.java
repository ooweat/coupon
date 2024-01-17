package kr.co.ooweat.exception.member;

import kr.co.ooweat.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {

    private static final String ERROR_CODE = "MEMBER_NOT_FOUND";
    private static final String SERVER_MESSAGE = "존재하지 않는 사용자 조회";
    private static final String CLIENT_MESSAGE = "사용자가 존재하지 않습니다.";

    public MemberNotFoundException(final Long id) {
        super(String.format("%s -> member id: %d", SERVER_MESSAGE, id), CLIENT_MESSAGE, ERROR_CODE);
    }

    public MemberNotFoundException(final String userId) {
        super(String.format("%s -> member id: %s", SERVER_MESSAGE, userId), CLIENT_MESSAGE, ERROR_CODE);
    }
}
