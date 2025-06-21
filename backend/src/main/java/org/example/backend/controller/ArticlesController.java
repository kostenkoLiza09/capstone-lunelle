package org.example.backend.controller;

import org.example.backend.model.dto.ArticlesDto;
import org.example.backend.model.record.Articles;
import org.example.backend.service.ArticlesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    private final ArticlesService articlesService;

    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping
    public List<ArticlesDto> findAllArticles() {
        return articlesService.findAll();
    }

    @GetMapping("/{id}")
    public Articles findById(@PathVariable String id) {
        return articlesService.findById(id);
    }

    @PostMapping
    public Articles createArticles(@RequestBody ArticlesDto articles) {
        return articlesService.createArticles(articles);
    }

    @PutMapping("/{id}")
    public Articles updateArticles(@PathVariable String id, @RequestBody ArticlesDto articles) {
        return articlesService.updateArticles(id, articles);
    }

    @DeleteMapping("/{id}")
    public void deleteArticles(@PathVariable String id) {
        articlesService.deleteArticles(id);
    }
}
