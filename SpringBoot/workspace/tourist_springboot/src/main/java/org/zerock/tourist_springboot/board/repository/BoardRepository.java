package org.zerock.tourist_springboot.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.tourist_springboot.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
