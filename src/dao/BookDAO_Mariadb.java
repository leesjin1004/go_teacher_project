package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVo;

public class BookDAO_Mariadb {
     
    
   public  List<BookVo> bookList() {
      List<BookVo> list = new ArrayList<BookVo>();
      String sql ="select * from book order by bookno ASC";
      
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      try {
         con = JDBCUtil.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()) {
            BookVo vo = new BookVo();
            vo.setBookno(rs.getInt("bookno"));
            vo.setPrice(rs.getInt("price"));
            vo.setPublisher(rs.getString("publisher"));
            vo.setTitle(rs.getString("title"));
            list.add(vo);
         }
         
      } catch (Exception e) {
         System.out.println("error"+e);
      }finally {
         JDBCUtil.close(con, ps, rs);
      }
      return list;
   }
   
   
   
   public void bookAdd(BookVo vo) {
      
      String sql = "insert into book (title,publisher,price,img) values (?,?,?,?)";
      
      Connection con = null;           //DB연동할 땐 여기부터
      PreparedStatement ps = null;
      ResultSet rs = null;
      int row = 0;
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //? 값 세팅
    	  ps.setString(1, vo.getTitle());
    	  ps.setString(2, vo.getPublisher());
    	  ps.setInt(3, vo.getPrice());
    	  ps.setString(4, vo.getImg());
    	  
    	  //sql 구문 실행
    	  //ps.executeQuery();
    	  //ps.executeUpdate();
    	  row= ps.executeUpdate();
    	  
    	  //결과값 처리
    	  if(row == 0) {
    		  throw new Exception("등록실패");
    	  }
    	  
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  
	      }finally {
	    	  JDBCUtil.close(con, ps, rs);
	    	  
	      }
	      
	   }								//여기까지 항상 계속 반복. 꼭 들어가야함. spring으로 가면 이부분이 사라져 코드량이 1/10으로 줄어듬.
   
   
   
   public void bookDelete(int bookno) {
      
      String sql = "delete from book where bookno = ? ";
      Connection con = null;          
      PreparedStatement ps = null;
      ResultSet rs = null;
      int row = 0;
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //? 값 세팅
    	  ps.setInt(1, bookno);
    	  
    	  //sql 구문 실행
    	  //ps.executeQuery();
    	  row= ps.executeUpdate();
    	  
    	  //결과값 처리
    	  
    	  if(row == 0) {
    		  throw new Exception("등록실패");
    	  }
    	  
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  
	      }finally {
	    	  JDBCUtil.close(con, ps, rs);
	    	  
	      }
	      
	   }	
      
   
   
   public void bookUpdate(BookVo vo) {
      
      String sql = "update book set price = ? where bookno = ?";
      Connection con = null;          
      PreparedStatement ps = null;
      ResultSet rs = null;
      int row = 0;
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //? 값 세팅
    	  ps.setInt(1, vo.getPrice());
    	  ps.setInt(2, vo.getBookno());
    	  
    	  //sql 구문 실행
    	  //ps.executeQuery();
    	  row= ps.executeUpdate();
    	  
    	  //결과값 처리
    	  
    	  if(row == 0) {
    		  throw new Exception("등록실패");
    	  }
    	  
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  
	      }finally {
	    	  JDBCUtil.close(con, ps, rs);
	    	  
	      }
	      
   		}
   
   
   
   public List<BookVo> bookSearch(String condition, String keyword) {
      
      String sql = "select * from book where " +condition+ " like ? order by bookno desc"; 
      //select * from book where title like '%자바%';
      //select * from book where title like '%g한%';
      Connection con = null;          
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<BookVo> list = new ArrayList<BookVo>();
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //?의 값 세팅
    	  ps.setString(1,"%"+keyword+"%");
    	  
    	  //실행
    	  rs = ps.executeQuery();
    	  
    	  //결과값 처리
    	  while(rs.next()) {
    		  BookVo vo = new BookVo();
    		  vo.setBookno(rs.getInt("bookno"));
              vo.setPrice(rs.getInt("price"));
              vo.setPublisher(rs.getString("publisher"));
              vo.setTitle(rs.getString("title"));
              list.add(vo);
    	  }
    	  
      }catch (Exception e) {
    	  e.printStackTrace();
      }finally {
    	  JDBCUtil.close(con, ps, rs);
      }return list; //리턴해줘야함 마지막에!
      
   }
   
   
   
   public BookVo getBook(int bookno) {
      
      String sql = "select * from book where bookno = ? ";
      Connection con = null;          
      PreparedStatement ps = null;
      ResultSet rs = null;
      int row = 0;
      BookVo vo =null;
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //? 값 세팅
    	  ps.setInt(1, bookno);
    	  
    	  //sql 구문 실행
    	  rs = ps.executeQuery();
    	  
    	  //결과값 처리
    	  while(rs.next()) {
    		  vo= new BookVo();
    		  vo.setBookno(rs.getInt("bookno"));	//지금 Bookno , "bookno" 가 일치하는 것처럼 애초에 코드를 짤때 일치해게 해줘야함!!!
    		  vo.setPrice(rs.getInt("price"));		//안그럼 나중에 내 손과 머리만 아픔.☆
    		  vo.setTitle(rs.getString("title"));
    		  vo.setPublisher(rs.getNString("publisher"));
    		  vo.setImg(rs.getString("img"));
    		  
    	  }
    	 
	    	 
	    	  
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  
	      }finally {
	    	  JDBCUtil.close(con, ps, rs);
	      }
      	  return vo;
	   
   }
}



