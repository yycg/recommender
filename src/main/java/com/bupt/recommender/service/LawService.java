package com.bupt.recommender.service;

import com.bupt.recommender.entity.LawPO;
import com.bupt.recommender.entity.LawSpecialtyPO;
import com.bupt.recommender.entity.LawTypePO;

import java.util.List;

public interface LawService {
    List<LawSpecialtyPO> getLawSpecialties() throws Exception;

    List<LawTypePO> getLawTypes(String specialtyId, String parentId) throws Exception;

    List<LawPO> getLaws(String specialtyId, String typeId) throws Exception;

    List<LawPO> searchLaws(String keyword) throws Exception;

    LawPO getLawById(String id) throws Exception;

}
