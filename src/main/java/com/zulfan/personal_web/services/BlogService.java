package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.BlogSummaryDto;
import com.zulfan.personal_web.entities.Blog;
import com.zulfan.personal_web.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog saveBlog(Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdateAt(LocalDateTime.now());
        blog.getSubheadingList().forEach(s -> s.setBlog(blog));

        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Page<BlogSummaryDto> getHomeBlogSummary() {
        Pageable pageable = PageRequest.of(0, 5);
        return blogRepository.findBlogSummary(pageable);
    }

    public Page<BlogSummaryDto> getPageBlogSummary(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return blogRepository.findBlogSummary(pageable);
    }

    public Page<BlogSummaryDto> getAllBlogSummary() {
        Pageable pageable = Pageable.unpaged();
        return blogRepository.findBlogSummary(pageable);
    }

    public List<Blog> getBlogByCriteria(String criteria){
        return blogRepository.findBlogByCustomCriteria(criteria);
    }

    public Blog getblogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public Blog updateBlog(Long id, Blog updatedBlog) {
        Blog existingBlog = blogRepository.findById(id).orElse(null);

        if (existingBlog == null) {
            return null;
        }

        existingBlog.setTitle(updatedBlog.getTitle());
        existingBlog.setDescription(updatedBlog.getDescription());
        existingBlog.setUpdateAt(LocalDateTime.now());
        if (updatedBlog.getSubheadingList() != null) {
            existingBlog.getSubheadingList().clear();

            updatedBlog.getSubheadingList().forEach(s -> {
                s.setBlog(existingBlog);
                existingBlog.getSubheadingList().add(s);
            });
        }

        return blogRepository.save(existingBlog);
    }
}


