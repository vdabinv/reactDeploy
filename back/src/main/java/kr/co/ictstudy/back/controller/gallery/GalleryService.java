package kr.co.ictstudy.back.controller.gallery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    // public List<Gallery> getAllGalleries() {
    // return galleryRepository.findAllByOrderByIdDesc();
    // }
    // 여기는 순수하게 페이지 쿼리 테스트 용이고
    // http://192.168.0.42/myapp0716/api/gallery?page=1&size=10&title=해피
    // 테스트 해보면 된다 원래는 검색에 대한 total이 필요하다
    // public List<Gallery> getAllGalleries(String title, int page, int size) {
    // // 페이지 번호가 1부터 시작하므로 1을 감소시킴
    // int startRow = (page - 1) * size + 1;
    // int endRow = (page + 1) * size;
    // return galleryRepository.findByTitleContainingOrderByGdateDesc(title,
    // startRow, endRow);
    // }
    // 검색에 대한 total 포함 한 서비스
    public Page<Gallery> getAllGalleries(String title, int page, int size) {
        // 페이지 번호와 크기를 기준으로 시작 행과 끝 행 번호를 계산
        int startRow = (page - 1) * size + 1;
        int endRow = page * size;
        System.out.println("startRow:" + startRow + ": Page" + page);
        // 페이징된 쿼리의 결과를 받은 리스트
        List<Gallery> galleries = galleryRepository.findByTitleContainingOrderByIdDesc(title, startRow, endRow);
        // 이건 토탈 값 - title 검색 포함
        int totalElements = galleryRepository.countByTitleContaining(title);
        // Page구현 객체인 PageImpl을 생성해서
        // 페이징된 결과 리스트를 반환 바다가지고
        // 주며는 되는데 이때
        // Spring Data JPA의 PageRequest는 페이지 번호가 0부터 시작 하니까
        // -1 을 빼줘야 한다.
        /*
         * 사용자 입력 페이지 번호: 사용자는 1페이지, 2페이지 등의 번호를 입력합니다. 즉, 1부터 시작합니다.
         * Spring Data JPA의 PageRequest: 페이지 번호가 0부터 시작합니다. 따라서 페이지 번호를 맞추기 위해 1을 빼줘야
         * 합니다.
         * 왜 page - 1을 사용하는지?
         * 사용자 입력: 페이지 번호가 1부터 시작합니다.
         * 내부 처리: Spring Data JPA는 페이지 번호를 0부터 시작합니다.
         * 따라서 page - 1을 통해 이를 맞춥니다.
         * 사용자가 1페이지를 요청하면:
         * 
         * page는 1입니다.
         * startRow는 (1 - 1) * size + 1 = 1입니다.
         * endRow는 1 * size = size입니다.
         * PageRequest.of(page - 1, size)는 PageRequest.of(0, size)입니다.
         * 사용자가 2페이지를 요청하면:
         * 
         * page는 2입니다.
         * startRow는 (2 - 1) * size + 1 = size + 1입니다.
         * endRow는 2 * size = 2 * size입니다.
         * PageRequest.of(page - 1, size)는 PageRequest.of(1, size)입니다.
         */
        return new PageImpl<>(galleries, PageRequest.of(page - 1, size), totalElements);
    }

    public Optional<Gallery> getGalleryById(Long id) {
        return galleryRepository.findById(id);
    }

    public Gallery createGallery(GalleryVO galleryVO) throws IOException {
        Gallery gallery = new Gallery();
        gallery.setWriter(galleryVO.getWriter());
        gallery.setTitle(galleryVO.getTitle());
        gallery.setDescription(galleryVO.getDescription());
        gallery.setGdate(new java.util.Date());
        gallery.setImageNames(galleryVO.getImgNames());
        return galleryRepository.save(gallery);
    }
}
