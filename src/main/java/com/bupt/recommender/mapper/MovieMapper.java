package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.MoviePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MovieMapper {
    List<MoviePO> getTopMovies(@Param("start") Integer start, @Param("count") Integer count) throws Exception;

    List<MoviePO> getMoviesByConditions(@Param("yearMin") Integer yearMin, @Param("yearMax") Integer yearMax,
                                       @Param("country") String country, @Param("genre") String genre,
                                       @Param("subtype") String subtype, @Param("start") Integer start,
                                       @Param("count") Integer count) throws Exception;

    int countMoviesByConditions(@Param("yearMin") Integer yearMin, @Param("yearMax") Integer yearMax,
                                @Param("country") String country, @Param("genre") String genre,
                                @Param("subtype") String subtype) throws Exception;

    MoviePO getMovieById(@Param("id") int id) throws Exception;
}
