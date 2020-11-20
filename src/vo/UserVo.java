package vo;

public class UserVo {
	private String id;			//varchar(10) primary key,
	private String password;	//varchar(10) not null,
	private String name;		//varchar(20),
	private String role;		//varchar(10)
	
	public UserVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserVo(String id, String password, String name, String role) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
		
		
	

}
