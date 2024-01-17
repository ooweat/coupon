package kr.co.ooweat.model;

import lombok.Data;

@Data
public class User {
    int seq, authSeq, companySeq;
    String id, pass, name, phone, email;
}
