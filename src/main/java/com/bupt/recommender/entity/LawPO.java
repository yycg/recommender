package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class LawPO {
    String id;
    String checkContent;
    String illegalActivities;
    String superviseMessage;
    String accord;
    String disposalAccord;
    String disposalContent;
    String reportIllegalActivities;
    String operateTime;
    String userId;
    Boolean isHistory;
    String typeId;
    String nameId;
    String code;
    Integer sortId;
    String orgCode;
    Byte wordType;
    String supCompType;
    String infcontId;
    String checkCode;
    String countryWordCode;
}
