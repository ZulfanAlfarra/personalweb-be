package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.ApiResponse;
import com.zulfan.personal_web.dto.BlogSummaryDto;
import com.zulfan.personal_web.entities.Blog;
import com.zulfan.personal_web.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "http://localhost:5173")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody Blog blog){
        Blog newBlog = blogService.saveBlog(blog);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.CREATED.value(), "Success create blog", newBlog)
        );
//        return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllBlog(){
        List<Blog> allBlogs = blogService.getAllBlogs();
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Success get all blog", allBlogs)
        );
//        return ResponseEntity.ok().body(blogService.getAllBlogs());
    }

    @GetMapping("/_search")
    public ResponseEntity<?> searchBlog(@RequestParam String searchText){
        List<Blog> blogs = blogService.getBlogByCriteria(searchText);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Found blogs", blogs)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id){
        Blog blog = blogService.getblogById(id);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Found blog with id " + id, blog)
        );
//        return ResponseEntity.ok().body(blogService.getblogById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogById(@PathVariable Long id){
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
//        return ResponseEntity.ok().body("Deleted blog successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody Blog blog){
        Blog updatedBlog = blogService.updateBlog(id, blog);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Success updated blog with id " + id, updatedBlog)
        );
//        return ResponseEntity.ok().body(blogService.updateBlog(id, blog));
    }

    @GetMapping("/summary/home")
    public ResponseEntity<?> getSummaryBlogs(){
        Page<BlogSummaryDto> summary = blogService.getHomeBlogSummary();
        return ResponseEntity.ok(
                Map.of(
                        "message", "Success",
                        "data", summary.getContent(),
                        "metadata", Map.of(
                                "totalElements", summary.getTotalElements(),
                                "totalPages", summary.getTotalPages(),
                                "pageSize", summary.getSize(),
                                "currentPage", summary.getNumber()
                        )
                )
        );
    }

    @GetMapping("/summary/blog")
    public ResponseEntity<?> getSummaryAllBlogs(){
        Page<BlogSummaryDto> summary = blogService.getAllBlogSummary();
        return ResponseEntity.ok(
                Map.of(
                        "message", "Success",
                        "data", summary.getContent(),
                        "metadata", Map.of(
                                "totalElements", summary.getTotalElements(),
                                "totalPages", summary.getTotalPages(),
                                "pageSize", summary.getSize(),
                                "currentPage", summary.getNumber()
                        )
                )
        );
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getPageBlog(@RequestParam(value = "page") int page){
        Page<BlogSummaryDto> blogs = blogService.getPageBlogSummary(page);
        return ResponseEntity.ok(
                Map.of(
                        "message", "Success",
                        "data", blogs.getContent(),
                        "metadata", Map.of(
                                "totalElements", blogs.getTotalElements(),
                                "totalPages", blogs.getTotalPages(),
                                "PageSize", blogs.getSize(),
                                "currentPage", blogs.getNumber()
                        )
                )
        );
    }
}
