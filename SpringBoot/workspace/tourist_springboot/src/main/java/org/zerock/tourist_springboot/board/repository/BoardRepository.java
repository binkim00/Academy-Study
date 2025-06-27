package org.zerock.tourist_springboot.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.tourist_springboot.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>,
        BoardRepositoryCustom, BoardDslRepository {

    Page<Board> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Board> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Board> findByIdContainingIgnoreCase(String keyword, Pageable pageable);
}
