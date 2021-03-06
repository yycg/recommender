package com.bupt.recommender.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LawCasePunishedPO implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.ID
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.DIS_ID
     *
     * @mbggenerated
     */
    private String disId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.TYPE_ID
     *
     * @mbggenerated
     */
    private String typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.TYPE_CODE
     *
     * @mbggenerated
     */
    private String typeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.AMOUNT
     *
     * @mbggenerated
     */
    private BigDecimal amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.BUS_PCODE
     *
     * @mbggenerated
     */
    private String busPcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.CREATE_TIME
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discipline_punished_tf.REMOVE_DATE
     *
     * @mbggenerated
     */
    private String removeDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_discipline_punished_tf
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.ID
     *
     * @return the value of t_discipline_punished_tf.ID
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.ID
     *
     * @param id the value for t_discipline_punished_tf.ID
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.DIS_ID
     *
     * @return the value of t_discipline_punished_tf.DIS_ID
     *
     * @mbggenerated
     */
    public String getDisId() {
        return disId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.DIS_ID
     *
     * @param disId the value for t_discipline_punished_tf.DIS_ID
     *
     * @mbggenerated
     */
    public void setDisId(String disId) {
        this.disId = disId == null ? null : disId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.TYPE_ID
     *
     * @return the value of t_discipline_punished_tf.TYPE_ID
     *
     * @mbggenerated
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.TYPE_ID
     *
     * @param typeId the value for t_discipline_punished_tf.TYPE_ID
     *
     * @mbggenerated
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.TYPE_CODE
     *
     * @return the value of t_discipline_punished_tf.TYPE_CODE
     *
     * @mbggenerated
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.TYPE_CODE
     *
     * @param typeCode the value for t_discipline_punished_tf.TYPE_CODE
     *
     * @mbggenerated
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.AMOUNT
     *
     * @return the value of t_discipline_punished_tf.AMOUNT
     *
     * @mbggenerated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.AMOUNT
     *
     * @param amount the value for t_discipline_punished_tf.AMOUNT
     *
     * @mbggenerated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.BUS_PCODE
     *
     * @return the value of t_discipline_punished_tf.BUS_PCODE
     *
     * @mbggenerated
     */
    public String getBusPcode() {
        return busPcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.BUS_PCODE
     *
     * @param busPcode the value for t_discipline_punished_tf.BUS_PCODE
     *
     * @mbggenerated
     */
    public void setBusPcode(String busPcode) {
        this.busPcode = busPcode == null ? null : busPcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.CREATE_TIME
     *
     * @return the value of t_discipline_punished_tf.CREATE_TIME
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.CREATE_TIME
     *
     * @param createTime the value for t_discipline_punished_tf.CREATE_TIME
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discipline_punished_tf.REMOVE_DATE
     *
     * @return the value of t_discipline_punished_tf.REMOVE_DATE
     *
     * @mbggenerated
     */
    public String getRemoveDate() {
        return removeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discipline_punished_tf.REMOVE_DATE
     *
     * @param removeDate the value for t_discipline_punished_tf.REMOVE_DATE
     *
     * @mbggenerated
     */
    public void setRemoveDate(String removeDate) {
        this.removeDate = removeDate == null ? null : removeDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discipline_punished_tf
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
        sb.append(", disId=").append(disId);
        sb.append(", typeId=").append(typeId);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", amount=").append(amount);
        sb.append(", busPcode=").append(busPcode);
        sb.append(", createTime=").append(createTime);
        sb.append(", removeDate=").append(removeDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
