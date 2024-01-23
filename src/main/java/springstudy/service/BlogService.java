package springstudy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springstudy.domain.Article;
import springstudy.dto.AddArticleRequest;
import springstudy.dto.UpdateArticleRequest;
import springstudy.repository.BlogRepository;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;
    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return  blogRepository.save(request.toEntity());
    }

    // article 테이불애 저장되어 있는 모든 데이터를 조회.
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // ID를 받아 엔티티를 조회하고 없으면 IllegalArgumentException 예외 발생
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }
    // 삭제하는 기능
    public void delete(long id) {
        blogRepository.deleteById(id);
    }


    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
