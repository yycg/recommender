package com.bupt.recommender.service;

import com.bupt.recommender.entity.MoviePO;

import java.util.List;

public interface MovieService {
    List<MoviePO> getTopMovies() throws Exception;

    List<MoviePO> getMoviesByCondition(Integer yearMin, Integer yearMax, String country, String genre,
                                       String subtype) throws Exception;

    MoviePO getMovieById(Integer id) throws Exception;
}
