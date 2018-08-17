package com.lhy.common.web.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ResponseResult
 * <p/>
 * 返回的实体对象
 * @author hyluan
 * @date 2018/8/17 14:33
 * Copyright (c) 2018 wisedu
 */
@ApiModel(value = "ResponseResult（响应结果）", description = "数据")
@Data
public class ResponseResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    @ApiModelProperty(value = "返回结果:true 成功 false失败", notes = " true成功 false失败", required = true)
    private boolean success;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回的业务数据")
    private T data;

    public ResponseResult() {
    }

    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<T>().setSuccess(true);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<T>().setSuccess(false);
    }

    public static <T> ResponseResult<T> create(int record) {
        return new ResponseResult<>(record);
    }

    public static <T> ResponseResult<T> create(boolean ret) {
        return ret ? ok() : fail();
    }

    ResponseResult(int record) {
        super();
        this.success = record > 0;
    }

}
