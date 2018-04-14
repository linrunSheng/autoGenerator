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
import java.math.BigDecimal;
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

    public T getCond(T bean) {
        List<T> select = this.mapper.select(bean);
        if (select.isEmpty()) {
            return null;
        }
        return select.get(0);
    }

    public int add(T bean) {
        return this.mapper.insertSelective(bean);
    }

    public int update(T bean) {
        return this.mapper.updateByPrimaryKeySelective(bean);
    }

    public int update(T bean, Example example) {
        return this.mapper.updateByExample(bean, example);
    }

    public int delete(P id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    public int deleteCond(T bean) {
        return this.mapper.delete(bean);
    }

    public int delete(Example example) {
        return this.mapper.deleteByExample(example);
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
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(trunlatePageNumber, trunlatePageSize, trunlateSortColumns);
        List<T> select = this.mapper.select(bean);
        return new Page<T>(trunlatePageNumber, trunlatePageSize, new BigDecimal(startPage.getTotal()).intValue(), select);
    }

    public List<T> query(T bean) {
        return this.mapper.select(bean);
    }

    public T queryOne(T bean) {
        return this.mapper.selectOne(bean);
    }

    public List<T> query(Example e) {
        return this.mapper.selectByExample(e);
    }

    public List<T> queryAll() {
        return this.mapper.selectAll();
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
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(trunlatePageNumber, trunlatePageSize, trunlateSortColumns);
        List<T> select = this.mapper.selectByExample(example);
        return new Page<T>(trunlatePageNumber, trunlatePageSize, new BigDecimal(startPage.getTotal()).intValue(), select);
    }

    public int count(T bean) {
        return this.mapper.selectCount(bean);
    }

    public int count(Example example) {
        return this.mapper.selectCountByExample(example);
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
