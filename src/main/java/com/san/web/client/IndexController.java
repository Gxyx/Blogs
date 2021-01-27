package com.san.web.client;

import com.github.pagehelper.PageInfo;
import com.san.model.domain.Article;
import com.san.model.domain.Comment;
import com.san.service.ArticleService;
import com.san.service.CommentService;
import com.san.service.SiteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/15:44
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private SiteService siteService;


    /**
     * 自动跳转到首页
     * @param request
     * @return
     */
    @GetMapping(value = "/")
    private String index(HttpServletRequest request) {
        return this.index(request, 1, 5);
    }

    /**
     * 文章页
     * @param request
     * @param page
     * @param count
     * @return
     */
    @GetMapping(value = "/page/{p}")
    public String index(HttpServletRequest request, @PathVariable("p") int page, @RequestParam(value = "count", defaultValue = "5") int count) {
        PageInfo<Article> articles = articleService.selectArticleWithPage(page, count);
        // 获取文章热度统计信息
        List<Article> articleList = articleService.getHeatArticles();
        request.setAttribute("articles", articles);
        request.setAttribute("articleList", articleList);
        logger.info("分页获取文章信息: 页码 "+page+",条数 "+count);
        return "client/index";
    }

    /**
     * 文章详情查询
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/article/{id}")
    public String getArticleById(@PathVariable("id") Integer id, HttpServletRequest request){
        Article article = articleService.selectArticleWithId(id);
        if(article!=null){
            // 查询封装评论相关数据
            getArticleComments(request, article);
            // 更新文章点击量
            siteService.updateStatistics(article);
            request.setAttribute("article",article);
            return "client/articleDetails";
        }else {
            logger.warn("查询文章详情结果为空，查询文章id: "+id);
            // 未找到对应文章页面，跳转到提示页
            return "comm/error_404";
        }
    }

    /**
     * 查询文章的评论信息，并补充到文章详情里面
     * @param request
     * @param article
     */
    private void getArticleComments(HttpServletRequest request, Article article) {
        if (article.getAllowComment()){
            //cp表示评论页码，commentPage
            String cp = request.getParameter("cp");
            cp = StringUtils.isBlank(cp)?"1":cp;
            request.setAttribute("cp", cp);
            PageInfo<Comment> comments = commentService.getComments(article.getId(),Integer.parseInt(cp),3);
            request.setAttribute("cp", cp);
            request.setAttribute("comments", comments);
        }
    }

}
