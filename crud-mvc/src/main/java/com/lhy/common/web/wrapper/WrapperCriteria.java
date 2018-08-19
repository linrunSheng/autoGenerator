package com.lhy.common.web.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public interface WrapperCriteria<O> {

    QueryWrapper criteria(QueryWrapper queryWrapper, String condition, String key, O value);
}
