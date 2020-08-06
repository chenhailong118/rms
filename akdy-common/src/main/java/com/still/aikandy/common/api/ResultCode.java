package com.still.aikandy.common.api;

public enum ResultCode {
  OK(200,"操作成功"),
  UNAUTHORIZED(401, "暂未登录或token已经过期"),
  FORBIDDEN(403, "没有相关权限"),
  UNKNOWN_ERROR(10000,"未知异常"),
  WRONG_PAGE(10100,"页码不合法"),
  USER_NOT_FOUND(10101,"用户未找到"),
  ROLE_NOT_FOUND(10101,"角色未找到"),
  MENU_NOT_FOUND(10101,"菜单未找到"),
  RESOURCE_NOT_FOUND(10101,"资源信息未找到"),
  RESOURCE_CATEGORY_NOT_FOUND(10101,"资源分类信息未找到"),
  PARENT_MENU_NOT_FOUND(10101,"父级菜单未找到"),
  ILLEGAL_PARAMS(10103,"参数不合法"),
  USER_EXIST(10104,"昵称已存在"),
  PHONE_EXIST(10105,"手机号码已存在"),
  EMAIL_EXIST(10106,"邮箱已存在"),
  PWD_NOT_EQUALS_COMPWD(10107,"密码和确认密码不一致"),
  USERNAME_OR_PASSWORD_WRONG(10108,"用户名或密码不正确"),
  ICON_PARSE_WRONG(10109,"头像解析异常"),
  TOKEN_ERROR(10110,"TOKEN异常"),
  HYSTRIX_DONOTHING_ERROR(10111,"HYSTRIX短路异常未处理"),
  USER_NAME_IS_NULL(10112,"用户名不能为空");


  public final int code;
  public final String msg;
  private ResultCode(int code, String msg){
    this.code = code;
    this.msg = msg;
  }

}
