package org.zerock.test1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.test1.dto.MemberDTO;
import org.zerock.test1.vo.MemberVO;
import org.zerock.test1.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public void register(MemberDTO dto) {
        MemberVO vo = new MemberVO();
        vo.setId(dto.getId());
        vo.setPw(dto.getPw());
        vo.setEmail1(dto.getEmail1());
        vo.setEmail2(dto.getEmail2());

        memberMapper.insert(vo);
    }

    @Override
    public MemberVO login(String id, String pw) {
        return memberMapper.login(id, pw);
    }

    @Override
    public void remove(String id) {
        memberMapper.delete(id);
    }

}
