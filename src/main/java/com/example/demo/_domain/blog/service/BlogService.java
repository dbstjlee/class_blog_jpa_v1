package com.example.demo._domain.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo._domain.blog.dto.ArticleDTO;
import com.example.demo._domain.blog.entity.Article;
import com.example.demo._domain.blog.repository.PostRepository;
import com.example.demo.common.ApiUtil;
import com.example.demo.common.errors.Exception400;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // PostRepository 를 매개변수로 받는 생성자 자동 생성
@Service // IoC(빈(객체)으로 등록)
// -> IoC 컨테이너가 BlogService 빈 생성 시 자동으로 PostRepository 빈 주입
public class BlogService {

	// @Autowired // DI <-- 생략 가능하나 가독성 때문에 작성
	// final - 초기화 반드시 보장X -> @RequiredArgsConstructor 필요
	// final 사용 이유 -> 성능 상의 이점 있음
	private final PostRepository postRepository;

	@Transactional // 쓰기 지연 처리까지
	public Article save(ArticleDTO dto) {
		// 비즈니스 로직이 필요하다면 작성
		return postRepository.save(dto.toEntity());
		// postRepository는 JpaRepository를 상속받기 때문에 save() 사용 가능
	}
	
	// 전체 게시글 조회 기능 
	public List<Article> findAll() {
		List<Article> articles = postRepository.findAll();
		// ListCrudRepository가 findAll() 제공
		return articles;
	}
	
	// 상세보기 게시글 조회
	public Article findById(Integer id) {
		// Optional<T> 는 JAVA 8에서 도입된 클래스이며, 
		// 값이 존재할 수도 있고 없을 수도 있는 상황을 명확하게 처리하기 위해 사용된다. 
		return postRepository.findById(id).orElseThrow(() -> new Exception400("해당 게시글이 없습니다."));
		// Optional<Article> 반환
		// 데이터가 없으면 예외 떨어짐
		
		
	}
	
}
