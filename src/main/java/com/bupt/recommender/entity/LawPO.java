package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class LawPO {
    private String id;
    private String checkContent;
    private String illegalActivities;
    private String superviseMessage;
    private String accord;
    private String disposalAccord;
    private String disposalContent;
    private String reportIllegalActivities;
    private String operateTime;
    private String userId;
    private Boolean isHistory;
    private String typeId;
    private String nameId;
    private String code;
    private Integer sortId;
    private String orgCode;
    private Byte wordType;
    private String supCompType;
    private String infcontId;
    private String checkCode;
    private String countryWordCode;
}
