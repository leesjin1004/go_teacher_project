//����Ͻ�(����WEB�� ����� �츮��)�� ���� �����ϴ� �������� �޴��� ���� ������. �׻� �̸� �ڿ� Service�� �ٿ������! �̰� �����!�� �Ⱥ��� ������޾�

package service;

import java.util.List;

import vo.BookVo;

public interface BookService {
	
	public List<BookVo> bookList();
	
	public void bookAdd(BookVo vo);
	
	public void bookDelete(int bookno);
	
	public void bookUpdate(BookVo vo);
	
	public BookVo getBook(int bookno);	//Ư��å�� �Ѱ���
	
	public List<BookVo> searchBook(String condition , String keyword);
	

}
