package com.example.giftlist.dto.response;

import com.example.giftlist.db.models.Wish;
import com.example.giftlist.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishResponse {

    private Long id;
    private String wishName;
    private String image;
    private HolidayResponse holiday;
    private Boolean isBlock;
    private Status wishStatus;
    private Boolean isMy;

    public WishResponse(Wish wish) {
        this.id = wish.getId();
        this.wishName = wish.getWishName();
        this.image = wish.getImage();
        this.isBlock = wish.getIsBlock();
        this.wishStatus = wish.getWishStatus();
        if (wish.getHoliday() != null) {
            this.holiday = new HolidayResponse(wish.getHoliday().getId(), wish.getHoliday().getName(),
                    wish.getHoliday().getDateOfHoliday());
        } else {
            this.holiday = new HolidayResponse();
        }
    }
}