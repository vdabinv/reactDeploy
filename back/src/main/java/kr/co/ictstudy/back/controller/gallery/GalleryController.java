package kr.co.ictstudy.back.controller.gallery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {
    @Autowired
    private GalleryService galleryService;
    // 파일 업로드 되는 경로를 react로 잡아야 한다.
    private final static String imageDirectory = Paths.get("").toAbsolutePath().normalize().toString()
            + "/../client/public/upimg/";

    public GalleryController() {
        System.out.println("imagePath=>" + imageDirectory);
        File file = new File(imageDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    // @GetMapping
    // public List<Gallery> getAllGalleries() {
    // return galleryService.getAllGalleries();
    // }

    // @GetMapping
    // public List<Gallery> getAllGalleries(@RequestParam(defaultValue = "1") int
    // page,
    // @RequestParam(defaultValue = "10") int size,
    // @RequestParam(defaultValue = "") String title) {
    // return galleryService.getAllGalleries(title, page, size);
    // }
    @GetMapping
    public Page<Gallery> getAllGalleries(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "9") int size,
            @RequestParam(name = "title", defaultValue = "") String title) {
        return galleryService.getAllGalleries(title, page, size);
    }

    private String getExtension(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if (index > -1) { // .이 존재 할 경우
            return fileName.substring(index);
        }
        return "";
    }

    @PostMapping
    public Gallery createGallery(@ModelAttribute GalleryVO galleryVO) throws IOException {
        List<String> imageNames = new ArrayList<>();
        for (MultipartFile multipartFile : galleryVO.getImages()) {
            String newFileName = UUID.randomUUID() + getExtension(multipartFile);
            String filePath = imageDirectory + newFileName;
            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(multipartFile.getBytes());
                imageNames.add(newFileName);
            } catch (Exception e) {
                throw new RuntimeException("Fail to upload files.");
            }
        }
        galleryVO.setImgNames(imageNames);
        return galleryService.createGallery(galleryVO);
    }

    @GetMapping("/{id}")
    public Gallery getGalleryById(@PathVariable Long id) {
        Optional<Gallery> gallery = galleryService.getGalleryById(id);
        if (gallery.isPresent()) {
            return gallery.get();
        } else {
            throw new RuntimeException("Gallery not found with id: " + id);
        }
    }

}
