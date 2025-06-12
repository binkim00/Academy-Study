package org.zerock.tourist_spring.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	private String id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String gender;
	private boolean agree;
	private String content;
	private String regidate;

}
