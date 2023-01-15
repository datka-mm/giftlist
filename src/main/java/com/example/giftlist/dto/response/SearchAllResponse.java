package com.example.giftlist.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchAllResponse {

    List<YourCharityResponse> myCharities = new ArrayList<>();
    List<SearchCharityResponse> searchOthers = new ArrayList<>();
}