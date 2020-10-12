package com.bupt.recommender.service.impl;

import com.bupt.recommender.dto.EventDTO;
import com.bupt.recommender.mapper.EventMapper;
import com.bupt.recommender.service.EventService;
import com.bupt.recommender.utls.EventConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;

    public List<EventDTO> getPopularEvents() throws Exception {
        return EventConverter.convertEventPOs2DTOs(eventMapper.getPopularEvents());
    }

    public List<EventDTO> getEventsByCategory(String category, String subcategory, int start, int count)
            throws Exception {
        return EventConverter.convertEventPOs2DTOs(eventMapper.getEventsByCategory(category, subcategory, start, count));
    }

    public int countEventsByCategory(String category, String subcategory) throws Exception {
        return eventMapper.countEventsByCategory(category, subcategory);
    }
}
