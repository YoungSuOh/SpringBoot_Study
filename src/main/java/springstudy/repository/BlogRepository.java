package springstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.domain.Article;

public interface BlogRepository extends JpaRepository<Article,Long> {
}
