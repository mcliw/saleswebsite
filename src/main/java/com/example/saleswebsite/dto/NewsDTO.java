package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {

    private Long id;
    private Long authorId;
    private String title;
    private String slug;
    private String thumbnail;
    private String content;

    public static NewsDTO fromEntity(News entity) {
        if (entity == null)
            return null;
        NewsDTO dto = new NewsDTO();
        dto.setId(entity.getId());
        dto.setAuthorId(entity.getAuthorId());
        dto.setTitle(entity.getTitle());
        dto.setSlug(entity.getSlug());
        dto.setThumbnail(entity.getThumbnail());
        dto.setContent(entity.getContent());
        return dto;
    }

    public News toEntity() {
        News e = new News();
        e.setId(this.id);
        e.setAuthorId(this.authorId);
        e.setTitle(this.title);
        e.setSlug(this.slug);
        e.setThumbnail(this.thumbnail);
        e.setContent(this.content);
        return e;
    }
}
