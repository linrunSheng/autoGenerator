package com.lhy.commonweb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 校验对象
 */
@ApiModel(value = "ResponseValidate（校验对象）", description = "校验对象")
@Data
public class ResponseValidate implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    @ApiModelProperty(value = "返回结果:true 成功 false失败", notes = " true成功 false失败", required = true)
    private boolean valid;

    private String msg;

    public static ResponseValidate ok() {
        return create(true);
    }

    public static ResponseValidate fail() {
        return create(false);
    }

    public static ResponseValidate create(boolean valid) {
        return new ResponseValidate().setValid(valid);
    }
}
