package com.example.webtoon_print.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "series")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String about;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String age;

    private byte[] thumbImg;

    private String thumbImgType;

    private String thumbUrl;
    // One Series can have multiple Episodes
    // 한 개의 시리즈(Series) 엔티티가 여러 개의 에피소드(Episode) 엔티티를 가질 수 있다
    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Episode> episodes;
}
