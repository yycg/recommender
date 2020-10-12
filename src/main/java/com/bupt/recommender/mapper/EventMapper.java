package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.EventPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventMapper {
    List<EventPO> getPopularEvents() throws Exception;

    List<EventPO> getEventsByCategory(@Param("category") String category, @Param("subcategory") String subcategory,
                                      @Param("start") int start, @Param("count") int count) throws Exception;

    int countEventsByCategory(@Param("category") String category, @Param("subcategory") String subcategory)
            throws Exception;

    EventPO getEventById(@Param("id") int id) throws Exception;
}
