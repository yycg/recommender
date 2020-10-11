package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.EventPO;

import java.util.List;

public interface EventMapper {
    List<EventPO> getPopularEvents() throws Exception;
}
