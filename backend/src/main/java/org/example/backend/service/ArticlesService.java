package org.example.backend.service;

import org.example.backend.exception.PerfumeNotFoundException;
import org.example.backend.model.dto.ArticlesDto;
import org.example.backend.model.plp.ArticlesPlpDto;
import org.example.backend.model.record.Articles;
import org.example.backend.repository.ArticlesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {

    private final ArticlesRepository articlesRepository;

    public ArticlesService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }


    public Articles createArticles(ArticlesDto articles) {
        Articles articles1 = new Articles(
                null,
                articles.name(),
                articles.imgUrl(),
                articles.description()
        );
        return articlesRepository.save(articles1);
    }

    public Articles updateArticles (String id, ArticlesDto articles) {
        Articles oldData = articlesRepository.findById(id)
                .orElseThrow(() -> new PerfumeNotFoundException("Not found: " + id));

        Articles articles1 = new Articles(
                oldData.id(),
                articles.name(),
                articles.imgUrl(),
                articles.description()
        );

        return articlesRepository.save(articles1);
    }

    public void deleteArticles (String id) {
        Articles articles = articlesRepository.findById(id)
                .orElseThrow(() -> new PerfumeNotFoundException("Not found: " + id));
        articlesRepository.delete(articles);
    }

    public List<ArticlesPlpDto> findAllPlp() {
        return articlesRepository.findAll().stream()
                .map(article -> new ArticlesPlpDto(
                        article.id(),
                        article.name(),
                        article.imgUrl(),
                        article.description()
                ))
                .toList();
    }


    public Articles findById (String id){
        return articlesRepository.findById(id)
                .orElseThrow( () -> new PerfumeNotFoundException("Not found the Articles with id: " + id));
    }

}
