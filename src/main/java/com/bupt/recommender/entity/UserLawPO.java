package com.bupt.recommender.entity;

import java.io.Serializable;

public class UserLawPO implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_law.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_law.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_law.law_id
     *
     * @mbggenerated
     */
    private String lawId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_law
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_law.id
     *
     * @return the value of user_law.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_law.id
     *
     * @param id the value for user_law.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_law.user_id
     *
     * @return the value of user_law.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_law.user_id
     *
     * @param userId the value for user_law.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_law.law_id
     *
     * @return the value of user_law.law_id
     *
     * @mbggenerated
     */
    public String getLawId() {
        return lawId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_law.law_id
     *
     * @param lawId the value for user_law.law_id
     *
     * @mbggenerated
     */
    public void setLawId(String lawId) {
        this.lawId = lawId == null ? null : lawId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_law
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", lawId=").append(lawId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}