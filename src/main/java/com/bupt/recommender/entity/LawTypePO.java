package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class LawTypePO {
    private String id;
    private Integer level;
    private String name;
    private String parentId;
    private Boolean isBottom;
    private Boolean isHistory;
    private String nameId;
    private Integer sortId;
    private String orgCode;
    private Byte wordType;
}
