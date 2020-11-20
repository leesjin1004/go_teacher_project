
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVo;

class BookTest {

   BookService service = null; //service;는 service=null;이랑 같음..
   
   @BeforeEach
   void setUp() throws Exception {
      
      BookDAO_Mariadb dao = new BookDAO_Mariadb(); 
      service = new BookServiceImpl(dao); //impl이 만들어짐
      
      //생성자를 통한 dao 주입 - 컨스트럭셔 인덱션
   }
   
   
   // 기능별로 테스트 가능
   
   @Test
   void list() {
      service.bookList().forEach(i -> {System.out.println(i);} ); //디비에 있는 북리스트 들어옴
      //여기 리턴되는게 리스트
      //Lambda식
   }
   
   //@Test
   void add() {
	   BookVo vo = new BookVo();
	   vo.setTitle("jsp");
	   vo.setPublisher("명지");
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
		   System.out.println("삭제되었습니다.!");
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
	   System.out.println("====== 검색 ======");
	   
	   List<BookVo> list = service.searchBook("title", "j");
	   
	   list.forEach( i -> {System.out.println(i);});
	   
	   for(BookVo data :list) {
		   System.out.println(data);
	   }
      
   }

}