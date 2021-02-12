package com.webspoons.SnipetTimedUrl.controller;

import com.webspoons.SnipetTimedUrl.model.SnippetModel;
import com.webspoons.SnipetTimedUrl.pojo.ApiResponse;
import com.webspoons.SnipetTimedUrl.pojo.SnippetRequest;
import com.webspoons.SnipetTimedUrl.service.SnippetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SnippetController {

    @Autowired
    private SnippetService snippetService;

    //Create Snippet Api
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createSnippet(@RequestBody SnippetRequest snippetRequest) {

        return ResponseEntity.ok(snippetService.create(snippetRequest));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(snippetService.findByName(name));
    }

    @PutMapping("/edit")
    public ResponseEntity<ApiResponse> edit(@RequestBody SnippetRequest snippetRequest) {
        return ResponseEntity.ok(snippetService.edit(snippetRequest));
    }
}
