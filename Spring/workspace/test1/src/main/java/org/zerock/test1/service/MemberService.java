package org.zerock.test1.service;

import org.zerock.test1.dto.MemberDTO;
import org.zerock.test1.vo.MemberVO;

public interface MemberService {

    void register(MemberDTO dto);

    MemberVO login(String id, String pw);

    void remove(String id);
}
