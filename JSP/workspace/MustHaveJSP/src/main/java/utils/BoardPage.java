package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";
		// 전체 페이지 수 
		int totalPages = (int)(Math.ceil((double)totalCount/pageSize));
		int pageTemp = (((pageNum-1)/blockPage)*blockPage)+1;
		// 첫페이지 , 이전페이지를 출력할지 설정하는 if
		if(pageTemp != 1) {
			pagingStr = "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp;";
			pagingStr +="<a href='"+reqUrl+"?pageNum="+(pageTemp-1)+"'>[이전 블록]</a>";
		}
		int blockCount = 1;
		// 페이지 블럭에 맞게 페이지 숫자를 출력하는 if문
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				pagingStr += "&nbsp;<a href='"+reqUrl+"?pageNum="+pageTemp+"'>"+pageTemp+"</a>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		// 다음블럭, 마지막페이지를 출력하는 if문
		if(pageTemp<= totalPages) {
			pagingStr += "<a href='"+reqUrl+"?pageNum="+pageTemp+"'>[다음블럭]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='"+reqUrl+"?pageNum="+totalPages+"'>[마지막 페이지]</a>";
		}
		
		return pagingStr;
	}
}
