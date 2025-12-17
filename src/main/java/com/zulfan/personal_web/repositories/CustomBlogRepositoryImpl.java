package com.zulfan.personal_web.repositories;

import com.zulfan.personal_web.entities.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomBlogRepositoryImpl implements CustomBlogRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findBlogByCustomCriteria(String criteria) {
        String query = "SELECT b FROM Blog b WHERE LOWER(b.title) LIKE LOWER(:criteria)";
        TypedQuery<Blog> typedQuery = entityManager.createQuery(query, Blog.class);
        typedQuery.setParameter("criteria", "%" + criteria + "%");
        return typedQuery.getResultList();
    }
}
