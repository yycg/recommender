package com.bupt.recommender.service.impl;

import com.bupt.recommender.entity.EventPO;
import com.bupt.recommender.mapper.EventMapper;
import com.bupt.recommender.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;

    public List<EventPO> getPopularEvents() throws Exception {
        return eventMapper.getPopularEvents();
    }
}
