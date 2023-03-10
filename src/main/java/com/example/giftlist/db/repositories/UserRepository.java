package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.User;
import com.example.giftlist.dto.response.SearchUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(String email);
    @Query("select case when count(u)>0 then true else false end from User u where u.email like :email")
    boolean existsByEmail(@Param(value = "email") String email);

    @Query("select u from User u where u.role = 'USER' ")
    List<User> getAll();

    @Query("select new com.example.giftlist.dto.response.SearchUserResponse(" +
            "u.id," +
            "u.image," +
            "concat(u.firstName,' ',u.lastName)) from User u where" +
            " upper(u.firstName) like concat(:text,'%') or " +
            "upper(u.lastName) like concat(:text,'%')")
    List<SearchUserResponse> search(@Param("text") String text);
}