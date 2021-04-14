package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.UserLawPO;
import java.util.List;

public interface UserLawMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_law
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_law
     *
     * @mbggenerated
     */
    int insert(UserLawPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_law
     *
     * @mbggenerated
     */
    UserLawPO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_law
     *
     * @mbggenerated
     */
    List<UserLawPO> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_law
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserLawPO record);

    List<UserLawPO> selectByUserId(Integer user_id);
}