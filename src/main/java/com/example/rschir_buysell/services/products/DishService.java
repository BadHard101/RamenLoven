package com.example.rschir_buysell.services.products;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.Image;
import com.example.rschir_buysell.models.enums.ProductType;
import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.models.products.Drink;
import com.example.rschir_buysell.repositories.ClientRepository;
import com.example.rschir_buysell.repositories.ImageRepository;
import com.example.rschir_buysell.repositories.products.DishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final ClientRepository clientRepository;
    private final ImageRepository imageRepository;

    public Client getClientByPrincipal(Principal principal) {
        if (principal == null) return new Client();
        return clientRepository.findByEmail(principal.getName());
    }

    private String validation(Dish dish) {
        if (
                dish.getPrice() != null &&
                        dish.getPrice() > 0 &&
                        dish.getName() != null &&
                        !dish.getName().isEmpty()
                        // local variables
        ) return "Success";
        else {
            if (dish.getPrice() == null) {
                return "Укажите цену!";
            } else if (dish.getPrice() <= 0) {
                return "Укажите корректную цену!";
            } else if (dish.getName() == null || dish.getName().isEmpty()) {
                return "Напишите название блюда!";
            }
        }
        return "Error";
    }

    public String createDish(Principal principal, Dish dish, MultipartFile file1) throws IOException {
        dish.setProductType(ProductType.Dish);

        String validation = validation(dish);
        if (validation.equals("Success")) {
            Image image1;
            if (file1.getSize() != 0) {
                image1 = toImageEntity(file1);
                image1.setPreviewImage(true);
                imageRepository.save(image1);
                dish.setPreviewImageId(image1.getId());
                dish.addImageToProduct(image1);
            }
            dishRepository.save(dish);
        }
        return validation;
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public Dish getDishById(Long id) {
        return dishRepository.getById(id);
    }

    public String updateDish(Long id, Dish dish, MultipartFile file1) throws IOException {
        String validation = validation(dish);
        if (validation.equals("Success")) {
            Dish original = dishRepository.getById(id);

            Image image1;
            if (file1.getSize() != 0) {
                if (original.hasPreview()) {
                    imageRepository.deleteById(original.getPreviewImageId());
                }
                image1 = toImageEntity(file1);
                image1.setPreviewImage(true);
                imageRepository.save(image1);
                original.setPreviewImageId(image1.getId());
                original.addImageToProduct(image1);
            }

            original.setName(dish.getName());
            original.setPrice(dish.getPrice());
            original.setQuantity(dish.getQuantity());
            // local variables

            dishRepository.save(original);
        }
        return validation;
    }

    public void deleteDish(Long id) {
        Dish dish = dishRepository.getById(id);
        dish.setQuantity(0);
        dishRepository.save(dish);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Page<Dish> getDishesByName(String name, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return dishRepository.findByNameLike("%" + name + "%", pageable);
        } else {
            return dishRepository.findAll(pageable);
        }
    }
}
