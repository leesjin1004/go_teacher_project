//얘도 뒤에 Impl쓰는게 정해져있음..!!!!!!! 테스트에 공 엄청많이 들어야함!

package service;

import java.util.List;

import dao.BookDAO_Mariadb;
import vo.BookVo;

public class BookServiceImpl implements BookService {
   private BookDAO_Mariadb dao = null; //얘가 있어야 디비연동 가능
   //주소가 들어가줘야 함?
   
   
   
   @Override
   public List<BookVo> bookList() {
      return dao.bookList();
   }

   public BookServiceImpl() {
//      super();   //지워도 자동으로 호출 됨
   }

   public BookServiceImpl(BookDAO_Mariadb dao) {
//      super();
      this.dao = dao;
   }

   public BookDAO_Mariadb getDao() {
      return dao;
   }

   public void setDao(BookDAO_Mariadb dao) {
      this.dao = dao;
   }

   @Override
   public void bookAdd(BookVo vo) { //insert
      dao.bookAdd(vo);
   }

   @Override
   public void bookDelete(int bookno) {
      dao.bookDelete(bookno);
   }

   @Override
   public void bookUpdate(BookVo vo) {
      dao.bookUpdate(vo);
   }

   @Override
   public BookVo getBook(int bookno) {
      return dao.getBook(bookno);
   }

   @Override
   public List<BookVo> searchBook(String condition, String keyword) {
      return dao.bookSearch(condition, keyword);
   }

}