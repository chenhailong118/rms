package com.still.rms.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table auth_role_menu
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class AuthRoleMenu implements Serializable {
    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table auth_role_menu
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_role_menu.id
     *
     * @return the value of auth_role_menu.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_role_menu.id
     *
     * @param id the value for auth_role_menu.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_role_menu.role_id
     *
     * @return the value of auth_role_menu.role_id
     *
     * @mbggenerated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_role_menu.role_id
     *
     * @param roleId the value for auth_role_menu.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_role_menu.menu_id
     *
     * @return the value of auth_role_menu.menu_id
     *
     * @mbggenerated
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_role_menu.menu_id
     *
     * @param menuId the value for auth_role_menu.menu_id
     *
     * @mbggenerated
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_role_menu
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
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId=").append(menuId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}