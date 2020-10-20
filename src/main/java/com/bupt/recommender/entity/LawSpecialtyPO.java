package com.bupt.recommender.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LawSpecialtyPO {
    private String id;
    private String name;
    private Byte state;
    private String specialtyCode;
    private Date createTime;
    private String orgCode;
    private Byte type;
    private Byte isXZXK;
}
