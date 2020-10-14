package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class EventUserPO {
    Integer eventId;
    Integer userId;
    String userType;
}
