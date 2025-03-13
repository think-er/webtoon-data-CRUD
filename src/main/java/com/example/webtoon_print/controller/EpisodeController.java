package com.example.webtoon_print.controller;

import com.example.webtoon_print.dto.EpisodeDTO;
import com.example.webtoon_print.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EpisodeController {

    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/episode/{seriesId}")
    public String getEpisodesBySeriesId(@PathVariable("seriesId") Long seriesId,
                                                  Model model) {
        List<EpisodeDTO> episodeDTOS = episodeService.getAllEpisodesBySeriesId(seriesId);
        model.addAttribute("episodeList", episodeDTOS);
        return "episode";
    }

    @GetMapping("/episode/{seriesId}/thumb/{episodeId}")
    public ResponseEntity<byte[]> getEpisodeThumbImg(@PathVariable("seriesId") Long seriesId,
                                                     @PathVariable("episodeId") Long episodeId) {
        // EpisodeDTO에서 썸네일 데이터 가져오기
        EpisodeDTO episodeDTO = episodeService.getThumbImgById(episodeId, seriesId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", episodeDTO.getThumbImgType());  // 썸네일 이미지의 MIME 타입

            // 썸네일 이미지를 바이트 배열로 반환
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(episodeDTO.getThumbImg());
    }
}
