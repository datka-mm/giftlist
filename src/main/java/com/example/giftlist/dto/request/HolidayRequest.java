package com.example.giftlist.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HolidayRequest {

    private String name;
    private LocalDate dateOfHoliday;
    private String image;
}
