package member.dto;

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
	
	public MemberDTO() {}
	public MemberDTO(String id, String email, String name, String password, String phone, String gender, boolean agree,
			String content) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.gender = gender;
		this.agree = agree;
		this.content = content;
	}
	public MemberDTO(String id, String email, String name, String password, String phone, String gender, boolean agree,
			String content, String regidate) {
		this(id,email,name,password,phone,gender, agree, content);
		this.regidate = regidate;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}
	
	
	
	
}
