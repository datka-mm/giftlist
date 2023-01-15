package com.example.giftlist.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfileResponse {

    private Long id;
    private String image;
    private String firstName;
    private String lastName;
    private String email;
}
