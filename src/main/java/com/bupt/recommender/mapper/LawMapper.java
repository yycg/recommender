package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.LawPO;
import com.bupt.recommender.entity.LawSpecialtyPO;
import com.bupt.recommender.entity.LawTypePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LawMapper {
    List<LawSpecialtyPO> getLawSpecialties() throws Exception;

    List<LawTypePO> getLawTypes(@Param("specialtyId") String specialtyId, @Param("parentId") String parentId)
            throws Exception;

    List<LawPO> getLaws(@Param("specialtyId") String specialtyId, @Param("typeId") String typeId) throws Exception;

    List<LawPO> searchLaws(@Param("keyword") String keyword, @Param("start") int start,
                           @Param("count") int count) throws Exception;

    int countLawsByKeyword(@Param("keyword") String keyword) throws Exception;

    LawPO getLawById(@Param("id") String id) throws Exception;

    List<LawPO> getLawsBySpecialtyId(@Param("specialtyId") String specialtyId, @Param("start") int start,
                                     @Param("count") int count) throws Exception;

    int countLawsBySpecialtyId(@Param("specialtyId") String specialtyId) throws Exception;
}
