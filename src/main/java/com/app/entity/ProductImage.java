package com.app.entity;

import com.app.entity.Product;
import com.app.entity.common.BaseEntity;

import javax.persistence.*;

@Entity
public class ProductImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imgPath;

    @OneToOne
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", imgPath='" + imgPath + '\'' +
                ", product=" + product +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
