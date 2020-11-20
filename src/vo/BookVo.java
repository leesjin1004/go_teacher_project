package vo;

public class BookVo {

	   private int bookno     ;  //int(4) PRIMARY KEY 
	   private String title   ;
	   private String publisher ;
	   private int price ;
	   private String img;
	   
	   
	   public BookVo() {    }
	   
	   public BookVo(int bookno, String title, String publisher, int price, String img ) {
	      this.bookno =bookno;
	      this.title = title;
	      this.publisher = publisher;
	      this.price = price;
	      this.img = img;
	   }
	   
	     public int getBookno() {
	         return bookno;
	      }
	      
	      public void setBookno(int bookno) {
	         this.bookno = bookno;
	      }
	      public String getTitle() {
	         return title;
	      }
	      public void setTitle(String title) {
	         this.title = title;
	      }
	      public String getPublisher() {
	         return publisher;
	      }
	      public void setPublisher(String publisher) {
	         this.publisher = publisher;
	      }
	      public int getPrice() {
	         return price;
	      }
	      public void setPrice(int price) {
	         this.price = price;
	      }
	      public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public BookVo(int bookno) {
	         super();
	         this.bookno = bookno;
	      }

		@Override
		public String toString() {
			return "BookVo [bookno=" + bookno + ", title=" + title + ", publisher=" + publisher + ", price=" + price
					+ ", img=" + img + "]";
		}
	      


	      
	      
	      
	      
	   }