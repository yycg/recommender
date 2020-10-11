package com.bupt.recommender.service;

import com.bupt.recommender.entity.EventPO;

import java.util.List;

public interface EventService {
    List<EventPO> getPopularEvents() throws Exception;
}
