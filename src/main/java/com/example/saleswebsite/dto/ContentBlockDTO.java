package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.ContentBlock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentBlockDTO {

    private Long id;
    private Long newsId;
    private String title;
    private Integer sortOrder;
    private String content;
    private String imgContent;
    private String captionImg;

    public static ContentBlockDTO fromEntity(ContentBlock entity) {
        if (entity == null)
            return null;
        ContentBlockDTO dto = new ContentBlockDTO();
        dto.setId(entity.getId());
        dto.setNewsId(entity.getNewsId());
        dto.setTitle(entity.getTitle());
        dto.setSortOrder(entity.getSortOrder());
        dto.setContent(entity.getContent());
        dto.setImgContent(entity.getImgContent());
        dto.setCaptionImg(entity.getCaptionImg());
        return dto;
    }

    public ContentBlock toEntity() {
        ContentBlock e = new ContentBlock();
        e.setId(this.id);
        e.setNewsId(this.newsId);
        e.setTitle(this.title);
        e.setSortOrder(this.sortOrder);
        e.setContent(this.content);
        e.setImgContent(this.imgContent);
        e.setCaptionImg(this.captionImg);
        return e;
    }
}
