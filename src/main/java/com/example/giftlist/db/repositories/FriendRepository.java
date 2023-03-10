package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.User;
import com.example.giftlist.dto.response.FriendInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<User,Long> {

    @Query("select new com.example.giftlist.dto.response.FriendInfoResponse( " +
            "f.id," +
            "f.image," +
            "concat(f.firstName,' ',f.lastName)," +
            "f.holidays.size" +
            ",f.wishes.size) from User u join u.friends f where u.email =?1")
    List<FriendInfoResponse> getAllFriends(String email);

    @Query("select new com.example.giftlist.dto.response.FriendInfoResponse( " +
            "f.id," +
            "f.image," +
            "concat(f.firstName,' ',f.lastName)," +
            "f.holidays.size" +
            ",f.wishes.size) from User u join u.requests f where u.email =?1")
    List<FriendInfoResponse>getAllRequests(String email);

}
