package com.still.rms.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table auth_menu
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class AuthMenu implements Serializable {
    private Long id;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "前端名称")
    private String name;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "菜单级数")
    private Integer level;

    @ApiModelProperty(value = "前端图标")
    private String icon;

    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "前端隐藏")
    private Integer hidden;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table auth_menu
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.id
     *
     * @return the value of auth_menu.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.id
     *
     * @param id the value for auth_menu.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.parent_id
     *
     * @return the value of auth_menu.parent_id
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.parent_id
     *
     * @param parentId the value for auth_menu.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.name
     *
     * @return the value of auth_menu.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.name
     *
     * @param name the value for auth_menu.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.title
     *
     * @return the value of auth_menu.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.title
     *
     * @param title the value for auth_menu.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.level
     *
     * @return the value of auth_menu.level
     *
     * @mbggenerated
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.level
     *
     * @param level the value for auth_menu.level
     *
     * @mbggenerated
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.icon
     *
     * @return the value of auth_menu.icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.icon
     *
     * @param icon the value for auth_menu.icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.sort
     *
     * @return the value of auth_menu.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.sort
     *
     * @param sort the value for auth_menu.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.create_time
     *
     * @return the value of auth_menu.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.create_time
     *
     * @param createTime the value for auth_menu.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_menu.hidden
     *
     * @return the value of auth_menu.hidden
     *
     * @mbggenerated
     */
    public Integer getHidden() {
        return hidden;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_menu.hidden
     *
     * @param hidden the value for auth_menu.hidden
     *
     * @mbggenerated
     */
    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_menu
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
        sb.append(", title=").append(title);
        sb.append(", level=").append(level);
        sb.append(", icon=").append(icon);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", hidden=").append(hidden);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}