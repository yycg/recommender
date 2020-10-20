package com.bupt.recommender.vo;

import com.bupt.recommender.entity.MoviePO;
import lombok.Data;

import java.util.List;

@Data
public class MoviesRespVO {
    private List<MoviePO> moviePOs;

    private Integer total;

    private Integer start;

    private Integer count;
}
