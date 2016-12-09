package mainAPI;

public class Post {
private String  userId;
private String id;
private String title;
private String body;

	public String getuserId() {
		return userId;
	}

	public String getid() {
		return id;
	}

	public String gettitle() {
		return title;
	}

	public String getbody() {
		return body;
	}
	public void setuserId(String userId) {
		this.userId = userId;
	}

	public void setid(String id) {
		this.id= id;
	}

	public void settitle(String  title) {
		this.title= title;
	}

	public void setbody(String body) {
		this.body= body;
	}
}
