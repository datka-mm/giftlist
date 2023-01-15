package com.example.giftlist.dto.response;

import com.example.giftlist.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharityResponse {

    private Long id;
    private String name;
    private Status charityStatus;
    private String description;
    private String condition;
    private String image;
    private LocalDate createdDate;
    private Boolean isMy;
    private Boolean isBlock;
    private ReservedUserResponse reservedUserResponse;
}