package com.still.rms.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table realtion_actor_resource
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class RealtionActorResource implements Serializable {
    private Long id;

    @ApiModelProperty(value = "演员ID")
    private Long actorId;

    @ApiModelProperty(value = "视频ID")
    private Long resourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table realtion_actor_resource
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_resource.id
     *
     * @return the value of realtion_actor_resource.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_resource.id
     *
     * @param id the value for realtion_actor_resource.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_resource.actor_id
     *
     * @return the value of realtion_actor_resource.actor_id
     *
     * @mbggenerated
     */
    public Long getActorId() {
        return actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_resource.actor_id
     *
     * @param actorId the value for realtion_actor_resource.actor_id
     *
     * @mbggenerated
     */
    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_resource.resource_id
     *
     * @return the value of realtion_actor_resource.resource_id
     *
     * @mbggenerated
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_resource.resource_id
     *
     * @param resourceId the value for realtion_actor_resource.resource_id
     *
     * @mbggenerated
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_resource
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
        sb.append(", resourceId=").append(resourceId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}