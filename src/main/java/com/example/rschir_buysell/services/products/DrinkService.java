package com.example.rschir_buysell.services.products;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.Image;
import com.example.rschir_buysell.models.enums.ProductType;
import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.models.products.Drink;
import com.example.rschir_buysell.repositories.ClientRepository;
import com.example.rschir_buysell.repositories.ImageRepository;
import com.example.rschir_buysell.repositories.products.DrinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final ClientRepository clientRepository;
    private final ImageRepository imageRepository;

    public Client getClientByPrincipal(Principal principal) {
        if (principal == null) return new Client();
        return clientRepository.findByEmail(principal.getName());
    }

    private String validation(Drink drink) {
        if (
                drink.getPrice() != null &&
                        drink.getPrice() > 0 &&
                        drink.getName() != null &&
                        !drink.getName().isEmpty()
                        // local variables
        ) return "Success";
        else {
            if (drink.getPrice() == null) {
                return "Укажите цену!";
            } else if (drink.getPrice() <= 0) {
                return "Укажите корректную цену!";
            } else if (drink.getName() == null || drink.getName().isEmpty()) {
                return "Напишите название блюда!";
            }
        }
        return "Error";
    }

    public String createDrink(Principal principal, Drink drink, MultipartFile file1) throws IOException {
        drink.setProductType(ProductType.Drink);

        String validation = validation(drink);
        if (validation.equals("Success")) {
            Image image1;
            if (file1.getSize() != 0) {
                image1 = toImageEntity(file1);
                image1.setPreviewImage(true);
                imageRepository.save(image1);
                drink.setPreviewImageId(image1.getId());
                drink.addImageToProduct(image1);
            }
            drinkRepository.save(drink);
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

    public Drink getDrinkById(Long id) {
        return drinkRepository.getById(id);
    }

    public String updateDrink(Long id, Drink drink, MultipartFile file1) throws IOException {
        String validation = validation(drink);
        if (validation.equals("Success")) {
            Drink original = drinkRepository.getById(id);

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

            original.setName(drink.getName());
            original.setPrice(drink.getPrice());
            original.setQuantity(drink.getQuantity());
            // local variables

            drinkRepository.save(original);
        }
        return validation;
    }

    public void deleteDrink(Long id) {
        Drink drink = drinkRepository.getById(id);
        drink.setQuantity(0);
        drinkRepository.save(drink);
    }
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Page<Drink> getDrinksByName(String name, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return drinkRepository.findByNameLike("%" + name + "%", pageable);
        } else {
            return drinkRepository.findAll(pageable);
        }
    }
}
