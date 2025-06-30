package me.shinsunyoung.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyoung.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request, String writer){
        return blogRepository.save(request.toEntity(writer));
    }
    // DB에 저장되어있는 모든 데이터 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }
    // DB의 데이터 한 건 조회
    public Article findById(Long id){
        return blogRepository.findById(id)
                // 조회결과가 없을때 예외처리를 실행
                .orElseThrow(()->new IllegalArgumentException("not found" + id));
    }
    // 데이터 삭제
    public void delete(Long id, String username){
        Article article = blogRepository.findById(id).get();
        // 작성자와 로그인한 유저 아이디가 같으면 삭제
        if(article.getWriter().equals(username)){
            blogRepository.deleteById(id);
        }
    }
    // 데이터 수정하기
    @Transactional
    public Article update(long id, UpdateArticleRequest request, String loginUserEmail){
        // id를 이용하여 데이터를 변수에 저장
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found" + id));
        // @Transactional 어노테이션이 있다면
        // title과 content를 변경하는 메서드를 실행하는 순간 바로 db에 적용됨
        // -> blogRepository.save()실행할 필요가 없음
        if(article.getWriter().equals(loginUserEmail)){
            article.update(request.getTitle(), request.getContent());
        }
        // @Transactional이 없다면 아래와 같이 save를 실행해야함
        //blogRepository.save(article);
        return article;
    }
}
