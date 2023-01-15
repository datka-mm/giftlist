package com.example.giftlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllMailingListResponse {

    private Long id;
    private String image;
    private String name;
    private LocalDateTime createdAt;
}