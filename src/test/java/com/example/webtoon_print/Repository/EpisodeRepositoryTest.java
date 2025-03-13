package com.example.webtoon_print.Repository;

import com.example.webtoon_print.model.Episode;
import com.example.webtoon_print.repository.EpisodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EpisodeRepositoryTest {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Test
    @DisplayName("Episodes 조회 테스트")
    void 에피소드_조회() {
        // When (조회)
        List<Episode> episodeList = episodeRepository.findAllBySeriesId(21815L); // 모든 Series 데이터 조회

        // Then (검증)
        assertThat(episodeList).isNotEmpty();  // Series 데이터가 비어있지 않으면 성공

        for(Episode episode : episodeList) {
//            System.out.println(Arrays.toString(episode.getThumbImg()));
            System.out.println(episode.getTitle());
        }
    }

}