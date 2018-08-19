package com.lhy.common.web.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public class WrapperCriteriaDispatcher {

    @Autowired
    List<WrapperCriteria> wrapperCriteriaList;

    public <T> QueryWrapper dispatch(QueryWrapper<T> queryWrapper, String key, Object value, String condition) {
        WrapperCriteria<T> wrapperCriteria = wrapperCriteriaList.stream().filter(criteria -> {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) criteria.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            return entityClass.isAssignableFrom(value.getClass());
        }).findFirst().orElseThrow(() -> new RuntimeException("未找到对应WrapperCriteria处理类" + value.getClass()));
        return wrapperCriteria.criteria(queryWrapper, condition, key, (T) value);
    }
}
