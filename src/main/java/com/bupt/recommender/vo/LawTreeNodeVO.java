package com.bupt.recommender.vo;

import lombok.Data;

import java.util.List;

@Data
public class LawTreeNodeVO {
    String title;
    String key;
    List<LawTreeNodeVO> children;
}
