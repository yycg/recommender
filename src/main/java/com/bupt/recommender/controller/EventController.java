package com.bupt.recommender.controller;

import com.bupt.recommender.common.ResultBean;
import com.bupt.recommender.entity.EventPO;
import com.bupt.recommender.service.EventService;
import com.bupt.recommender.vo.EventVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(path="/event", method= RequestMethod.GET)
    public ResultBean<List<EventVO>> getPopularEvents() {
        try {
            List<EventVO> eventVOs = new ArrayList<>();
            for (EventPO eventPO: eventService.getPopularEvents()) {
                EventVO eventVO = new EventVO();
                BeanUtils.copyProperties(eventPO, eventVO);
                eventVOs.add(eventVO);
            }
            System.out.println(new ResultBean<>(eventVOs));
            return new ResultBean<>(eventVOs);
        } catch (Exception e) {

        }

        return null;
    }
}
