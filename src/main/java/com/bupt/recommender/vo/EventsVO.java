package com.bupt.recommender.vo;

import com.bupt.recommender.dto.EventDTO;
import lombok.Data;

import java.util.List;

@Data
public class EventsVO {
    private List<EventDTO> eventDTOs;

    private Integer total;

    private Integer start;

    private Integer count;

}
