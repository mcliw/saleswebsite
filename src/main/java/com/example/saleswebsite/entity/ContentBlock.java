package com.example.saleswebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "content_block")
public class ContentBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "news_id")
    private Long newsId;

    private String title;

    @Column(name = "sort_order")
    private Integer sortOrder;

    private String content;

    @Column(name = "img_content")
    private String imgContent;

    @Column(name = "caption_img")
    private String captionImg;

}
