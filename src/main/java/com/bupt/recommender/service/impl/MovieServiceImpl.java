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
    private MovieMapper movieMapper;

    @Override
    public List<MoviePO> getTopMovies(int start, int count) throws Exception {
        return movieMapper.getTopMovies(start, count);
    }

    @Override
    public List<MoviePO> getMoviesByConditions(Integer yearMin, Integer yearMax, String country, String genre,
                                               String subtype, int start, int count) throws Exception {
        return movieMapper.getMoviesByConditions(yearMin, yearMax, country, genre, subtype, start, count);
    }

    @Override
    public int countMoviesByConditions(Integer yearMin, Integer yearMax, String country, String genre, String subtype)
            throws Exception {
        return movieMapper.countMoviesByConditions(yearMin, yearMax, country, genre, subtype);
    }

    @Override
    public MoviePO getMovieById(Integer id) throws Exception {
        return movieMapper.getMovieById(id);
    }
}
