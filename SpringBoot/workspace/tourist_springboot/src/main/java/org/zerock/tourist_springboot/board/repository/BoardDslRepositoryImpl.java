package org.zerock.tourist_springboot.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.tourist_springboot.board.domain.Board;
import org.zerock.tourist_springboot.board.domain.QBoard;
import org.zerock.tourist_springboot.board.dto.BoardDTO;
import org.zerock.tourist_springboot.common.dto.PageRequestDTO;
import org.zerock.tourist_springboot.common.dto.PageResponseDTO;

import java.util.List;


public class BoardDslRepositoryImpl
        extends QuerydslRepositorySupport
        implements BoardDslRepository {
    public BoardDslRepositoryImpl(){
        super(Board.class);
    }

    @Override
    public PageResponseDTO<BoardDTO> searchDsl(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("num").descending());
        String keyword = pageRequestDTO.getKeyword();
        // QueryDSL에서 생성된 Board Entity를 저장
        QBoard qBoard = QBoard.board;
        // from(qBoard) : SELECT b FROM Board b
        JPQLQuery<Board> query = from(qBoard);
        if(keyword!=null && !keyword.isEmpty()){
            // WHERE b.title LIKE '%keyword%'
            query.where(qBoard.title.contains(keyword));
        }
        // BooleanBuilder : and, or조건을 더욱 쉽게 사용할 수 있도록 하는 클래스
        BooleanBuilder builder = new BooleanBuilder();
        String[] types = pageRequestDTO.getTypes();
        if(types!=null && types.length>0 && keyword!=null && !keyword.isEmpty()){
            for(String type : types){
                switch(type){
                    case "t" : builder.or(qBoard.title.contains(keyword)); break;
                    case "c" : builder.or(qBoard.content.contains(keyword));break;
                    case "w" : builder.or(qBoard.id.contains(keyword)); break;
                }
            }
            // t,c,w 세가지 모두 있을 경우 완성되는 SQL문
            // WHERE (title LIKE '%keyword%'
            //      OR content LIKE '%keyword%'
            //      OR id LIKE '%keyword%')
            query.where(builder);
        }

        // 페이징 처리
        this.getQuerydsl().applyPagination(pageable, query);
        // 생성한 쿼리를 실행
        List<Board> list = query.fetch();
        // 전체 개수를 저장
        int totalCount = Long.valueOf(query.fetchCount()).intValue();
        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(list.stream().map(BoardDTO::new).toList())
                .total(totalCount)
                .build();
    }
}
