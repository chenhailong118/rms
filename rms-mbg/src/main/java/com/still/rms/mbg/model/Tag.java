package com.still.rms.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tag
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class Tag implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "标签别名")
    private String aliase;

    @ApiModelProperty(value = "标签等级")
    private Integer level;

    @ApiModelProperty(value = "标签排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "前端隐藏：0-不隐藏，1-隐藏")
    private Integer hidden;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tag
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.id
     *
     * @return the value of tag.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.id
     *
     * @param id the value for tag.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.parent_id
     *
     * @return the value of tag.parent_id
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.parent_id
     *
     * @param parentId the value for tag.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.name
     *
     * @return the value of tag.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.name
     *
     * @param name the value for tag.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.aliase
     *
     * @return the value of tag.aliase
     *
     * @mbggenerated
     */
    public String getAliase() {
        return aliase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.aliase
     *
     * @param aliase the value for tag.aliase
     *
     * @mbggenerated
     */
    public void setAliase(String aliase) {
        this.aliase = aliase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.level
     *
     * @return the value of tag.level
     *
     * @mbggenerated
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.level
     *
     * @param level the value for tag.level
     *
     * @mbggenerated
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.sort
     *
     * @return the value of tag.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.sort
     *
     * @param sort the value for tag.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.create_time
     *
     * @return the value of tag.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.create_time
     *
     * @param createTime the value for tag.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.update_time
     *
     * @return the value of tag.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.update_time
     *
     * @param updateTime the value for tag.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.hidden
     *
     * @return the value of tag.hidden
     *
     * @mbggenerated
     */
    public Integer getHidden() {
        return hidden;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.hidden
     *
     * @param hidden the value for tag.hidden
     *
     * @mbggenerated
     */
    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag
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
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", aliase=").append(aliase);
        sb.append(", level=").append(level);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", hidden=").append(hidden);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}