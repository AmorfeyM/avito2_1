package com.amr.project.converter;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ShopMapperTest {
    
    private ShopMapper shopMapper;
    private Shop shop;
    private ShopDto shopDto;
    private List<Shop> shopList = new ArrayList<>();
    private List<ShopDto> shopDtoList = new ArrayList<>();

    @Autowired
    public ShopMapperTest(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }
    @BeforeEach
    public void setup() {
        shopDto = ShopDto.builder()
                .id(1L)
                .description("description")
                .name("Vasya")
                .email("tets@mail.com")
                .build();
        shop = Shop.builder()
                .id(1L)
                .description("description")
                .name("Vasya")
                .email("tets@mail.com")
                .build();
        shopList.add(shop);
        shopDtoList.add(shopDto);

    }

    @Test
    void toModel() {

        Shop entity = shopMapper.toModel(shopDto);

        assertEquals(shopDto.getId(), entity.getId());
        assertEquals(shopDto.getDescription(), entity.getDescription());
        assertEquals(shopDto.getName(), entity.getName());
        assertEquals(shopDto.getEmail(), entity.getEmail());
    }

    @Test
    void toDto() {
        ShopDto entity = shopMapper.toDto(shop);

        assertEquals(shop.getId(), entity.getId());
        assertEquals(shop.getDescription(), entity.getDescription());
        assertEquals(shop.getName(), entity.getName());
        assertEquals(shop.getEmail(), entity.getEmail());
    }


    @Test
    void toDtoList() {
        List<ShopDto> entityList = shopMapper.toDtoList(shopList);
        assertEquals(shopList.get(0).getEmail(), entityList.get(0).getEmail());

    }

    @Test
    void toModelList() {
        List<Shop> entityList = shopMapper.toModelList(shopDtoList);
        assertEquals(shopDtoList.get(0).getEmail(), entityList.get(0).getEmail());
    }
}