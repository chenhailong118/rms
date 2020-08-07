package com.still.rms.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table realtion_actor_dictinfo
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class RealtionActorDictinfo implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "演员ID")
    private Integer actorId;

    @ApiModelProperty(value = "字段信息ID")
    private Integer dictinfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table realtion_actor_dictinfo
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_dictinfo.id
     *
     * @return the value of realtion_actor_dictinfo.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_dictinfo.id
     *
     * @param id the value for realtion_actor_dictinfo.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_dictinfo.actor_id
     *
     * @return the value of realtion_actor_dictinfo.actor_id
     *
     * @mbggenerated
     */
    public Integer getActorId() {
        return actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_dictinfo.actor_id
     *
     * @param actorId the value for realtion_actor_dictinfo.actor_id
     *
     * @mbggenerated
     */
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_dictinfo.dictinfo_id
     *
     * @return the value of realtion_actor_dictinfo.dictinfo_id
     *
     * @mbggenerated
     */
    public Integer getDictinfoId() {
        return dictinfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_dictinfo.dictinfo_id
     *
     * @param dictinfoId the value for realtion_actor_dictinfo.dictinfo_id
     *
     * @mbggenerated
     */
    public void setDictinfoId(Integer dictinfoId) {
        this.dictinfoId = dictinfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_dictinfo
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
        sb.append(", actorId=").append(actorId);
        sb.append(", dictinfoId=").append(dictinfoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}