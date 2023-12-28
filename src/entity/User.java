package entity;

import java.util.ArrayList;

public class User {
	private static int postNo = 1;
	private static int increasedID = 1000;
	private int id;
	private String name;
	private String username;
	private String email;
	private long phone;
	private String password;
	private int age;
	private Content content;

	public User(String name, String username, String email, long phone, String password, int age) {
		this.id = increasedID++;
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.age = age;
		content = new Content();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<String> getPosts() {
		return content.getPosts();
	}

	public void addPosts(String content) {
	    long timestamp = System.currentTimeMillis(); // Current time in milliseconds
	    String post = content + ":" + timestamp;
	    this.content.getPosts().add(post);
	}

	public ArrayList<String> getStory() {
		return this.content.getStory();
	}

	public void setStory(String story) {
		this.content.getStory().add(story);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + ", age=" + age + ", content=" + content + "]";
	}



}
