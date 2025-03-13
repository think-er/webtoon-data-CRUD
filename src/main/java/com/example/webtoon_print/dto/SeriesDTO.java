package com.example.webtoon_print.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeriesDTO {
    private Long id;
    private String title;
    private String about;
    private String genre;
    private String age;
    private byte[] thumbImg;
    private String thumbImgType;
    private String thumb;

    public String getId() {
        return id != null ? String.valueOf(id) : null;  // Long -> String 변환
    }
}
