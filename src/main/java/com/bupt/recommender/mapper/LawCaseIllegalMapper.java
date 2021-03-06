package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.LawCaseIllegalPO;
import java.util.List;

public interface LawCaseIllegalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discipline_illegal2_tf
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discipline_illegal2_tf
     *
     * @mbggenerated
     */
    int insert(LawCaseIllegalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discipline_illegal2_tf
     *
     * @mbggenerated
     */
    LawCaseIllegalPO selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discipline_illegal2_tf
     *
     * @mbggenerated
     */
    List<LawCaseIllegalPO> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discipline_illegal2_tf
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LawCaseIllegalPO record);

    List<LawCaseIllegalPO> selectByLawId(String lawId);

    List<LawCaseIllegalPO> selectByCaseId(String caseId);
}