package com.bupt.recommender.service;

import com.bupt.recommender.entity.MoviePO;

import java.util.List;

public interface MovieService {
    List<MoviePO> getTopMovies(int start, int count) throws Exception;

    List<MoviePO> getMoviesByConditions(Integer yearMin, Integer yearMax, String country, String genre,
                                        String subtype, int start, int count) throws Exception;

    int countMoviesByConditions(Integer yearMin, Integer yearMax, String country, String genre, String subtype)
            throws Exception;

    MoviePO getMovieById(Integer id) throws Exception;
}
