package com.bupt.recommender.service.impl;

import com.bupt.recommender.entity.LawPO;
import com.bupt.recommender.entity.LawSpecialtyPO;
import com.bupt.recommender.entity.LawTypePO;
import com.bupt.recommender.mapper.LawMapper;
import com.bupt.recommender.service.LawService;
import com.bupt.recommender.utils.LawConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LawServiceImpl implements LawService {
    @Autowired
    private LawMapper lawMapper;

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
}
