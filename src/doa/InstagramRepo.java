package doa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import entity.User;

public interface InstagramRepo {
	ArrayList<User> list = new ArrayList<User>();
	HashMap<String, String> emailmap = new HashMap<>();
	HashMap<Long, String> phonemap = new HashMap<>();
	HashSet<String> usernameSet = new HashSet<>();
	ArrayList<String> allPosts = new ArrayList<>();
}
