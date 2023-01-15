package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.Wish;
import com.example.giftlist.dto.response.BookResponse;
import com.example.giftlist.dto.response.WishResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("select new com.example.giftlist.dto.response.BookResponse(w) from Wish w where w.user.email = ?1 and w.wishStatus = 'RESERVED'")
    List<BookResponse> getALlReservoirWishes(String email);

    @Query("select new com.example.giftlist.dto.response.WishResponse (w) from Wish w where w.user.email=?1")
    List<WishResponse> getAllWish(String email);

    @Query("select w from Wish w where w.user.isBlock = false and w.user.email <> ?1")
    List<Wish> getAllWishes(String email);

    @Query("select w from Wish w where w.user.isBlock = false and w.id = :id")
    Optional<Wish> findWishById(Long id);

    @Modifying
    @Query("delete from Wish w where w.id = :id")
    void deleteById(Long id);


}
