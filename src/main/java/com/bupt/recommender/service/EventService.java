package com.bupt.recommender.service;

import com.bupt.recommender.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getPopularEvents() throws Exception;

    List<EventDTO> getEventsByCategory(String category, String subcategory, int start, int count) throws Exception;

    int countEventsByCategory(String category, String subcategory) throws Exception;

    EventDTO getEventById(int id) throws Exception;
}
