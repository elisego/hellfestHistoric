package com.wcs.hellfestHistoric.repository;

import com.wcs.hellfestHistoric.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {

    List<Concert> findConcertByBandId(Long id);
}
