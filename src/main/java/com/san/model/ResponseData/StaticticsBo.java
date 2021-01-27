package com.san.model.ResponseData;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/17:28
 * 站点服务统计
 */
public class StaticticsBo {
    private Integer articles;
    private Integer comments;

    public Integer getArticles() {
        return articles;
    }

    public void setArticles(Integer articles) {
        this.articles = articles;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "StaticticsBo{" +
                "articles=" + articles +
                ", comments=" + comments +
                '}';
    }
}
