package com.example.mongobookstore.Mapper.Implementation;

import com.example.mongobookstore.Entity.CreateUpdateAuthorRequest;
import com.example.mongobookstore.Entity.DTOs.CreateUpdateAuthorRequestDto;
import com.example.mongobookstore.Mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorCreateUpdateRequestMapper implements Mapper<CreateUpdateAuthorRequest, CreateUpdateAuthorRequestDto> {
    @Override
    public CreateUpdateAuthorRequestDto mapTo(CreateUpdateAuthorRequest createUpdateAuthorRequest) {
        return CreateUpdateAuthorRequestDto.builder()
                .name(createUpdateAuthorRequest.getName())
                .build();
    }

    @Override
    public CreateUpdateAuthorRequest mapFrom(CreateUpdateAuthorRequestDto createUpdateAuthorRequestDto) {
        return CreateUpdateAuthorRequest.builder()
                .name(createUpdateAuthorRequestDto.getName())
                .build();
    }
}
