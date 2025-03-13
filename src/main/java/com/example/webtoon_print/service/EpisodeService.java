package com.example.webtoon_print.service;

import com.example.webtoon_print.dto.EpisodeDTO;
import com.example.webtoon_print.model.Episode;
import com.example.webtoon_print.repository.EpisodeRepository;
import com.example.webtoon_print.repository.EpisodeThumbImgProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    // id를 통해 episodes 조회   
    public List<EpisodeDTO> getAllEpisodesBySeriesId(Long seriesId) {
        List<Episode> episodes = episodeRepository.findAllBySeriesId(seriesId);

        List<EpisodeDTO> episodeDTOs = new ArrayList<>();

        for (Episode episode : episodes) {
            // 3. EpisodeDTO 생성 (필요한 데이터만 넘겨줌)
            EpisodeDTO episodeDTO = new EpisodeDTO(
                    episode.getId(),
                    episode.getTitle(),
                    episode.getRating(),
                    episode.getDate(),
                    episode.getThumbImg(),
                    episode.getThumbImgType(),
                    episode.getThumbUrl()
            );
            episodeDTOs.add(episodeDTO);
        }

        return episodeDTOs;
    }

    public EpisodeDTO getThumbImgById(Long id, Long seriesId) {
        EpisodeThumbImgProjection episode = episodeRepository.findThumbImgByIdAndSeriesId(id, seriesId)
                .orElseThrow(() -> new RuntimeException("썸네일을 찾을 수 없습니다."));
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setThumbImg(episode.getThumbImg());
        episodeDTO.setThumbImgType(episode.getThumbImgType());
        return episodeDTO;
    }

}
