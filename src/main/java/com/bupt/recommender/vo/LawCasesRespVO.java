package com.bupt.recommender.vo;

import com.bupt.recommender.dto.LawCaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class LawCasesRespVO {
    private List<LawCaseDTO> lawCaseDTOs;
}
