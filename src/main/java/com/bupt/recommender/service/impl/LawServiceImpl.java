package com.bupt.recommender.service.impl;

import com.bupt.recommender.dto.LawCaseDTO;
import com.bupt.recommender.entity.*;
import com.bupt.recommender.mapper.LawCaseIllegalMapper;
import com.bupt.recommender.mapper.LawCaseMapper;
import com.bupt.recommender.mapper.LawCasePunishedMapper;
import com.bupt.recommender.mapper.LawMapper;
import com.bupt.recommender.service.LawService;
import com.bupt.recommender.utils.LawConverter;
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
    public List<LawPO> searchLaws(String keyword) throws Exception {
        List<LawPO> lawPOs = lawMapper.searchLaws(keyword);
        LawConverter.regularReplace(lawPOs);
        return lawPOs;
    }

    @Override
    public LawPO getLawById(String id) throws Exception {
        return lawMapper.getLawById(id);
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
        Set<String> lawIds = new HashSet<>();
        for (LawCaseIllegalPO lawCaseIllegalPO : lawCaseIllegalPOs) {
            String caseId = lawCaseIllegalPO.getDisId();
            List<LawCaseIllegalPO> lawCaseIllegalPOsSharingCase = lawCaseIllegalMapper.selectByCaseId(caseId);
            for (LawCaseIllegalPO l : lawCaseIllegalPOsSharingCase) {
                String lawId = l.getStandardId();
                LawPO lawPO = lawMapper.getLawById(lawId);
                if (!lawIds.contains(lawPO.getId())) {
                    result.add(lawPO);
                }
                lawIds.add(lawPO.getId());
            }
        }
        return result;
    }
}
