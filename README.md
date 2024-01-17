# coupon
쿠폰 서비스 토이 프로젝트

## 중요 포인트
- 쿠폰과 관련된 서비스를 담당
- 모듈별로 언제든 분리가 가능해야 함 -> 완전 격리된 API 로 만들어볼까?
- 초기엔 발권만 담당하는 서비스 개발
- 쿠폰 정보 데이터베이스 테이블 설계
- jar 로 배포할 계획
- 익숙한 MyBatis 말고 JPA로 시도해보자

```
#1차 목표
쿠폰 발권

대상? 프로모션 당첨 대상자
조건: 연락처 또는 이메일 등의 쿠폰을 수신받을 정보

시나리오:
요청 받은 조건에 따라 쿠폰을 전송한다.

전송방식: 알림톡, 이메일, MMS 를 염두에 둔다.

#쿠폰
사용 기간이 정해져 있다.
금액이 있다.
쿠폰의 종류가 여러개 있다. 1회권, 금액권(금액 소멸까지 다회 사용할 수 있는 쿠폰)
```


```SQL
-- 데이터베이스 생성
CREATE DATABASE coupon;

-- 계정 생성
CREATE USER 'ooweat' @'%' IDENTIFIED BY 'ooweat!@#';
CREATE USER 'ooweat' @'localhost' IDENTIFIED BY 'ooweat!@#';

-- 모든 권한 주기
GRANT ALL PRIVILEGES ON coupon.* TO 'ooweat' @'localhost';
GRANT ALL PRIVILEGES ON coupon.* TO 'ooweat' @'%';

FLUSH PRIVILEGES;

```
