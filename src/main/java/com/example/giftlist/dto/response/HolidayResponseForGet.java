package com.example.giftlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HolidayResponseForGet {

    private Long id;
    private String name;
    private LocalDate dateOfHoliday;
    private String image;
    private Long user;
    private Boolean holidayIsBlock;
    private List<HolidayGiftsResponse> wishResponse;
}
