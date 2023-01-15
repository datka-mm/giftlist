package com.example.giftlist.dto.response;

import com.example.giftlist.db.models.Charity;
import com.example.giftlist.db.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservoirResponse {

    private Long id;
    private String image;

    public ReservoirResponse(Charity charity) {
        if (charity.getReservoir() != null) {
            this.id = charity.getReservoir().getId();
            this.image = charity.getReservoir().getImage();
        }
        charity.setReservoir(new User("name"));
    }

}
