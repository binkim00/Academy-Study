package org.zerock.tourist_springboot.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.tourist_springboot.board.domain.Board;
import org.zerock.tourist_springboot.board.dto.BoardDTO;
import org.zerock.tourist_springboot.board.repository.BoardRepository;
import org.zerock.tourist_springboot.common.dto.PageRequestDTO;
import org.zerock.tourist_springboot.common.dto.PageResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDTO> getList() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(board -> BoardDTO.builder()
                .num(board.getNum())
                .title(board.getTitle())
                .content(board.getContent())
                .id(board.getId())
                .visitCount(board.getVisitCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build()).collect(Collectors.toList());
    }

    public PageResponseDTO<BoardDTO> findList(PageRequestDTO requestDTO) {
        List<Board> boards = boardRepository.findAll();

        List<BoardDTO> dtoList = boards.stream()
                .map(board -> BoardDTO.builder()
                        .num(board.getNum())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .id(board.getId())
                        .visitCount(board.getVisitCount())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .build())
                .toList();

        return new PageResponseDTO<>(requestDTO, dtoList, dtoList.size());

    }

    public BoardDTO read(Long num) {
        Board board = boardRepository.findById(num)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + num));

        board.increaseVisitCount();

        return BoardDTO.builder()
                .num(board.getNum())
                .title(board.getTitle())
                .content(board.getContent())
                .id(board.getId())
                .visitCount(board.getVisitCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }


    public void write(BoardDTO dto) {
        Board board = dto.toEntity();
        boardRepository.save(board);
    }

    public void update(BoardDTO dto) {
        Board board = boardRepository.findById(dto.getNum())
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않습니다: " + dto.getNum()));

        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setUpdatedAt(LocalDateTime.now());

        boardRepository.save(board);
    }

    public void remove(Long num) {
        boardRepository.deleteById(num);
    }

}
