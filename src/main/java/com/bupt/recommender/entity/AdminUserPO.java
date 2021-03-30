package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class AdminUserPO {
    private Integer id;
    private String username;
    private String password;
    private String salt;
}
