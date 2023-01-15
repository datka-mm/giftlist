package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserInfo, Long> {

}
