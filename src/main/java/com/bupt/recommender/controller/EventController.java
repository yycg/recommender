package com.bupt.recommender.controller;

import com.bupt.recommender.common.ResultBean;
import com.bupt.recommender.dto.EventDTO;
import com.bupt.recommender.service.EventService;
import com.bupt.recommender.vo.EventVO;
import com.bupt.recommender.vo.EventsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @RequestMapping(path="/event/popular", method=RequestMethod.GET)
    public ResultBean<EventsVO> getPopularEvents() {
        logger.info("getPopularEvents");
        try {
            List<EventDTO> eventDTOs = eventService.getPopularEvents();
            Collections.shuffle(eventDTOs);
            List<EventDTO> randomEventDTOs = eventDTOs.subList(0, 5);

            EventsVO eventsVO = new EventsVO();
            eventsVO.setEventDTOs(randomEventDTOs);
            return new ResultBean<>(eventsVO);
        } catch (Exception e) {
            logger.warn("getPopularEvents exception", e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/category", method=RequestMethod.GET)
    public ResultBean<EventsVO> getEventsByCategory(
            @RequestParam(value="category", required=true) String category,
            @RequestParam(value="subcategory", required=false) String subcategory,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("getEventsByCategory: category {}, subcategory {}, start {}, count {}",
                category, subcategory, start, count);
        try {
            EventsVO eventsVO = new EventsVO();
            List<EventDTO> eventDTOs = eventService.getEventsByCategory(category, subcategory, start, count);
            eventsVO.setEventDTOs(eventDTOs);
            eventsVO.setStart(start);
            eventsVO.setCount(eventDTOs.size());
            eventsVO.setTotal(eventService.countEventsByCategory(category, subcategory));

            return new ResultBean<>(eventsVO);
        } catch (Exception e) {
            logger.warn("getEventsByCategory exception: category {}, subcategory {}, start {}, count {}",
                    category, subcategory, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/{id}", method=RequestMethod.GET)
    public ResultBean<EventVO> getEventDetail(@PathVariable(name="id", required=true) Integer id) {
        logger.info("getEventDetail: id {}", id);
        try {
            EventVO eventVO = new EventVO();
            eventVO.setEventDTO(eventService.getEventById(id));
            return new ResultBean<>(eventVO);
        } catch (Exception e) {
            logger.warn("getEventDetail exception: id {}", id, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/search", method=RequestMethod.GET)
    public ResultBean<EventsVO> searchEvents(
            @RequestParam(value="keyword", required=true) String keyword,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("searchEvent: keyword {}", keyword);
        try {
            List<EventDTO> eventDTOs = eventService.getPopularEvents();
            EventsVO eventsVO = new EventsVO();
            eventsVO.setEventDTOs(eventDTOs.subList(start, start+count));
            eventsVO.setStart(start);
            eventsVO.setCount(count);
            eventsVO.setTotal(eventDTOs.size());
            return new ResultBean<>(eventsVO);
        } catch (Exception e) {
            logger.warn("searchEvent exception: keyword {}", keyword, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/recommend", method=RequestMethod.GET)
    public ResultBean<EventsVO> recommendEvents(
            @RequestParam(value="algorithm", required=true) String algorithm,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("recommendEvents: algorithm {}", algorithm);
        try {
            List<EventDTO> eventDTOs = eventService.getPopularEvents();
            EventsVO eventsVO = new EventsVO();
            eventsVO.setEventDTOs(eventDTOs.subList(start, start+count));
            eventsVO.setStart(start);
            eventsVO.setCount(count);
            eventsVO.setTotal(eventDTOs.size());
            return new ResultBean<>(eventsVO);
        } catch (Exception e) {
            logger.warn("recommendEvents exception: keyword {}", algorithm, e);
            return new ResultBean<>(e);
        }
    }
}
