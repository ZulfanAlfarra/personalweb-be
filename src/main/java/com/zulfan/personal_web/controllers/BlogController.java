package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.ApiResponse;
import com.zulfan.personal_web.dto.BlogSummaryDto;
import com.zulfan.personal_web.entities.Blog;
import com.zulfan.personal_web.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@CrossOrigin(origins = "http://localhost:5173")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping
    public ApiResponse<Blog> createBlog(@RequestBody Blog blog){
        Blog newBlog = blogService.saveBlog(blog);
        return ApiResponse.success("Success create blog", newBlog);
//        return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
    }

    @GetMapping
    public ApiResponse<List<Blog>> getAllBlog(){
        List<Blog> allBlogs = blogService.getAllBlogs();
        return ApiResponse.success("Success get blogs", allBlogs);
//        return ResponseEntity.ok().body(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ApiResponse<Blog> getBlogById(@PathVariable Integer id){
        Blog blog = blogService.getblogById(id);
        return ApiResponse.success("Success get blog by id", blog);
//        return ResponseEntity.ok().body(blogService.getblogById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBlogById(@PathVariable Integer id){
        blogService.deleteBlog(id);
        return ApiResponse.success("Success deleted blog with id " + id, "");
//        return ResponseEntity.ok().body("Deleted blog successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<Blog> updateBlog(@PathVariable Integer id, @RequestBody Blog blog){
        Blog updatedBlog = blogService.updateBlog(id, blog);
        return ApiResponse.success("Success updated blog with id " + id, updatedBlog);
//        return ResponseEntity.ok().body(blogService.updateBlog(id, blog));
    }

    @GetMapping("/summary")
    public ApiResponse<List<BlogSummaryDto>> getSummaryBlogs(){
        List<BlogSummaryDto> summary = blogService.getSummaryBlogs();
        return ApiResponse.success("Success get summary blogs", summary);
    }
}
