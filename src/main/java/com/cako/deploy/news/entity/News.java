package com.cako.deploy.news.entity;

import com.cako.platform.category.entity.Category;
import com.cako.platform.user.entity.User;
import com.orm.commons.utils.IdEntity;

import javax.persistence.*;

/**
 * Created by Davis on 2016/08/26 0026.
 */
@Table(name = "t_u_news")
@Entity
public class News extends IdEntity {
    private String title;
    private String content;
    private Category category;
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
