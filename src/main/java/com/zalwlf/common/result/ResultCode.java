package com.zalwlf.common.result;

/**
 * 枚举了一些常用API操作码
 * @author macro,lcq
 * @date 2019/4/19
 */
public enum ResultCode implements IErrorCode {
    /**
     * 默认返回结果限定，提供响应成功、异常、资源找不到、权限、token过期等
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "数据没有找到"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
