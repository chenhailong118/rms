package com.still.aikandy.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table realtion_video_dictinfo
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class RealtionVideoDictinfo implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "演员ID")
    private Integer videoId;

    @ApiModelProperty(value = "字段信息ID")
    private Integer dictinfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table realtion_video_dictinfo
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_video_dictinfo.id
     *
     * @return the value of realtion_video_dictinfo.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_video_dictinfo.id
     *
     * @param id the value for realtion_video_dictinfo.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_video_dictinfo.video_id
     *
     * @return the value of realtion_video_dictinfo.video_id
     *
     * @mbggenerated
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_video_dictinfo.video_id
     *
     * @param videoId the value for realtion_video_dictinfo.video_id
     *
     * @mbggenerated
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column realtion_video_dictinfo.dictinfo_id
     *
     * @return the value of realtion_video_dictinfo.dictinfo_id
     *
     * @mbggenerated
     */
    public Integer getDictinfoId() {
        return dictinfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column realtion_video_dictinfo.dictinfo_id
     *
     * @param dictinfoId the value for realtion_video_dictinfo.dictinfo_id
     *
     * @mbggenerated
     */
    public void setDictinfoId(Integer dictinfoId) {
        this.dictinfoId = dictinfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_video_dictinfo
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
        sb.append(", videoId=").append(videoId);
        sb.append(", dictinfoId=").append(dictinfoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}