package com.lhy.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author hyluan
 * @since 2018-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sp_user")
@ApiModel(value="SpUser对象", description="用户")
public class SpUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String pass;

    @ApiModelProperty(value = "昵称姓名")
    private String nick;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户状态")
    private Integer state;

    @ApiModelProperty(value = "最近访问时间")
    @TableField("visit_time")
    private LocalDateTime visitTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "学校编码")
    @TableField("school_code")
    private String schoolCode;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PASS = "pass";

    public static final String NICK = "nick";

    public static final String EMAIL = "email";

    public static final String PHONE = "phone";

    public static final String STATE = "state";

    public static final String VISIT_TIME = "visit_time";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String SCHOOL_CODE = "school_code";

}
