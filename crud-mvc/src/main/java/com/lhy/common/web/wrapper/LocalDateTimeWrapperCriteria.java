package com.lhy.common.web.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeWrapperCriteria implements WrapperCriteria<LocalDateTime> {

    @Override
    public QueryWrapper criteria(QueryWrapper queryWrapper, String condition, String key, LocalDateTime value) {
        if ("andLike".equals(condition)) {
            queryWrapper.eq(key,value);
        }
        return null;
    }
}

