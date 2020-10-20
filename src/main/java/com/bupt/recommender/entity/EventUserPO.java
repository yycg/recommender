package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class EventUserPO {
    private Integer eventId;
    private Integer userId;
    private String userType;
}
