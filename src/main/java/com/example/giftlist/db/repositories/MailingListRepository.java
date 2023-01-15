package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.MailingList;
import com.example.giftlist.dto.response.AllMailingListResponse;
import com.example.giftlist.dto.response.MailingListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MailingListRepository extends JpaRepository<MailingList, Long> {

    @Query("select new com.example.giftlist.dto.response.MailingListResponse(" +
            "m.id," +
            "m.image," +
            "m.name," +
            "m.text," +
            "m.createdAt)from MailingList m where m.id = :id")
    Optional<MailingListResponse> findMailingById(Long id);

    @Query("select new com.example.giftlist.dto.response.AllMailingListResponse(" +
            "m.id," +
            "m.image," +
            "m.name," +
            "m.createdAt) from MailingList m ")
    List<AllMailingListResponse> findAllMailingList();
}