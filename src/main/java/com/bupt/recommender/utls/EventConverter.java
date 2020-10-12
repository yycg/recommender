package com.bupt.recommender.utls;

import com.bupt.recommender.dto.EventDTO;
import com.bupt.recommender.entity.EventPO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class EventConverter {
    public static EventDTO convertEventPO2DTO(EventPO eventPO) {
        EventDTO eventDTO = new EventDTO();
        BeanUtils.copyProperties(eventPO, eventDTO);

        if (!StringUtils.isEmpty(eventPO.getGeo())) {
            String[] geo = eventPO.getGeo().split(" ");
            eventDTO.setLongitude(geo[0]);
            eventDTO.setLatitude(geo[1]);
        }

        return eventDTO;
    }

    public static List<EventDTO> convertEventPOs2DTOs(List<EventPO> eventPOs) {
        return eventPOs.stream().map(EventConverter::convertEventPO2DTO).collect(Collectors.toList());
    }
}
