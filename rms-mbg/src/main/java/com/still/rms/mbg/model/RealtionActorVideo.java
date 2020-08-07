package com.still.rms.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table realtion_actor_video
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class RealtionActorVideo implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "演员ID")
    private Integer actorId;

    @ApiModelProperty(value = "视频ID")
    private Integer videoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table realtion_actor_video
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_video.id
     *
     * @return the value of realtion_actor_video.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_video.id
     *
     * @param id the value for realtion_actor_video.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_video.actor_id
     *
     * @return the value of realtion_actor_video.actor_id
     *
     * @mbggenerated
     */
    public Integer getActorId() {
        return actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_video.actor_id
     *
     * @param actorId the value for realtion_actor_video.actor_id
     *
     * @mbggenerated
     */
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_actor_video.video_id
     *
     * @return the value of realtion_actor_video.video_id
     *
     * @mbggenerated
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_actor_video.video_id
     *
     * @param videoId the value for realtion_actor_video.video_id
     *
     * @mbggenerated
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_video
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
        sb.append(", videoId=").append(videoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}