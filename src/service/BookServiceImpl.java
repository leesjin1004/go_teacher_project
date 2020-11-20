//�굵 �ڿ� Impl���°� ����������..!!!!!!! �׽�Ʈ�� �� ��û���� ������!

package service;

import java.util.List;

import dao.BookDAO_Mariadb;
import vo.BookVo;

public class BookServiceImpl implements BookService {
   private BookDAO_Mariadb dao = null; //�갡 �־�� ��񿬵� ����
   //�ּҰ� ����� ��?
   
   
   
   @Override
   public List<BookVo> bookList() {
      return dao.bookList();
   }

   public BookServiceImpl() {
//      super();   //������ �ڵ����� ȣ�� ��
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