package com.example.webtoon_print.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "episode")
public class Episode {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // 여러 개의 Episode가 한 개의 Series를 가질 수 있다.
        @ManyToOne
        @JoinColumn(name = "series_id", nullable = false)
        private Series series; // FK (외래키)

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private float rating;

        @Column(nullable = false)
        private String date;

        private byte[] thumbImg;

        private String thumbImgType;

        private String thumbUrl;
}
