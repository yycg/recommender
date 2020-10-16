package com.bupt.recommender.entity;

import lombok.Data;

@Data
public class MoviePO {
    private Integer id;
    private String title;
    private String originalTitle;
    private String aka;
    private String rating;
    private Integer year;
    private String countries;
    private String genres;
    private String summary;
    private Integer ratingsCount;
    private Integer commentsCount;
    private Integer reviewsCount;
    private String subtype;
}
