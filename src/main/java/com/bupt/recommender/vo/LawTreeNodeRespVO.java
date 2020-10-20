package com.bupt.recommender.vo;

import lombok.Data;

import java.util.List;

@Data
public class LawTreeNodeRespVO {
    private String title;
    private String key;
    private List<LawTreeNodeRespVO> children;
}
