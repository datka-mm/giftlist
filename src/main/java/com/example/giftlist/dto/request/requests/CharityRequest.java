package com.example.giftlist.dto.request.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharityRequest {

    private String image;
    private String name;
    private String condition;
    private String category;
    private String subCategory;
    private String description;
}
