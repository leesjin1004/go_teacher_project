package test;

import java.sql.Connection;

import util.JDBCUtil;

public class test01 {

   public static void main(String[] args) {

	   System.out.println("Connetion test");
      Connection con = JDBCUtil.getConnection();
      System.out.println(con); //����� �ǳ� con ����
      JDBCUtil.close(con, null, null);
      
   }

}