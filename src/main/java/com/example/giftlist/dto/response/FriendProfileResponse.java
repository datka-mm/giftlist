package com.example.giftlist.dto.response;

import com.example.giftlist.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FriendProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private String photo;
    private String country;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private int shoeSize;
    private String clothingSize;
    private String hobby;
    private String important;
    private Boolean isBlock;
    private Boolean isMe;
    private Boolean sendRequest;
    private List<FriendWishesResponse> wishResponses;
    private List<HolidayResponses> holidayResponses;
    private List<CharityResponse> charityResponses;
    private String facebookLink;
    private String instagramLink;
    private String telegramLink;
    private String vkLink;
}