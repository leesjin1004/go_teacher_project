//비즈니스(서비스WEB을 만드는 우리들)과 고객을 연결하는 음식점의 메뉴판 같은 페이지. 항상 이름 뒤에 Service를 붙여줘야함! 이건 약속임!☆ 안붙임 관종취급쓰

package service;

import java.util.List;

import vo.BookVo;

public interface BookService {
	
	public List<BookVo> bookList();
	
	public void bookAdd(BookVo vo);
	
	public void bookDelete(int bookno);
	
	public void bookUpdate(BookVo vo);
	
	public BookVo getBook(int bookno);	//특정책을 넘겨줘
	
	public List<BookVo> searchBook(String condition , String keyword);
	

}
