package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.Gift;
import com.example.giftlist.dto.response.GiftResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

    void deleteByWishId(Long id);

    @Query("select new com.example.giftlist.dto.response.GiftResponse(g) from Gift g join g.wish w join g.user u where u.email =?1 ")
    List<GiftResponse> getAllGifts(String email);

    @Query("select new com.example.giftlist.dto.response.GiftResponse(ch) from  Charity ch where ch.reservoir.email=?1")
    List<GiftResponse> getAllReservedCharity(String email);

    @Modifying
    @Query("delete from Gift w where w.id = :id")
    void deleteGiftById(Long id);
}