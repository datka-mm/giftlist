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
public class InnerFeedResponse {

    private Long wishId;
    private SearchUserResponse saveUser;
    private HolidayResponse holidayResponse;
    private String image;
    private String wishName;
    private Status status;
    private String description;
    private UserFeedResponse reservoirUser;

    public InnerFeedResponse(Wish wish) {
        this.wishId = wish.getId();
        if (wish.getHoliday() != null){
        this.holidayResponse = new HolidayResponse(wish.getHoliday().getId(),wish.getHoliday().getName(), wish.getHoliday().getDateOfHoliday());
        }
        else {
            this.holidayResponse = new HolidayResponse();
        }
        this.saveUser = new SearchUserResponse(wish.getUser().getId(), wish.getImage(), wish.getUser().getFirstName()+" "+wish.getUser().getLastName());
        this.image = wish.getImage();
        this.wishName = wish.getWishName();
        this.status = wish.getWishStatus();
        this.description = wish.getDescription();
        if (wish.getReservoir() == null) {
            this.reservoirUser = new UserFeedResponse();
        } else {
            this.reservoirUser = new UserFeedResponse(wish.getReservoir().getId(),wish.getReservoir().getImage());
        }
    }
}
