package org.zerock.tourist_spring.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.tourist_spring.member.dto.MemberDTO;
import org.zerock.tourist_spring.member.mapper.MemberMapper;
import org.zerock.tourist_spring.member.vo.MemberVO;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public void register(MemberDTO dto) {
        MemberVO vo = new MemberVO();
        vo.setId(dto.getId());
        vo.setEmail(dto.getEmail());
        vo.setName(dto.getName());
        vo.setPassword(dto.getPassword());
        vo.setPhone(dto.getPhone());
        vo.setGender(dto.getGender());
        vo.setAgree(dto.isAgree());
        vo.setContent(dto.getContent());

        memberMapper.insertMember(vo);
    }

    @Override
    public MemberDTO login(String id, String password) {
        return memberMapper.login(id, password);
    }
}
