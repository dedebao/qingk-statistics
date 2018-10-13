package com.allook.statistics.core.model;

import com.alibaba.fastjson.JSON;

public interface Model {

    public default String toJson() {
        return JSON.toJSONString(this);
    }

}
