package com.bupt.recommender.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserPO {
    private Integer id;
    private String uid;
    private String name;
    private String type;
    private String avatar;
    private String largeAvatar;
    private String alt;
    private Integer locId;
    private String locName;
    private String desc;
    private Date created;
    private String isSuicide;
    private String isBanned;
}
