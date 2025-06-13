package org.zerock.tourist_spring.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.tourist_spring.board.dto.BoardDTO;
import org.zerock.tourist_spring.board.mapper.BoardMapper;
import org.zerock.tourist_spring.board.vo.BoardVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getPagedList(Map<String, Object> paramMap) {
        return boardMapper.selectPaged(paramMap).stream()
                .map(vo -> BoardDTO.builder()
                        .num(vo.getNum())
                        .title(vo.getTitle())
                        .content(vo.getContent())
                        .id(vo.getId())
                        .postdate(vo.getPostdate())
                        .visitcount(vo.getVisitcount())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalCount(String searchWord) {
        return boardMapper.countAll(searchWord);
    }

    @Override
    public BoardDTO getView(int num) {
        BoardVO vo = boardMapper.selectOne(num);

        return BoardDTO.builder()
                .num(vo.getNum())
                .title(vo.getTitle())
                .content(vo.getContent() != null
                        ? vo.getContent().replaceAll("(\r\n|\r|\n)", "<br>")
                        : null)
                .id(vo.getId())
                .postdate(vo.getPostdate())
                .visitcount(vo.getVisitcount())
                .build();
    }

    @Override
    public void write(BoardDTO dto) {
        BoardVO vo = new BoardVO();
        vo.setTitle(dto.getTitle());
        vo.setContent(dto.getContent());
        vo.setId(dto.getId());
        boardMapper.insert(vo);
    }

    @Override
    public void edit(BoardDTO dto) {
        BoardVO vo = new BoardVO();
        vo.setNum(dto.getNum());
        vo.setTitle(dto.getTitle());
        vo.setContent(dto.getContent());
        boardMapper.update(vo);
    }

    @Override
    public void remove(int num) {
        boardMapper.delete(num);
    }

    @Override
    public void increaseVisitCount(int num) {
        boardMapper.updateVisitCount(num);
    }
}

