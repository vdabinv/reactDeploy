package kr.co.ictstudy.back.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {
    private Long id;
    private String name;
    private String context;
    private Double price;
    private Date createdAt;
    private Date updatedAt;
    private Double discount;
    private Double rating;

    @Override
    public String toString() {
        return "ProductVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", discount=" + discount +
                ", rating=" + rating +
                '}';
    }
}
