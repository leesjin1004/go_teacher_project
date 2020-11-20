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
      
      Connection con = null;           //DB������ �� �������
      PreparedStatement ps = null;
      ResultSet rs = null;
      int row = 0;
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //? �� ����
    	  ps.setString(1, vo.getTitle());
    	  ps.setString(2, vo.getPublisher());
    	  ps.setInt(3, vo.getPrice());
    	  ps.setString(4, vo.getImg());
    	  
    	  //sql ���� ����
    	  //ps.executeQuery();
    	  //ps.executeUpdate();
    	  row= ps.executeUpdate();
    	  
    	  //����� ó��
    	  if(row == 0) {
    		  throw new Exception("��Ͻ���");
    	  }
    	  
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  
	      }finally {
	    	  JDBCUtil.close(con, ps, rs);
	    	  
	      }
	      
	   }								//������� �׻� ��� �ݺ�. �� ������. spring���� ���� �̺κ��� ����� �ڵ差�� 1/10���� �پ��.
   
   
   
   public void bookDelete(int bookno) {
      
      String sql = "delete from book where bookno = ? ";
      Connection con = null;          
      PreparedStatement ps = null;
      ResultSet rs = null;
      int row = 0;
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //? �� ����
    	  ps.setInt(1, bookno);
    	  
    	  //sql ���� ����
    	  //ps.executeQuery();
    	  row= ps.executeUpdate();
    	  
    	  //����� ó��
    	  
    	  if(row == 0) {
    		  throw new Exception("��Ͻ���");
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
    	  //? �� ����
    	  ps.setInt(1, vo.getPrice());
    	  ps.setInt(2, vo.getBookno());
    	  
    	  //sql ���� ����
    	  //ps.executeQuery();
    	  row= ps.executeUpdate();
    	  
    	  //����� ó��
    	  
    	  if(row == 0) {
    		  throw new Exception("��Ͻ���");
    	  }
    	  
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  
	      }finally {
	    	  JDBCUtil.close(con, ps, rs);
	    	  
	      }
	      
   		}
   
   
   
   public List<BookVo> bookSearch(String condition, String keyword) {
      
      String sql = "select * from book where " +condition+ " like ? order by bookno desc"; 
      //select * from book where title like '%�ڹ�%';
      //select * from book where title like '%g��%';
      Connection con = null;          
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<BookVo> list = new ArrayList<BookVo>();
      
      try {
    	  con = JDBCUtil.getConnection();
    	  ps = con.prepareStatement(sql);
    	  //?�� �� ����
    	  ps.setString(1,"%"+keyword+"%");
    	  
    	  //����
    	  rs = ps.executeQuery();
    	  
    	  //����� ó��
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
      }return list; //����������� ��������!
      
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
    	  //? �� ����
    	  ps.setInt(1, bookno);
    	  
    	  //sql ���� ����
    	  rs = ps.executeQuery();
    	  
    	  //����� ó��
    	  while(rs.next()) {
    		  vo= new BookVo();
    		  vo.setBookno(rs.getInt("bookno"));	//���� Bookno , "bookno" �� ��ġ�ϴ� ��ó�� ���ʿ� �ڵ带 ©�� ��ġ�ذ� �������!!!
    		  vo.setPrice(rs.getInt("price"));		//�ȱ׷� ���߿� �� �հ� �Ӹ��� ����.��
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



