package com.example.rschir_buysell.models.products;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.Image;
import com.example.rschir_buysell.models.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "product")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;

    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image);
    }

    public boolean hasPreview() {
        return previewImageId != null;
    }

    public abstract String toControllerProductType();
}