package com.example.giftlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FriendInfoResponse {
    private Long id;
    private String image;
    private String fullName;
    private int countOfWishes;
    private int countOfHolidays;
}
