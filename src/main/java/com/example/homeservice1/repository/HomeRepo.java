package com.example.homeservice1.repository;

import com.example.homeservice1.entity.Home;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface HomeRepo extends JpaRepository<Home, Long> {
    //List<Home> findByName(String name);
//    List findByUserId(Long userId);
    @EntityGraph(value = "home-entity-graph-with-rooms", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Home> findById(@NonNull Long aLong);
    @EntityGraph(value = "home-entity-graph-with-rooms", type = EntityGraph.EntityGraphType.LOAD)
    <S extends Home> S save(@NonNull S entity);


    @Query("SELECT h FROM Home h WHERE h.ownerid = :ownerid")
    List<Home> findByAllHouse(@Param("ownerid") String ownerid);

    @Query("SELECT h from Home h where (h.id = :id AND h.ownerid = :ownerid)")
    Optional<Home> findByHouseId(@Param("id") Long id, @Param("ownerid") String ownerid);
}