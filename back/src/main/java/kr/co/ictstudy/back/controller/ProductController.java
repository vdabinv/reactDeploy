package kr.co.ictstudy.back.controller;

import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import kr.co.ictstudy.back.vo.ProductVO;

@RestController
public class ProductController {
    // http://192.168.0.42/myapp0719/product
    @GetMapping("/product")
    public ProductVO getProduct() {
        ProductVO product = new ProductVO();
        product.setId(1L);
        product.setName("겔럭시 폰");
        product.setContext("이것은 겔럭시 폰이다.");
        product.setPrice(99.99);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setDiscount(10.5);
        product.setRating(4.7);
        return product;
    }

    @PostMapping("/product")
    public ProductVO createProduct(@RequestBody ProductVO product) {
        System.out.println("호출!");
        product.setId(1L); // 실제로는 DB에서 자동 생성되도록 설정해야 합니다.
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        System.out.println(product);
        return product;
    }
}