package test;

import dao.BookDAO_Mariadb;
import vo.BookVo;

public class test02 {

	public static void main(String[] args) {

		BookDAO_Mariadb b = new BookDAO_Mariadb();
		
		BookVo vo = new BookVo();
		vo.setTitle("Spring boot");
		vo.setPublisher("����");
		vo.setPrice(7700);
		
		//b.bookAdd(vo);
		
		BookVo book = b.getBook(4);
		
		if(book != null) {
		
		System.out.println(book);
		book.setPrice(book.getPrice()*2);
		b.bookUpdate(book);
		BookVo book2 = b.getBook(book.getBookno());
		System.out.println(book2);
		b.bookDelete(book2.getBookno());
		System.out.println(b.getBook(book2.getBookno()));
		}
		
		
		//b.bookList().forEach(i->{System.out.println(i);});
		//b.bookDelete(2);
		System.out.println("==============��Ϻ���==============");
		b.bookList().forEach(i->{System.out.println(i);});
		
		System.out.println("==============�˻�==============");
		b.bookSearch("title", "��").forEach(i -> {System.out.println(i);});
	
		System.out.println("==============�˻�==============");
		b.bookSearch("publisher", "��").forEach(i->{System.out.println(i);});

	}

}
 