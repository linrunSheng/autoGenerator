package com.lhy.common.web.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luanhy
 * @ClassName: RequestPage
 * @Description: 通用请求分页参数
 * @date 2017年7月7日 下午4:22:42
 * @Copyright: Copyright (c) 2017 wisedu
 */
@Data
@ApiModel(value = "分页请求参数", description = "分页请求参数")
public class SimplePage implements Serializable {

    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_NUMBER = 1;

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "每页长度", required = false)
    @Min(1)
    @Digits(integer = 100, fraction = 0)
    private Integer rows = DEFAULT_PAGE_SIZE;

    @ApiModelProperty(value = "页码", required = false)
    @Min(1)
    @Digits(integer = 100, fraction = 0)
    private Integer page = DEFAULT_PAGE_NUMBER;

    /**
     * 排序列
     * 前端参数传递方式： <br/>
     * encodeURIComponent('sort[0].name=id')+'&'+encodeURIComponent(sort[0].order=desc)
     * 转义后如下：<br/>
     * ?sort%5B0%5D.name=name&sort%5B0%5D.order=desc&sort%5B1%5D.name=id&sort%5B1%5D.order=desc
     */
    private List<SimpleSort> sort = new ArrayList<>();

}
