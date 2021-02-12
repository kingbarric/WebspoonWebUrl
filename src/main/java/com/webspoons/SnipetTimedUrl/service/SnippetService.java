package com.webspoons.SnipetTimedUrl.service;

import com.webspoons.SnipetTimedUrl.exception.CustomException;
import com.webspoons.SnipetTimedUrl.model.SnippetModel;
import com.webspoons.SnipetTimedUrl.pojo.ApiResponse;
import com.webspoons.SnipetTimedUrl.pojo.SnippetRequest;
import com.webspoons.SnipetTimedUrl.repository.SnippetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SnippetService {

    @Autowired
    private SnippetRepository snippetRepo;

    private final ModelMapper modelMapper;

    public SnippetService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public ApiResponse create(SnippetRequest snippetRequest) {
        try {
            Optional<SnippetModel> mSnippetModel1 = snippetRepo.findByName(snippetRequest.getName());
            if (mSnippetModel1.isPresent()){
                throw new CustomException("Name already Exists", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            SnippetModel snippetModel = modelMapper.map(snippetRequest, SnippetModel.class);
            snippetModel.setExpiryDate(snippetRequest.getExpiryDate());
            SnippetModel mSnippetModel = snippetRepo.save(snippetModel);
            ApiResponse apiResponse = modelMapper.map(mSnippetModel, ApiResponse.class);
            apiResponse.setExpiresAt(mSnippetModel.getExpiryDate());
            apiResponse.setUrl("http://localhost:8089/snippet/"+mSnippetModel.getName());
            return apiResponse;
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    public ApiResponse findByName(String name) {
        try {
            Optional<SnippetModel> snippetModel = snippetRepo.findByName(name);
            if (snippetModel.isPresent()) {
                if (snippetModel.get().isValid()) {
                    snippetModel.get().setExpiryDate(30);
                    SnippetModel updatedSnippet = snippetRepo.save(snippetModel.get());
                    ApiResponse apiResponse = modelMapper.map(updatedSnippet, ApiResponse.class);
                    apiResponse.setUrl("http://localhost:8089/snippet/"+snippetModel.get().getName());
                    apiResponse.setExpiresAt(updatedSnippet.getExpiryDate());
                    return apiResponse;
                } else {
                    throw new CustomException("Not Valid", HttpStatus.UNPROCESSABLE_ENTITY);
                }

            }else {
                throw new CustomException("Not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }


    }

    public ApiResponse edit( SnippetRequest snippetRequest) {
        try {
            Optional<SnippetModel> snippetModel1 = snippetRepo.findByName(snippetRequest.getName());
            if (snippetModel1.isPresent()){
                snippetModel1.get().setExpiryDate(snippetRequest.getExpiryDate());
                SnippetModel mSnippetModel = snippetRepo.save(snippetModel1.get());
                ApiResponse apiResponse = modelMapper.map(mSnippetModel, ApiResponse.class);
                apiResponse.setExpiresAt(mSnippetModel.getExpiryDate());
                apiResponse.setUrl("http://localhost:8089/snippet/"+mSnippetModel.getName());
                return apiResponse;
            } else {
                throw new CustomException("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
