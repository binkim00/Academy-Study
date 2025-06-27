package org.zerock.tourist_springboot.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.zerock.tourist_springboot.board.domain.Board;
import org.zerock.tourist_springboot.board.dto.BoardDTO;
import org.zerock.tourist_springboot.common.dto.PageRequestDTO;
import org.zerock.tourist_springboot.common.dto.PageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    // EntityManager : CRUD, JPQL실행, 영속성 컨텍스트 관리를 담당하는 객체
    @PersistenceContext
    private EntityManager em;

    @Override
    public PageResponseDTO<BoardDTO> search(PageRequestDTO pageRequestDTO) {
        //페이징을 하기위한 객체 생성
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1 // 페이지 번호
                ,pageRequestDTO.getSize() // 페이지 사이즈
                , Sort.by("num").descending());// 정렬방식
        // 가장 기본인 JPQL문 작성
        String sql = "SELECT b FROM Board b";
        // keyword값 변수에 저장
        String keyword = pageRequestDTO.getKeyword();
        // 검색어가 있는지 확인하는 if문
        if(keyword!=null && !keyword.isEmpty()){
            sql+=" WHERE b.title LIKE '%"+keyword+"%'";
        }
        // 정렬 처리 추가
        sql += " ORDER BY b.num DESC";
        // SQL을 실행한 결과를 저장
        TypedQuery<Board> query = em.createQuery(sql, Board.class);
        // 조회된 데이터의 개수를 저장
        int totalCount = query.getResultList().size();
        // 페이징에 필요한 데이터 만큼만 잘라네는 코드
        // 몇번째 데이터부터 가지고 올지 설정
        query.setFirstResult((int)pageable.getOffset());
        // 몇개의 데이터를 가지고 올지 설정
        query.setMaxResults((int)pageable.getPageSize());
        // PageResponseDTO에 저장할 dtoList를 생성
        List<BoardDTO> dtoList = query.getResultList().stream()
                .map(BoardDTO::new).toList();
        // PageResponseDTO를 생성하여 반환
        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalCount)
                .build();
    }
}
