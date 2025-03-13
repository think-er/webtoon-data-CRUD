package com.example.webtoon_print.repository;

import com.example.webtoon_print.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findFirstByOrderByIdAsc();
    List<SeriesIdAndTitle> findAllBy();
}
