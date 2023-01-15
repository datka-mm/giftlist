package com.example.giftlist.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingResponse {
    private List<GiftResponse> getAllGifts;
    private List<GiftResponse> getReservedCharity;
}
