package com.example.giftlist.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class  CharityResponses {

    List<YourCharityResponse> yourCharityResponses;
    List<OtherCharityResponse> otherCharityResponses;
}
