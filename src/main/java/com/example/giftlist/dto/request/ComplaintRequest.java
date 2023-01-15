package com.example.giftlist.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {

    private String complaintText;
    private Long wishId;
}