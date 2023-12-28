package entity;

import java.util.ArrayList;

public class Content{
	private ArrayList<String> posts = new ArrayList<String>();
	private ArrayList<String> story = new ArrayList<String>();

	public ArrayList<String> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<String> posts) {
		this.posts = posts;
	}

	public ArrayList<String> getStory() {
		return story;
	}

	public void setStory(ArrayList<String> story) {
		this.story = story;
	}

	@Override
	public String toString() {
		return "Content [posts=" + posts + ", story=" + story + "]";
	}
	
	


}
