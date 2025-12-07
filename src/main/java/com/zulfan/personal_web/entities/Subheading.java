package com.zulfan.personal_web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Subheading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subTitle;
    @Column(columnDefinition = "TEXT")
    private String subDescription;
    @ManyToOne
    @JoinColumn(name = "blogId")
    @JsonBackReference
    private Blog blog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
