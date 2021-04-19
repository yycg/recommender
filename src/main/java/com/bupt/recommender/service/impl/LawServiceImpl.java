package com.bupt.recommender.service.impl;

import com.alibaba.fastjson.JSON;
import com.bupt.recommender.common.RecommendResult;
import com.bupt.recommender.dto.LawCaseDTO;
import com.bupt.recommender.entity.*;
import com.bupt.recommender.mapper.*;
import com.bupt.recommender.service.LawService;
import com.bupt.recommender.utils.LawConverter;
import com.bupt.recommender.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class LawServiceImpl implements LawService {
    @Autowired
    private LawMapper lawMapper;

    @Autowired
    private LawCaseMapper lawCaseMapper;

    @Autowired
    private LawCasePunishedMapper lawCasePunishedMapper;

    @Autowired
    private LawCaseIllegalMapper lawCaseIllegalMapper;

    @Autowired
    private UserLawMapper userLawMapper;

    @Override
    public List<LawSpecialtyPO> getLawSpecialties() throws Exception {
        return lawMapper.getLawSpecialties();
    }

    @Override
    public List<LawTypePO> getLawTypes(String specialtyId, String parentId) throws Exception {
        return lawMapper.getLawTypes(specialtyId, parentId);
    }

    @Override
    public List<LawPO> getLaws(String specialtyId, String typeId) throws Exception {
        return lawMapper.getLaws(specialtyId, typeId);
    }

    @Override
    public List<LawPO> searchLaws(String keyword, int start, int count) throws Exception {
        List<LawPO> lawPOs = lawMapper.searchLaws(keyword, start, count);
        LawConverter.regularReplace(lawPOs);
        return lawPOs;
    }

    @Override
    public int countLawsByKeyword(String keyword) throws Exception {
        return lawMapper.countLawsByKeyword(keyword);
    }

    @Override
    public LawPO getLawById(String id) throws Exception {
        LawPO lawPO = lawMapper.getLawById(id);
        LawConverter.regularReplace(lawPO);
        return lawPO;
    }

    @Override
    public List<LawCaseDTO> getRelatedCasesOfLaw(String id) throws Exception {
        List<LawCaseIllegalPO> lawCaseIllegalPOs = lawCaseIllegalMapper.selectByLawId(id);
        if (CollectionUtils.isEmpty(lawCaseIllegalPOs)) {
            return Collections.emptyList();
        }

        return lawCaseIllegalPOs.stream().map(lawCaseIllegalPO -> {
            String caseId = lawCaseIllegalPO.getDisId();
            LawCasePO lawCasePO = lawCaseMapper.selectByPrimaryKey(caseId);
            List<LawCasePunishedPO> lawCasePunishedPOs = lawCasePunishedMapper.selectByCaseId(caseId);
            return LawConverter.convertLawCasePO2DTO(lawCasePO, lawCaseIllegalPO, lawCasePunishedPOs);
        }).collect(Collectors.toList());
    }

    @Override
    public List<LawPO> getLawsOfRelatedCases(String id) throws Exception {
        List<LawCaseIllegalPO> lawCaseIllegalPOs = lawCaseIllegalMapper.selectByLawId(id);
        if (CollectionUtils.isEmpty(lawCaseIllegalPOs)) {
            return Collections.emptyList();
        }

        List<LawPO> result = new ArrayList<>();
        for (LawCaseIllegalPO lawCaseIllegalPO : lawCaseIllegalPOs) {
            String caseId = lawCaseIllegalPO.getDisId();
            List<LawCaseIllegalPO> lawCaseIllegalPOsSharingCase = lawCaseIllegalMapper.selectByCaseId(caseId);
            for (LawCaseIllegalPO l : lawCaseIllegalPOsSharingCase) {
                String lawId = l.getStandardId();
                LawPO lawPO = lawMapper.getLawById(lawId);
                if (lawPO != null) {
                    result.add(lawPO);
                }
            }
        }
        List<LawPO> lawPOs = result.stream().
                filter(LawConverter.distinctByKey(LawPO::getId)).collect(Collectors.toList());
        LawConverter.regularReplace(lawPOs);
        return lawPOs;
    }

    @Override
    public List<LawPO> getLawsBySpecialtyId(String specialtyId, int start, int count) throws Exception {
        List<LawPO> lawPOs = lawMapper.getLawsBySpecialtyId(specialtyId, start, count);
        LawConverter.regularReplace(lawPOs);
        return lawPOs;
    }

    @Override
    public int countLawsBySpecialtyId(String specialtyId) throws Exception {
        return lawMapper.countLawsBySpecialtyId(specialtyId);
    }

    @Override
    public List<LawPO> recommendLaws(String[] lawTitles, int start, int count) throws Exception {
        String url = "http://0.0.0.0:5000/HAGE/recommend";

        Map<String, String> params = new HashMap<>();
        params.put("lawTitles", JSON.toJSONString(lawTitles));
        params.put("start", Integer.toString(start));
        params.put("count", Integer.toString(count));

        //发送Post数据并返回数据
        RecommendResult<List<String>> result = RestTemplateUtils.sendPostRequest(url, params);
        List<String> recommendLaws = result.getResult();
        List<LawPO> lawPOs = new ArrayList<>();
        for (String recommendLaw : recommendLaws) {
            lawPOs.add(getLawById(recommendLaw));
        }
        LawConverter.regularReplace(lawPOs);
        return lawPOs;
    }

    @Override
    public List<String> getLawIdsByUserId(Integer userId) throws Exception {
        List<UserLawPO> userLawPOs = userLawMapper.selectByUserId(userId);
        return userLawPOs.stream().map(UserLawPO::getLawId).distinct().collect(Collectors.toList());
    }

    @Override
    public int insertUserLaw(int userId, String lawId) throws Exception {
        UserLawPO record = new UserLawPO();
        record.setUserId(userId);
        record.setLawId(lawId);
        return userLawMapper.insert(record);
    }
}
