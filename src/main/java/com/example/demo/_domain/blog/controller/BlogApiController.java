package com.example.demo._domain.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo._domain.blog.dto.ArticleDTO;
import com.example.demo._domain.blog.entity.Article;
import com.example.demo._domain.blog.service.BlogService;
import com.example.demo.common.ApiUtil;
import com.example.demo.common.errors.Exception400;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController // @controller + @responsebody
public class BlogApiController {

	private final BlogService blogService;
	
	// URL 매핑(주소 설계) - http://localhost:8080/api/articles
	@PostMapping("/api/articles")
	public ResponseEntity<Article> addArticle(@RequestBody ArticleDTO dto) {
		// 1. 인증 검사
		// 2. 유효성 검사 
		Article savedArticle = blogService.save(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
		// http의 body에 savedArticle 넣음
	}
	
	@GetMapping("/api/articles")
	public ApiUtil<?> getAllArticles() { // 값 받을 거 없음
		List<Article> articles = blogService.findAll();
		if(articles.isEmpty()) {
			// return new ApiUtil<>(new Exception400("게시글이 없습니다."));
			throw new Exception400("게시글이 없습니다.");
		}
		return new ApiUtil<>(articles);
	}
	
	// URL 매핑(주소 설계) - http://localhost:8080/api/articles/1 -> PathVariable 방식
	@GetMapping(value = "/api/articles/{id}")
	public ApiUtil<?> findArticle(@PathVariable(name = "id") Integer id) { 
		
		// 1. 유효성 검사 생략
		Article article = blogService.findById(id);
		return new ApiUtil<>(article);
	}
	
	
}
