
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVo;

class BookTest {

   BookService service = null; //service;�� service=null;�̶� ����..
   
   @BeforeEach
   void setUp() throws Exception {
      
      BookDAO_Mariadb dao = new BookDAO_Mariadb(); 
      service = new BookServiceImpl(dao); //impl�� �������
      
      //�����ڸ� ���� dao ���� - ����Ʈ���� �ε���
   }
   
   
   // ��ɺ��� �׽�Ʈ ����
   
   @Test
   void list() {
      service.bookList().forEach(i -> {System.out.println(i);} ); //��� �ִ� �ϸ���Ʈ ����
      //���� ���ϵǴ°� ����Ʈ
      //Lambda��
   }
   
   //@Test
   void add() {
	   BookVo vo = new BookVo();
	   vo.setTitle("jsp");
	   vo.setPublisher("����");
	   vo.setPrice(1100);
	   
	   service.bookAdd(vo);
   }
   
   //@Test
   void getBook() {
	   System.out.println(service.getBook(2));
   }
   
   //@Test
   void delete() {
	   BookVo vo = service.getBook(4);
	   if(vo != null) {
		   System.out.println(vo);
		   service.bookDelete(vo.getBookno());
		   System.out.println("�����Ǿ����ϴ�.!");
	   }
      
   }
   
   //@Test
   void update() {
	   BookVo vo = service.getBook(4);
	   if(vo != null) {
		   System.out.println(vo);
		   vo.setPrice(0);
		   service.bookUpdate(vo);
		   System.out.println(service.getBook(vo.getBookno()));
	   }
      
   }
   
   //@Test
   void search() {
	   System.out.println("====== �˻� ======");
	   
	   List<BookVo> list = service.searchBook("title", "j");
	   
	   list.forEach( i -> {System.out.println(i);});
	   
	   for(BookVo data :list) {
		   System.out.println(data);
	   }
      
   }

}