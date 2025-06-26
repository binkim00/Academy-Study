package org.zerock.tourist_springboot.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.tourist_springboot.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // title LIKE %?% (제목 검색)
    Page<Board> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // content LIKE %?% (내용 검색)
    Page<Board> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    // id LIKE %?% (작성자 검색)
    Page<Board> findByIdContainingIgnoreCase(String keyword, Pageable pageable);
}
