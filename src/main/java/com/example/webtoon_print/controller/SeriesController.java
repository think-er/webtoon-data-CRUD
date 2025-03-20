package com.example.webtoon_print.controller;

import com.example.webtoon_print.dto.SeriesDTO;
import com.example.webtoon_print.model.Series;
import com.example.webtoon_print.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SeriesController {

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/series/{id}")
    @ResponseBody
    public SeriesDTO getSeriesById(@PathVariable("id") Long id) {
        return seriesService.getSeriesById(id);
    }

//    @GetMapping("/today")
//    @ResponseBody
//    public List<SeriesDTO> getAllSeries() {
//        return seriesService.getAllSeries();
//    }

    @GetMapping("/series/{id}/thumb")
    public ResponseEntity<byte[]> getSeriesThumbImg(@PathVariable("id") Long id) {
        SeriesDTO seriesDTO = seriesService.getSeriesThumbImgBySeriesId(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", seriesDTO.getThumbImgType());

        return ResponseEntity.ok()
                .headers(headers)
                .body(seriesDTO.getThumbImg());
    }

    @GetMapping("/series-list")
    @ResponseBody
    public List<SeriesDTO> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("/series")  // 이 URL에서 HTML을 렌더링
    public String getSeriesPage() {
        return "series";  // series.html 파일을 렌더링
    }

    @GetMapping("/test1")
    @ResponseBody
    public List<Series> getTest1() {
        return seriesService.getSeriesTest();
    }

}
