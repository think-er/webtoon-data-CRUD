package com.example.webtoon_print.config;

import com.example.webtoon_print.model.Episode;
import com.example.webtoon_print.service.EpisodeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EpisodeThumbInitializer {

    private final EpisodeService episodeService;

    @PostConstruct
    public void init() {
        System.out.println("서버 시작: 에피소드 썸네일 초기화 중...");
        String dirPath = "src/main/resources/static/images/episode_thumb/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 진짜 초기 파일 생성에만 필요한 함수, 만약 값을 추가하려고하는데 디렉토리에 이미있다고
        // 빠꾸먹여버리면 말도안되는 코드;
        if(dir.exists() &&  dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files.length > 0) {
                System.out.println("✅ 디렉토리 내에 이미 파일이 있습니다.");
                return; // 디렉토리에 파일이 있으면 바로 종료
            }
        }

        List<Episode> episodes = episodeService.getAllEpisodes(); // 모든 에피소드 조회

        for (Episode episode : episodes) {
            saveEpisodeThumbToFile(episode, dirPath);
        }

        System.out.println("✅ 썸네일 초기화 완료!");
    }

    private void saveEpisodeThumbToFile(Episode episode, String directoryPath) {

        String thumbImgType = switch (episode.getThumbImgType()) {
            case "image/jpg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/gif" -> ".gif";
            default -> "";
        };

        String filePath = directoryPath + episode.getId() + thumbImgType;
        File file = new File(filePath);

        // 이미 저장된 파일이 있으면 다시 저장하지 않음
        if (file.exists()) {
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(episode.getThumbImg()); // BLOB 데이터를 파일로 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
