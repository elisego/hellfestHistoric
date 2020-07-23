package com.wcs.hellfestHistoric.repository;

import com.wcs.hellfestHistoric.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    Optional<Band> findByName(String name);

    List<Band> findAllByName(String name);

    @Query(value = "SELECT band.id FROM band WHERE name like '%:search%')",
            nativeQuery = true)
    List<Long> finAllBySearchName(@Param("search") String search);

}
