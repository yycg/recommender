package com.bupt.recommender.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LawSpecialtyPO {
    String id;
    String name;
    Byte state;
    String specialtyCode;
    Date createTime;
    String orgCode;
    Byte type;
    Byte isXZXK;
}
