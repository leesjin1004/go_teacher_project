package util; //Ŀ�ؼǸ� ���ͼ� �ڿ��ݳ��ϴ� ����

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

   public static Connection getConnection() {
      Connection con = null;
      
      Properties p = new Properties();
      try {
         p.load(new FileInputStream("C:/lib/dbinfo.txt"));
         
         //�̷��� �����͸� ������� Ŀ�ؼ��� ����
         String driver = p.getProperty("_driver"); //intellJ ���Ͽ� �ִ� ������ ����������  _�� ���� ����Ŭ����!
         String url = p.getProperty("_url");
         String user = p.getProperty("_user");
         String pw = p.getProperty("_pw"); 
         
         Class.forName(driver);
         
         con = DriverManager.getConnection(url, user, pw);
         
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return con;
   }
   
   public static void close(Connection con , Statement st , ResultSet rs ) { //�ݳ��ؾ� �� �ڿ���

      //�ڿ��ݳ��ϴ� ���
      try {
         if(rs != null) rs.close(); //rs�� ���� ��� �� �ȴٰ� ��.. st con�� ��������
         if(st != null) st.close();
         if(con != null) con.close();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   
}



