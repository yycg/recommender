package com.bupt.recommender.dto;

import com.bupt.recommender.entity.LawCasePunishedPO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class LawCaseDTO {
    // fields from LawCasePO
    private String id;
    private String compNo;
    private String compId;
    private String compName;
    private String busPcode;
    private String busCcode;
    private String busAcode;
    private String busTcode;
    private String ocode;
    private String economicCode;
    private String principal;
    private String cardType;
    private String idcard;
    private String disId;
    private Short reportNum;
    private String specialtyPrimary;
    private String specialtySecondary;
    private String spePri;
    private String disciplineSource;
    private String isRegular;
    private String regularId;
    private String punishimentprocedure;
    private String hearing;
    private String recordDate;
    private String decisionDate;
    private String documentNo;
    private String licRegion;
    private String licUalscxax;
    private String licSpe;
    private String licYear;
    private String licNum;
    private String allowOrg;
    private String allowSupOrg;
    private String aReview;
    private String aReviewDate;
    private String aLitigation;
    private String aLitigationDate;
    private String unpunish;
    private String fixedPlace;
    private Integer punishedTimes;
    private Integer controlTimes;
    private String performStatus;
    private String performResult;
    private BigDecimal actualPenalty;
    private String closedDate;
    private Short noLicense;
    private String dataType;
    private Short isAlter;
    private String rOrgcode;
    private String rOrgname;
    private String rHead;
    private String rDeptname;
    private String rDeptcode;
    private String rName;
    private String rId;
    private String rDate;
    private String createTime;
    private String updateTime;
    private Short isDelete;
    private String deleteTime;
    private String auditUserid;
    private String auditUsername;
    private String auditTime;
    private String lastUserid;
    private String lastUsername;
    private Short tranState;
    private Short transfer;
    private String creditCode;
    private Short addPun;
    private BigDecimal addPunPenalty;
    private String singleNo;
    private String isDetection;
    private String detectionId;
    private String regAddr;
    private String busAddr;
    private String disName;
    private String punishimentbasis;

    // fields from LawCaseIllegalPO
    private String standardId;
    private String standardCode;
    private Short stOrIll;
    private String illId;
    private String factDesc;
    private String factValue;
    private String opinion;
    private String opinionValue;

    // LawCasePunishedPO
    private List<LawCasePunishedPO> lawCasePunishedPOs;
}
