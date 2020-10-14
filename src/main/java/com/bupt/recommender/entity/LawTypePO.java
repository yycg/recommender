package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class LawTypePO {
    String id;
    Integer level;
    String name;
    String parentId;
    Boolean isBottom;
    Boolean isHistory;
    String nameId;
    Integer sortId;
    String orgCode;
    Byte wordType;
}
