package com.teamlab.api.repositories;

import com.teamlab.api.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAll();

    Optional<Item> findById(long id);

    List<Item> findByTitleContainingOrderById(@Param("title") String title);
}
