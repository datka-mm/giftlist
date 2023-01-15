package com.example.giftlist.db.repositories;

import com.example.giftlist.db.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.name = :name")
    Category findByName(String name);

    @Query("select c.name from Category c where c.name=:name")
    String getByName(String name);
}