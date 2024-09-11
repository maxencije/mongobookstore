package com.example.mongobookstore.Mapper.Implementation;

import com.example.mongobookstore.Entity.CreateUpdateBookRequest;
import com.example.mongobookstore.Entity.DTOs.CreateUpdateBookRequestDto;
import com.example.mongobookstore.Mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class BookCreateUpdateRequestMapper implements Mapper<CreateUpdateBookRequest, CreateUpdateBookRequestDto> {
    @Override
    public CreateUpdateBookRequestDto mapTo(CreateUpdateBookRequest createUpdateBookRequest) {
        return CreateUpdateBookRequestDto.builder()
                .title(createUpdateBookRequest.getTitle())
                .authorId(createUpdateBookRequest.getAuthorId())
                .build();
    }

    @Override
    public CreateUpdateBookRequest mapFrom(CreateUpdateBookRequestDto createUpdateBookRequestDto) {
        return CreateUpdateBookRequest.builder()
                .title(createUpdateBookRequestDto.getTitle())
                .authorId(createUpdateBookRequestDto.getAuthorId())
                .build();
    }
}
