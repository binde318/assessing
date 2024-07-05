package com.bindenannim.Simple_blog_app.service;

import com.bindenannim.Simple_blog_app.dto.BlogDto;

import java.util.List;

public interface BlogService {
    BlogDto createPost(BlogDto blogDto);
    List<BlogDto>getAllPosts();
    List<BlogDto>getAllApprovedPosts();
    BlogDto getPostById(Long id);
    BlogDto updatePost(BlogDto blogDto, Long id);
    void deletePost(Long id);
    List<BlogDto>getAllPendingApprovalPosts();
    BlogDto approvedPosts(Long id);
}
