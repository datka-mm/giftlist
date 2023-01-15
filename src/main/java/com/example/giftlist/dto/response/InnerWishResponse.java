package com.example.giftlist.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerWishResponse {

    private Long id;
    private String wishName;
    private String linkToGift;
    private HolidayResponse holiday;
    private String description;
    private String image;
    private Boolean isBlock;
}
