package com.example.webtoon_print.repository;

import com.example.webtoon_print.model.Episode;
import com.example.webtoon_print.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findAllBySeriesId(Long seriesId);
    Optional<EpisodeThumbImgProjection> findThumbImgByIdAndSeriesId(Long episodeId,
                                                                    Long seriesId);

    Long series(Series series);
}
