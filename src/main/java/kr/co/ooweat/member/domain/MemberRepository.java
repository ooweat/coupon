package kr.co.ooweat.member.domain;

import kr.co.ooweat.exception.member.MemberNotFoundException;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {

    Member save(Member member);

    void saveAll(Iterable<Member> members);

    Optional<Member> findById(Long seq);

    Optional<Member> findByUserId(String userId, String userPass);

    default Member getById(final Long id) {
        return findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    default Member getByUserId(final String userId, final String userPass) {
        return findByUserId(userId, userPass)
                .orElseThrow(() -> new MemberNotFoundException(userId, userPass));
    }
}
