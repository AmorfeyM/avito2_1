package com.amr.project.converter;

import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.entity.Image;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImageMapper {

    ImageDto toDto(Image image);

    //TODO возврат моделей нужно реализовать @Mapping если нужен
    Image toModel(ImageDto imageDto);
}
