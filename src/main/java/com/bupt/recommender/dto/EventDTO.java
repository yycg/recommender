package com.bupt.recommender.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {
    private Integer id;
    private Integer ownerId;
    private String title;
    private String content;
    private String category;
    private String categoryName;
    private Date beginTime;
    private Date endTime;
    private String timeStr;
    private Integer locId;
    private String locName;
    private String address;
    private String geo;
    private String longitude;
    private String latitude;
    private String priceRange;
    private String feeStr;
    private Byte hasTicket;
    private Integer album;
    private String canInvite;
    private String subcategoryName;
    private String label;
    private String tags;
    private String alt;
    private String uri;
    private String url;
    private String adaptUrl;
    private String image;
    private String imageHlarge;
    private String imageLmobile;
    private String participantCount;
    private String wisherCount;
}
