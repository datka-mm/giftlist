package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.Complaint;
import com.example.giftlist.dto.response.ComplaintResponseForAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("select new com.example.giftlist.dto.response.ComplaintResponseForAdmin(" +
            "c.id," +
            "c.wish.user.id," +
            "c.wish.id," +
            "c.wish.user.image," +
            "c.wish.user.userInfo.phoneNumber," +
            "c.wish.user.firstName," +
            "c.wish.user.lastName," +
            "c.wish.holiday.name," +
            "c.wish.wishName," +
            "c.wish.image," +
            "c.createdAt," +
            "c.complainer.id," +
            "c.complainer.image," +
            "c.wish.isBlock," +
            "c.reasonText) from Complaint c")
    List<ComplaintResponseForAdmin> getAllComplaints();

    @Modifying
    @Transactional
    @Query("delete from Complaint w where w.id = :id")
    void deleteComplaintById(Long id);

}