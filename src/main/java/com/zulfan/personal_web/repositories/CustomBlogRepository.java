package com.zulfan.personal_web.repositories;

import com.zulfan.personal_web.entities.Blog;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomBlogRepository {
    List<Blog> findBlogByCustomCriteria(String searchText);
}
