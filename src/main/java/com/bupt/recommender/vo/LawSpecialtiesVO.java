package com.bupt.recommender.vo;

import com.bupt.recommender.entity.LawSpecialtyPO;
import lombok.Data;

import java.util.List;

@Data
public class LawSpecialtiesVO {
    private List<LawSpecialtyPO> lawSpecialtyPOs;
}
