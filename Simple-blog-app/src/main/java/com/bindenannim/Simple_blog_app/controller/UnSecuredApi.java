package com.bindenannim.Simple_blog_app.controller;

import com.bindenannim.Simple_blog_app.dto.BlogDto;
import com.bindenannim.Simple_blog_app.service.BlogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/unsecure")
@AllArgsConstructor
@Slf4j
public class UnSecuredApi {
    private final BlogService blogService;

    @GetMapping("/view_all_posts")
    public ResponseEntity<List<BlogDto>> getAllPosts() {
        List<BlogDto> blogs = blogService.getAllPosts();
        log.info("{This unsecure end point that any user can access without auth}");
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("{id}/individual_post")
    public ResponseEntity<BlogDto> getPostById(@PathVariable("id") Long id) {

        BlogDto blogDto = blogService.getPostById(id);
        log.info("{End point for approving each post by Admin}");
        return new ResponseEntity<>(blogDto, HttpStatus.OK);
    }
}
