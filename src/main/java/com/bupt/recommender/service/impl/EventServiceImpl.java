package com.bupt.recommender.service.impl;

import com.bupt.recommender.dto.EventDTO;
import com.bupt.recommender.entity.UserPO;
import com.bupt.recommender.mapper.EventMapper;
import com.bupt.recommender.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    public List<EventDTO> getPopularEvents() throws Exception {
        return EventConverter.convertEventPOs2DTOs(eventMapper.getPopularEvents());
    }

    public List<EventDTO> getEventsByCategory(String category, String subcategory, int start, int count)
            throws Exception {
        List<EventDTO> eventDTOs = EventConverter.convertEventPOs2DTOs(
                eventMapper.getEventsByCategory(category, subcategory, start, count));
        for (EventDTO eventDTO: eventDTOs) {
            fillWithOwnerName(eventDTO);
        }
        return eventDTOs;
    }

    public int countEventsByCategory(String category, String subcategory) throws Exception {
        return eventMapper.countEventsByCategory(category, subcategory);
    }

    public EventDTO getEventById(int id) throws Exception {
        EventDTO eventDTO = EventConverter.convertEventPO2DTO(eventMapper.getEventById(id));
        fillWithOwnerName(eventDTO);
        return eventDTO;
    }

    private void fillWithOwnerName(EventDTO eventDTO) throws Exception {
        UserPO userPO = userMapper.getUserById(eventDTO.getOwnerId());
        if (userPO != null) {
            eventDTO.setOwnerName(userPO.getName());
        }
    }
}
