package com.bupt.recommender.vo;

import com.bupt.recommender.entity.LawPO;
import lombok.Data;

import java.util.List;

@Data
public class LawsRespVO {
    private List<LawPO> lawPOs;

    private Integer start;

    private Integer count;

    private Integer total;

}
