package com.lhy.commonweb.service;

import com.github.pagehelper.PageHelper;
import com.lhy.commonweb.model.Page;
import com.lhy.commonweb.model.RequestPage;
import com.lhy.commonweb.util.ReflectionUtils;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

/**
 * 单表操作
 * Copyright: Copyright (c) 2017 wisedu
 *
 * @ClassName: AbstractService.java
 * @Description: 通用service类
 * 子类覆盖构造器注入mapper即可使用 例如：
 * @public XszcZpService(@ Qualifier ( " xszcZpMapper ") Mapper<XszcZp> mapper) {
 * super(mapper);
 * }
 * @version: v1.0.0
 * @author: hyluan
 * @date: 2017年4月24日 下午4:03:21
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2017年4月24日     hyluan           v1.0.0               修改原因
 */
public abstract class AbstractService<T extends Serializable, P extends Serializable> {

    protected Mapper<T> mapper;

    private Class clazz;

    public AbstractService(Mapper<T> mapper) {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        this.mapper = mapper;
    }

    protected <S> S getMapper() {
        return (S) this.mapper;
    }

    public T get(P id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public int add(T bean) {
        return this.mapper.insertSelective(bean);
    }

    public int update(T bean) {
        return this.mapper.updateByPrimaryKeySelective(bean);
    }

    public int delete(P id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    public int delete(Collection<P> ids) {
        Example example = new Example(clazz);
        example.createCriteria().andIn("id", ids);
        return this.mapper.deleteByExample(example);
    }

    public boolean existsBean(T bean) {
        return this.mapper.selectCount(bean) > 0 ? true : false;
    }

    public boolean exists(P id) {
        return this.mapper.existsWithPrimaryKey(id);
    }

    public Page<T> query(Integer pageNumber, Integer pageSize, String sortColumns, T bean) {
        int trunlatePageNumber = trunlatePageNumber(pageNumber);
        int trunlatePageSize = trunlatePageSize(pageSize);
        String trunlateSortColumns = trunlateSortColumns(sortColumns);
        int count = this.mapper.selectCount(bean);
        PageHelper.startPage(trunlatePageNumber, trunlatePageSize, trunlateSortColumns);
        List<T> select = this.mapper.select(bean);
        return new Page<T>(trunlatePageNumber, trunlatePageSize, count, select);
    }

    public Page<T> queryExample(Integer pageNumber, Integer pageSize, String sortColumns, T bean) {
        int trunlatePageNumber = trunlatePageNumber(pageNumber);
        int trunlatePageSize = trunlatePageSize(pageSize);
        String trunlateSortColumns = trunlateSortColumns(sortColumns);
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            Object fieldValue = ReflectionUtils.getFieldValue(bean, field.getName());
            if (!ObjectUtils.isEmpty(fieldValue)) {
                criteria.andLike(field.getName(), "%" + fieldValue + "%");
            }
        }
        int count = this.mapper.selectCountByExample(example);
        PageHelper.startPage(trunlatePageNumber, trunlatePageSize, trunlateSortColumns);
        List<T> select = this.mapper.selectByExample(example);
        return new Page<T>(trunlatePageNumber, trunlatePageSize, count, select);
    }

    private int trunlatePageNumber(Integer pageNumber) {
        return pageNumber == null ? RequestPage.DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int trunlatePageSize(Integer pageSize) {
        return pageSize == null ? RequestPage.DEFAULT_PAGE_SIZE : pageSize;
    }

    private String trunlateSortColumns(String sortColumns) {
        return sortColumns == null ? RequestPage.DEFAULT_SORT_COLUMNS : sortColumns;
    }

}
