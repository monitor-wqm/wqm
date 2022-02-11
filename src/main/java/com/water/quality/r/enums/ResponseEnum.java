package com.water.quality.r.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {

    SUCCESS(0, "成功"),
    ERROR(-1, "服务器内部错误"),

    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(101, "sql语法错误"),
    SERVLET_ERROR(102, "servlet请求异常"), //-2xx 参数校验
    UPLOAD_ERROR(103, "文件上传错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败"),
    DELETE_DATA_ERROR(105, "数据删除失败"),
    TYPE_DATA_ERROR(106, "数据类型错误"),
    DATABASE_UPDATE_ERROR(107, "数据修改错误，请检查数据是否符合标准"),

    //-2xx 参数校验
    COMMIT_NULL_ERROR(201, "提交参数不能为空"),
    USERNAME_NULL_ERROR(202, "用户名不能为空"),
    DATABASE_NULL_ERROR(203,"数据库查询为空"),
    //    MOBILE_ERROR(-203, "手机号码不正确"),
    PASSWORD_NULL_ERROR(204, "密码不能为空"),
    CODE_NULL_ERROR(205, "验证码不能为空"),
    CODE_ERROR(206, "验证码错误"),
    USERNAME_EXIST_ERROR(207, "用户名已被注册"),
    LOGIN_USER_ERROR(208, "用户不存在"),
    LOGIN_PASSWORD_ERROR(209, "密码错误"),
    LOGIN_LOKED_ERROR(210, "用户被锁定"),
    LOGIN_AUTH_ERROR(211, "未登录"),
    DATA_REPEAT_ERROR(212, "数据重复"),
    ADD_ID_NOT(213, "新增ID不能指定"),
    DELETE_ID_NOT(214, "删除ID不能指定"),
    ID_NULL_ERROR(215, "ID不能为空"),

    USER_BIND_IDCARD_EXIST_ERROR(301, "身份证号码已绑定"),
    USER_NO_BIND_ERROR(302, "用户未绑定"),
    USER_NO_AMOUNT_ERROR(303, "用户信息未审核"),

    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数格式校验失败"),
    TO_MANY_REQUEST(10002, "请求流量过大，请稍后再试"),
    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高，请稍后再试"),
    USER_EXIST_EXCEPTION(15001, "存在相同的用户"),
    PHONE_EXIST_EXCEPTION(15002, "存在相同的手机号"),
    LOGINACCT_PASSWORD_EXCEPTION(15003, "账号或密码错误"),
    CODE_NOTSAME_EXCEPTION(15004, "验证码错误");

    //响应状态码
    private Integer code;

    //响应信息
    private String message;

}
