package com.bupt.recommender.controller;

import com.bupt.recommender.common.ResultBean;
import com.bupt.recommender.dto.EventDTO;
import com.bupt.recommender.service.EventService;
import com.bupt.recommender.vo.EventRespVO;
import com.bupt.recommender.vo.EventsRespVO;
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
    public ResultBean<EventsRespVO> getPopularEvents() {
        logger.info("getPopularEvents");
        try {
            List<EventDTO> eventDTOs = eventService.getPopularEvents();
            Collections.shuffle(eventDTOs);
            List<EventDTO> randomEventDTOs = eventDTOs.subList(0, 5);

            EventsRespVO eventsRespVO = new EventsRespVO();
            eventsRespVO.setEventDTOs(randomEventDTOs);
            return new ResultBean<>(eventsRespVO);
        } catch (Exception e) {
            logger.warn("getPopularEvents exception", e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/category", method=RequestMethod.GET)
    public ResultBean<EventsRespVO> getEventsByCategory(
            @RequestParam(value="category", required=true) String category,
            @RequestParam(value="subcategory", required=false) String subcategory,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("getEventsByCategory: category {}, subcategory {}, start {}, count {}",
                category, subcategory, start, count);
        try {
            EventsRespVO eventsRespVO = new EventsRespVO();
            List<EventDTO> eventDTOs = eventService.getEventsByCategory(category, subcategory, start, count);
            eventsRespVO.setEventDTOs(eventDTOs);
            eventsRespVO.setStart(start);
            eventsRespVO.setCount(eventDTOs.size());
            eventsRespVO.setTotal(eventService.countEventsByCategory(category, subcategory));

            return new ResultBean<>(eventsRespVO);
        } catch (Exception e) {
            logger.warn("getEventsByCategory exception: category {}, subcategory {}, start {}, count {}",
                    category, subcategory, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/{id}", method=RequestMethod.GET)
    public ResultBean<EventRespVO> getEventDetail(@PathVariable(name="id", required=true) Integer id) {
        logger.info("getEventDetail: id {}", id);
        try {
            EventRespVO eventRespVO = new EventRespVO();
            eventRespVO.setEventDTO(eventService.getEventById(id));
            return new ResultBean<>(eventRespVO);
        } catch (Exception e) {
            logger.warn("getEventDetail exception: id {}", id, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/search", method=RequestMethod.GET)
    public ResultBean<EventsRespVO> searchEvents(
            @RequestParam(value="keyword", required=true) String keyword,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("searchEvent: keyword {}, start {}, count {}", keyword, start, count);
        try {
            // TODO implement this, return fake data temporarily
            List<EventDTO> eventDTOs = eventService.getPopularEvents();
            EventsRespVO eventsRespVO = new EventsRespVO();
            eventsRespVO.setEventDTOs(eventDTOs.subList(start, start+count));
            eventsRespVO.setStart(start);
            eventsRespVO.setCount(count);
            eventsRespVO.setTotal(eventDTOs.size());
            return new ResultBean<>(eventsRespVO);
        } catch (Exception e) {
            logger.warn("searchEvent exception: keyword {}, start {}, count {}", keyword, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/event/recommend", method=RequestMethod.GET)
    public ResultBean<EventsRespVO> recommendEvents(
            @RequestParam(value="algorithm", required=true) String algorithm,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("recommendEvents: algorithm {}, start {}, count {}", algorithm, start, count);
        try {
            // TODO implement this, return fake data temporarily
            List<EventDTO> eventDTOs = eventService.getPopularEvents();
            EventsRespVO eventsRespVO = new EventsRespVO();
            eventsRespVO.setEventDTOs(eventDTOs.subList(start, start+count));
            eventsRespVO.setStart(start);
            eventsRespVO.setCount(count);
            eventsRespVO.setTotal(eventDTOs.size());
            return new ResultBean<>(eventsRespVO);
        } catch (Exception e) {
            logger.warn("recommendEvents exception: keyword {}, start {}, count {}", algorithm, start, count, e);
            return new ResultBean<>(e);
        }
    }
}
