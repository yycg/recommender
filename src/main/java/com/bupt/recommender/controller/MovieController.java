package com.bupt.recommender.controller;

import com.bupt.recommender.common.ResultBean;
import com.bupt.recommender.service.MovieService;
import com.bupt.recommender.vo.MoviesRespVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @RequestMapping(path="/movie/top", method=RequestMethod.GET)
    public ResultBean<MoviesRespVO> getTopMovies(
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("getTopMovies: start {}, count {}", start, count);
        try {
            MoviesRespVO moviesRespVO = new MoviesRespVO();
            moviesRespVO.setMoviePOs(movieService.getTopMovies(start, count));
            moviesRespVO.setStart(start);
            moviesRespVO.setCount(count);
            moviesRespVO.setTotal(250);
            return new ResultBean<>(moviesRespVO);
        } catch (Exception e) {
            logger.warn("getTopMovies exception: start {}, count {}", start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/movie/condition", method=RequestMethod.GET)
    public ResultBean<MoviesRespVO> getMoviesByConditions(
            @RequestParam(value="year", required=false) String year,
            @RequestParam(value="country", required=false) String country,
            @RequestParam(value="genre", required=false) String genre,
            @RequestParam(value="subtype", required=false) String subtype,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        try {
            Integer yearMin = null;
            Integer yearMax = null;
            if (year != null) {
                switch (year) {
                    case "---": {
                        break;
                    }
                    case "更早": {
                        yearMax = 1960;
                        break;
                    }
                    default: {
                        yearMin = Integer.parseInt(year.substring(0, year.length() - 1));
                        yearMax = yearMin + 10;
                    }
                }
            }

            String subtypeEng = null;
            if (subtype != null) {
                switch (subtype) {
                    case "电影": {
                        subtypeEng = "movie";
                        break;
                    }
                    case "电视": {
                        subtypeEng = "tv";
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

            MoviesRespVO moviesRespVO = new MoviesRespVO();
            moviesRespVO.setMoviePOs(movieService.getMoviesByConditions(yearMin, yearMax,
                    "---".equals(country)? null: country, "---".equals(genre)? null: genre,
                    subtypeEng, start, count));
            moviesRespVO.setStart(start);
            moviesRespVO.setCount(count);
            moviesRespVO.setTotal(movieService.countMoviesByConditions(yearMin, yearMax,
                    "---".equals(country)? null: country, "---".equals(genre)? null: genre, subtypeEng));
            return new ResultBean<>(moviesRespVO);
        } catch (Exception e) {
            logger.warn("getMoviesByConditions exception: year {}, country {}, genre {}, subtype {}, " +
                    "start {}, count {}", year, country, genre, subtype, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/movie/search", method=RequestMethod.GET)
    public ResultBean<MoviesRespVO> searchMovies(
            @RequestParam(value="keyword", required=true) String keyword,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("searchMovies: keyword {}, start {}, count {}", keyword, start, count);
        try {
            // TODO implement this, return fake data temporarily
            MoviesRespVO moviesRespVO = new MoviesRespVO();
            moviesRespVO.setMoviePOs(movieService.getTopMovies(start, count));
            moviesRespVO.setStart(start);
            moviesRespVO.setCount(count);
            moviesRespVO.setTotal(250);
            return new ResultBean<>(moviesRespVO);
        } catch (Exception e) {
            logger.warn("searchMovies: keyword {}, start {}, count {}", keyword, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/movie/recommend", method=RequestMethod.GET)
    public ResultBean<MoviesRespVO> recommendMovies(
            @RequestParam(value="algorithm", required=true) String algorithm,
            @RequestParam(value="movieTitles", required=true) String[] movieTitles,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("recommendMovies: algorithm {}, movieTitles {}, start {}, count {}",
                algorithm, movieTitles, start, count);
        try {
            // TODO implement this, return fake data temporarily
            MoviesRespVO moviesRespVO = new MoviesRespVO();
            moviesRespVO.setMoviePOs(movieService.getTopMovies(start, count));
            moviesRespVO.setStart(start);
            moviesRespVO.setCount(count);
            moviesRespVO.setTotal(250);
            return new ResultBean<>(moviesRespVO);
        } catch (Exception e) {
            logger.warn("recommendMovies: algorithm {}, start {}, count {}", algorithm, start, count, e);
            return new ResultBean<>(e);
        }
    }
}
