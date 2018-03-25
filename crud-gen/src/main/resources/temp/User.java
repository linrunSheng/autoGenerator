package com.lhy.example.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "t_user")
@ApiModel("User（）")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value ="",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value ="用户名",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value ="密码",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String pass;

    /**
     * 手机号
     */
    @ApiModelProperty(value ="手机号",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String phone;

    /**
     * 用户状态
     */
    @ApiModelProperty(value ="用户状态",required = false)
    @ColumnType(jdbcType=JdbcType.BIT)
    private Boolean status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value ="创建时间",required = false)
    @ColumnType(jdbcType=JdbcType.TIMESTAMP)
    private Date created;

    /**
     * 更新时间
     */
    @ApiModelProperty(value ="更新时间",required = false)
    @ColumnType(jdbcType=JdbcType.TIMESTAMP)
    private Date updated;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public User setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取密码
     *
     * @return pass - 密码
     */
    public String getPass() {
        return pass;
    }

    /**
     * 设置密码
     *
     * @param pass 密码
     */
    public User setPass(String pass) {
        this.pass = pass;
        return this;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public User setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public User setCreated(Date created) {
        this.created = created;
        return this;
    }

    /**
     * 获取更新时间
     *
     * @return updated - 更新时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间
     *
     * @param updated 更新时间
     */
    public User setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public enum FieldEnum {
        ID("id","id"),
		NAME("name","name"),
		PASS("pass","pass"),
		PHONE("phone","phone"),
		STATUS("status","status"),
		CREATED("created","created"),
		UPDATED("updated","updated");

        private String javaFieldName;

        private String dbFieldName;

        FieldEnum(String javaFieldName, String dbFieldName) {
            this.javaFieldName = javaFieldName;
            this.dbFieldName = dbFieldName;
        }

        public String javaFieldName() {
            return javaFieldName;
        }

        public String dbFieldName() {
            return dbFieldName;
        }
    }
}