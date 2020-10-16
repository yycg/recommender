package com.bupt.recommender.service.impl;

import com.bupt.recommender.entity.MoviePO;
import com.bupt.recommender.mapper.MovieMapper;
import com.bupt.recommender.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<MoviePO> getTopMovies() throws Exception {
        return movieMapper.getTopMovies();
    }

    @Override
    public List<MoviePO> getMoviesByCondition(Integer yearMin, Integer yearMax, String country, String genre,
                                              String subtype) throws Exception {
        return movieMapper.getMoviesByCondition(yearMin, yearMax, country, genre, subtype);
    }

    @Override
    public MoviePO getMovieById(Integer id) throws Exception {
        return movieMapper.getMovieById(id);
    }
}
