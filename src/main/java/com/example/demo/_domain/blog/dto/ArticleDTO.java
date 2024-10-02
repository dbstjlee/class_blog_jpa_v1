package com.example.demo._domain.blog.dto;

import com.example.demo._domain.blog.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
// 즉, 주로 계층 간의 데이터 전송 목적으로 설계된다. 
public class ArticleDTO {

	private String title;
	private String content;
	
	// ArticleDTO -> Article 데이터 타입을 변환시키는 메서드
	// Article 에 기본 생성자(@NoArgsConstructor) 선언 필요
	public Article toEntity() {
		return Article.builder()
				.title(this.title)
				.content(this.content)
				.build();
	}
	
}
