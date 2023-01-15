package com.example.giftlist.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String photo;
    private int giftCount;
    private Boolean isBlock;
}