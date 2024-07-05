package com.bindenannim.Simple_blog_app.repository;

import com.bindenannim.Simple_blog_app.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByIsApproved(boolean isApproved);

}
