package com.bindenannim.Simple_blog_app.controller;

import com.bindenannim.Simple_blog_app.dto.BlogDto;
import com.bindenannim.Simple_blog_app.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
@Slf4j
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<BlogDto> createPost(@RequestBody BlogDto blogDto) {
        BlogDto savedBlog = blogService.createPost(blogDto);
        log.info("{Create post end point}");
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);

    }

    @GetMapping("/view")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<BlogDto>> getAllPendingApprovalPosts() {
        List<BlogDto> blogs = blogService.getAllPendingApprovalPosts();
        log.info("{This view all pending approval posts end pont}");
        return ResponseEntity.ok(blogs);
    }

    @PatchMapping("{id}/approve_posts")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BlogDto> approvedPosts(@PathVariable("id") Long id) {
        BlogDto updatedBlog = blogService.approvedPosts(id);
        log.info("{This is approve posts end point}");
        return ResponseEntity.ok(updatedBlog);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlogDto> updatePost(@RequestBody BlogDto blogDto, @PathVariable("id") Long id) {
        BlogDto updateBlog = blogService.updatePost(blogDto, id);
        log.info("{This is the updated posts end point}");
        return ResponseEntity.ok(updateBlog);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        blogService.deletePost(id);
        log.info("{this is delete post end point}");
        return ResponseEntity.ok("Blog deleted successfully");
    }

    @GetMapping("/all_approved")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<BlogDto>> getAllApprovedPosts() {
        List<BlogDto> blogList = blogService.getAllApprovedPosts();
        log.info("{This is all approve end point}");
        return ResponseEntity.ok(blogList);
    }
}
