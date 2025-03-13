package com.example.webtoon_print.Repository;

import com.example.webtoon_print.model.Series;
import com.example.webtoon_print.repository.EpisodeRepository;
import com.example.webtoon_print.repository.SeriesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SeriesRepositoryTest {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Test
    @DisplayName("Series 조회 테스트")
    void 모든_시리즈_조회() {
        // When (조회)
        List<Series> seriesList = seriesRepository.findAll(); // 모든 Series 데이터 조회

        // Then (검증)
        assertThat(seriesList).isNotEmpty();  // Series 데이터가 비어있지 않으면 성공
    }

//    @Test
//    void 모든_시리즈_id_조회() {
//        List<SeriesIdOnly> seriesList = seriesRepository.findAllBy();
//
//        assertThat(seriesList).isNotEmpty();
//
//        System.out.println(seriesList.get(0).getId());
//        System.out.println(seriesList.get(1).getId());
//
//    }
}