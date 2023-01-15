package com.example.giftlist.dto.response;

import kg.peaksoft.giftlistb6.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedResponse {

    private Long wishId;
    private SearchUserResponse userSearchResponse;
    private String wishName;
    private String image;
    private HolidayResponse holiday;
    private UserFeedResponse userFeedResponse;
    private Status status;
    private Boolean isMy;
}
