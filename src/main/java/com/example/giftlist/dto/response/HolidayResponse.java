package com.example.giftlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HolidayResponse {

    private Long holidayId;
    private String name;
    private LocalDate localDate;
}