package com.bupt.recommender.common;

import lombok.Data;

@Data
public class RecommendResult<T> {
    private Integer returnCode;
    private String returnInfo;
    private T result;
}
