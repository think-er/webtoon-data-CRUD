package com.example.webtoon_print.service;

import com.example.webtoon_print.dto.SeriesDTO;
import com.example.webtoon_print.model.Series;
import com.example.webtoon_print.repository.SeriesIdAndTitle;
import com.example.webtoon_print.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public SeriesDTO getSeriesById(Long seriesId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("시리즈를 찾을 수 없습니다."));

        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setAbout(series.getAbout());
        seriesDTO.setTitle(series.getTitle());
        seriesDTO.setAge(series.getAge());
        seriesDTO.setGenre(series.getGenre());
        seriesDTO.setThumb(series.getThumbUrl());

        return seriesDTO;
    }


    public List<SeriesDTO> getAllSeries() {
        List<Series> seriesList = seriesRepository.findAll();
        List<SeriesDTO> seriesDTOList = new ArrayList<>();

        for (Series series : seriesList) {
            SeriesDTO seriesDTO = new SeriesDTO(
                    series.getId(),
                    series.getTitle(),
                    series.getAbout(),
                    series.getGenre(),
                    series.getAge(),
                    series.getThumbImg(),
                    series.getThumbImgType(),
                    series.getThumbUrl()
            );
            seriesDTOList.add(seriesDTO);
        }
        return seriesDTOList;
    }

    public SeriesDTO getFirstSeries() {
        Series series = seriesRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("시리즈를 찾을 수 없습니다."));
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setId(series.getId());
        return seriesDTO;
    }

    public SeriesDTO getSeriesThumbImgBySeriesId(Long id) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("시리즈를 찾을 수 없습니다."));
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setThumbImg(series.getThumbImg());
        seriesDTO.setThumbImgType(series.getThumbImgType());
        return seriesDTO;
    }

    public List<SeriesIdAndTitle> getAllSeriesIdAndTitle() {
//        return seriesRepository.findAllBy()  // SeriesIdOnly 리스트 조회
//                .stream()  // 스트림 변환
//                .map(SeriesIdOnly::getId)  // ID 값만 추출
//                .collect(Collectors.toList());  // List<Long>으로 변환
        return seriesRepository.findAllBy();
    }

    public List<Series> getSeriesTest() {
        return seriesRepository.findAll();
    }
}
