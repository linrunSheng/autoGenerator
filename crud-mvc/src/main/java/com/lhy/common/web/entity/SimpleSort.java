package com.lhy.common.web.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "排序对象参数", description = "排序对象参数")
public class SimpleSort {

    @NotBlank
    @ApiModelProperty(value = "字段名称")
    String name;

    @ApiModelProperty(value = "order 可选值为 asc 或者 desc")
    @Pattern(regexp = "asc|desc")
    String order = "asc";
}