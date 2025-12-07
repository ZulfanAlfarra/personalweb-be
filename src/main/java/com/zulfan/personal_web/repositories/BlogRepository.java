package com.zulfan.personal_web.repositories;

import com.zulfan.personal_web.dto.BlogSummaryDto;
import com.zulfan.personal_web.entities.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT new com.zulfan.personal_web.dto.BlogSummaryDto(b.id, b.title, b.description, b.createdAt) FROM Blog b ORDER BY b.createdAt DESC")
    List<BlogSummaryDto> findBlogSummary(Pageable pageable);
}
