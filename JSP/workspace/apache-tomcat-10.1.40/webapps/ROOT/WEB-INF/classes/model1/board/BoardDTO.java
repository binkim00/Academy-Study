package model1.board;

import java.sql.Date;

public class BoardDTO {
	private int num;
	private String title;
	private String content;
	private String id;
	private Date postDate;
	private int visitCount;
	private String name;
	
	public BoardDTO() {}
	public BoardDTO(int num, String title, String content, String id, Date postDate, int visitCount) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.id = id;
		this.postDate = postDate;
		this.visitCount = visitCount;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
