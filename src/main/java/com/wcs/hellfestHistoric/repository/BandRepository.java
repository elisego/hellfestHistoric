package com.wcs.hellfestHistoric.repository;

import com.wcs.hellfestHistoric.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    List<Band> findAllByName(String name);
}
