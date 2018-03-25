package com.lhy.commonweb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @param <T> 返回的实体对象
 * @author luanhy
 * @ClassName: ResponseResult
 * @Description: 响应结果
 * @date 2017年7月7日 下午5:00:16
 * @Copyright: Copyright (c) 2017 wisedu
 */
@ApiModel(value = "ResponseResult（响应结果）", description = "数据")
@Data
@NoArgsConstructor
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

    public static <T> ResponseResult<T> ok() {
        return new ResponseResult().setSuccess(true);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult().setSuccess(false);
    }

    public static <T> ResponseResult<T> create(int record) {
        return new ResponseResult(record);
    }

    ResponseResult(int record) {
        super();
        this.success = record > 0 ? true : false;
    }

}
