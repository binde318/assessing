package com.bindenannim.Simple_blog_app.service.impl;

import com.bindenannim.Simple_blog_app.dto.BlogDto;
import com.bindenannim.Simple_blog_app.entity.Blog;
import com.bindenannim.Simple_blog_app.exception.ResourceNotFoundException;
import com.bindenannim.Simple_blog_app.repository.BlogRepository;
import com.bindenannim.Simple_blog_app.service.BlogService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@AllArgsConstructor
public class BlogServiceImplementation implements BlogService {
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Override
    public BlogDto createPost(BlogDto blogDto) {

        Blog blog = modelMapper.map(blogDto, Blog.class);
        Blog saved = blogRepository.save(blog);
        BlogDto savedBlog = modelMapper.map(saved, BlogDto.class);
        return savedBlog;
    }

    @Override
    public List<BlogDto> getAllPosts() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<BlogDto> getAllApprovedPosts() {
        List<Blog> blogs = blogRepository.findByIsApproved(true);
        return blogs.stream()
                .map((blog) -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BlogDto getPostById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with the id is not found " + id));

        return modelMapper.map(blog, BlogDto.class);
    }

    @Override
    public BlogDto updatePost(BlogDto blogDto, Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with the id is not found " + id));
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setAuthor(blogDto.getAuthor());
        blog.setApproved(blogDto.isApproved());
        blog.setEmployee(blog.isEmployee());

        Blog blogUpdated = blogRepository.save(blog);
        return modelMapper.map(blogUpdated, BlogDto.class);
    }

    @Override
    public void deletePost(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The post is not found " + id));
        blogRepository.deleteById(id);


    }

    @Override
    public List<BlogDto> getAllPendingApprovalPosts() {
        List<Blog> blogs = blogRepository.findByIsApproved(false);
        return blogs.stream().map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BlogDto approvedPosts(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with the id is not found" + id));
        blog.setApproved(Boolean.TRUE);
        blog.setEmployee(Boolean.TRUE);
        Blog updateBlog = blogRepository.save(blog);
        return modelMapper.map(updateBlog, BlogDto.class);
    }

}
