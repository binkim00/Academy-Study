package org.zerock.tourist_springboot.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public PageResponseDTO<BoardDTO> findList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable("num");
        Page<Board> result;

        String[] types = requestDTO.getTypes();
        String keyword = requestDTO.getKeyword();

        if (types != null && keyword != null && !keyword.isBlank()) {
            String type = types[0];

            switch (type) {
                case "t" -> result = boardRepository.findByTitleContainingIgnoreCase(keyword, pageable);
                case "c" -> result = boardRepository.findByContentContainingIgnoreCase(keyword, pageable);
                case "w" -> result = boardRepository.findByIdContainingIgnoreCase(keyword, pageable);
                default -> result = boardRepository.findAll(pageable);
            }
        } else {
            result = boardRepository.findAll(pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> BoardDTO.builder()
                        .num(board.getNum())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .id(board.getId())
                        .visitCount(board.getVisitCount())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());

        return new PageResponseDTO<>(requestDTO, dtoList, (int) result.getTotalElements());
    }


    public BoardDTO read(Long num) {
        Board board = boardRepository.findById(num)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + num));

        board.increaseVisitCount();

        return BoardDTO.builder()
                .num(board.getNum())
                .title(board.getTitle())
                .content(board.getContent().replaceAll("(\r\n|\r|\n)", "<br/>"))
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

    // 선생님 버전
//    public PageResponseDTO<BoardDTO> findList(PageRequestDTO pageRequestDTO) {
//        // 전체 데이터 개수
//        int totalCount = boardRepository.findAll().size();
//        //Pageable을 이용한 페이징 조건 생성
//        Pageable pageable = PageRequest.of(
//                pageRequestDTO.getPage()-1 // 페이지 번호, 0페이지부터 시작
//                ,pageRequestDTO.getSize() // 페이지 사이즈
//                ,Sort.by("num").descending());// 정렬방식
//        // 페이징 처리된 findAll의 결과물을 저장
//        List<Board> boardList = boardRepository.findAll(pageable).getContent();
//        // Board를 BoardDTO로 변경
//        List<BoardDTO> dtoList = boardList.stream()
//                .map(BoardDTO::new)
//                .collect(Collectors.toList());
//        // PageResponseDTO를 생성하여 반환
//        return PageResponseDTO.<BoardDTO>withAll()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(dtoList)
//                .total(totalCount)
//                .build();
//    }
//    public PageResponseDTO<BoardDTO> searchList(PageRequestDTO pageRequestDTO) {
//        return boardRepository.searchDsl(pageRequestDTO);
//    }
//
//    @Transactional // 조회수 1증가를 위한 트랜잭션
//    public BoardDTO findOne(Long num){
//        // num을 기준으로 데이터를 저장
//        Board vo = boardRepository.findById(num).get();
//        // 조회수 증가 SQL실행
//        vo.updateVisitCount();
//        // 화면에서 사용하는 객체인 DTO로 변경
//        BoardDTO dto = new BoardDTO(vo);
//
//        return dto;
//    }

}
