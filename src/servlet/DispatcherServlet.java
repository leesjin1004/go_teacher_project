package servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.BookDAO_Mariadb;
import dao.UserDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import vo.BookVo;
import vo.UserVo;

@WebServlet("*.do")
@MultipartConfig(maxFileSize = 1024*1024*5)  //이게없으면 이미지 업로드 안된다함. 이미지 업로드 사이즈 지정해주는.

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String url = request.getRequestURI();
		String action = url.substring(url.lastIndexOf('/'));
		System.out.println("요청 : " + action);
		
		if (action.equals("/login.do")) {

			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			
			UserDAO_Mariadb dao = new UserDAO_Mariadb();
			UserService service = new UserServiceImpl(dao);
			
			UserVo login = service.login(id, pw);
			//System.out.println(login);
			
			if(login != null) {
				HttpSession session = request.getSession();
				session.setAttribute("login", login);
				
				getServletContext().
				getRequestDispatcher("/index.jsp").
				forward(request, response);
				
			}else {
				request.setAttribute("msg", "로그인 실패, 로그인 정보를 다시 입력 하세요.");
				
				getServletContext().
				getRequestDispatcher("/login.jsp").
				forward(request, response);
			}
			
			return;
		}
		if (action.equals("/logout.do")) {
			HttpSession session = request.getSession();
			if(session != null) {
				session.invalidate();
			}
			response.sendRedirect("./index.jsp");
			return;
		}
		if (action.equals("/addBook.do")) {
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);

			String title = request.getParameter("title");
			String publisher = request.getParameter("publisher");
			int price = Integer.parseInt(request.getParameter("price"));
			
			BookVo vo = new BookVo();
			vo.setPrice(price);
			vo.setTitle(title);
			vo.setPublisher(publisher);
			
			
			
			String path = request.getSession().getServletContext().getRealPath("/upload/");  //업로드서블릿2에서 복북해옴.
			System.out.println(path);
			
			Collection<Part> p = request.getParts();
			for(Part data :p) {
				if(data.getContentType() != null) {
					String fileName = data.getSubmittedFileName();
					if(fileName != null && fileName.length() != 0) {
						vo.setImg(fileName);
						data.write(path+fileName);
						//out.print("<img src='./upload/"+fileName+"'>");	 출력은 BookView에서 나와야하니 없애야함
					}
				}
			}
			
			
			
			service.bookAdd(vo);
			
			response.sendRedirect("bookList.do");
			return;
		}
		if (action.equals("/bookList.do")) {

			HttpSession session = request.getSession();
			UserVo login = (UserVo)session.getAttribute("login");
			if(login == null) {
	            request.setAttribute("msg", "로그인이 필요합니다.");
				
				getServletContext().
				getRequestDispatcher("/login.jsp").
				forward(request, response);
				return;
			}
			
			
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			List<BookVo> list = service.bookList();

			
			request.setAttribute("bookList", list);
			String page = "/BookList.jsp";
			
			getServletContext().
			getRequestDispatcher(page).
			forward(request, response);
			
			return;
		}
		if (action.equals("/bookSearch.do")) {
			String condition = request.getParameter("condition");
			String keyword =	request.getParameter("keyword")	;
			
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			List<BookVo> list = service.searchBook(condition, keyword);

			request.setAttribute("BookList", list);
			String page = "/BookList.jsp";
			
			getServletContext().
			getRequestDispatcher(page).
			forward(request, response);
			
			return;
		}
		if (action.equals("/viewBook.do")) {
			int bookno = Integer.parseInt(request.getParameter("bookno"));
			
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			BookVo vo = service.getBook(bookno);

			
			request.setAttribute("book", vo);
			String page = "/BookView.jsp";
			
			getServletContext().
			getRequestDispatcher(page).
			forward(request, response);
			
			return;
		}
		if (action.equals("/bookDelete.do")) {
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			
			String[] bookno = request.getParameterValues("bookno");
			
			for(String no :bookno) {
				service.bookDelete(Integer.parseInt(no));
			}
		
			response.sendRedirect("bookList.do");
			return;
		}
	}

}