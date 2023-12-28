package serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import controller.InstagramApp;
import doa.InstagramRepo;
import entity.PostComparator;
import entity.User;
import exceptions.*;
import service.Instagram;

public class InstagramImpl implements Instagram, InstagramRepo {
	private Scanner sc = new Scanner(System.in);
	{
		list.add(new User("Niyaj", "niyaj123", "niyaj@gmail.com", 1234567890L, "niyaj@123", 23));
		list.add(new User("Shubham", "Shubham123", "Shubham@gmail.com", 3234567890L, "Shubham@123", 23));
		list.add(new User("Solomon", "Solomon123", "Solomon@gmail.com", 4234567890L, "Solomon@123", 23));
		list.add(new User("Junaid", "junaid123", "junaid@gmail.com", 5234567890L, "junaid@123", 23));
		for (User user : list) {
			user.addPosts("post" + user.getId());
			user.setStory("Story" + user.getId());
			emailmap.put(user.getEmail(), user.getPassword());
			phonemap.put(user.getPhone(), user.getPassword());
			usernameSet.add(user.getUsername());

		}
//		System.out.println(list);
//		System.out.println(emailmap);
//		System.out.println(phonemap);
//		System.out.println(usernameSet);

	}

	private User currentUser;
	private int loginAttempts = 3;

	@Override
	public void register() {
		System.out.print("\nEnter your name: ");
		String name = sc.nextLine();

		int age = 0;
		while (age <= 0 && age > 100) {
			try {
				System.out.print("Enter your age: ");
				age = sc.nextInt();
				if (age <= 0 && age > 100) {
					System.out.println();
					System.err.println("Age must be a in range of 0 to 100");
				}
			} catch (Exception e) {
				System.out.println();
				System.err.println("Invalid age input.");
				System.out.println("Age must be a Number.");

			}
			sc.nextLine();
		}

		String username = "";
		while (true) {
			System.out.print("Enter your username: ");
			username = sc.next();
			if (!username.matches("[a-z0-9_]{5,20}")) {
				System.out.println();
				System.err.println("Invalid username format. Example: niyaj123, _niyaj123_");
			} else {
				if (usernameSet.contains(username)) {
					System.out.println();
					System.err.println(username + " already exists");
				} else {
					usernameSet.add(username);
					break;
				}
			}
		}

		String email = "";
		while (true) {
			System.out.print("Enter your email: ");
			email = sc.next();
			if (!email.matches("[a-zA-Z0-9._%-]+@gmail[.]com")) {
				System.out.println();
				System.err.println("Invalid email format. Example: niyaj@gmail.com");
			} else {
				if (emailmap.containsKey(email)) {
					System.out.println();
					System.err.println("Email already exists");
				} else {
					break;
				}
			}
		}

		long phone = 0;
		while (true) {
			try {
				System.out.print("Enter your phone number: ");
				phone = sc.nextLong();
				if (Long.toString(phone).length() != 10) {
					throw new RegistrationError("Phone number must be a 10-digit number.");
				}
				if (phonemap.containsKey(phone)) {
					System.out.println();
					System.err.println("Phone number already exists");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println();
				System.err.println("Invalid phone number");
			}
			sc.nextLine();
		}
		sc.nextLine();

		System.out.print("Enter your password: ");
		String password = sc.nextLine();

		emailmap.put(email, password);
		phonemap.put(phone, password);
		User user = new User(name, username, email, phone, password, age);
		list.add(user);
		currentUser = user;
		instaApp();
	}

	@Override
	public void login() {
		while (loginAttempts > 0) {
			System.out.println();
			System.out.print("Enter your Phone Number/Email: ");
			String userInput = sc.nextLine();
			int phone = 0;
			String email = null;

			if (userInput != null && userInput.matches("[a-zA-Z0-9._%-]+@gmail[.]com")) {
				email = userInput;
			} else if (userInput.matches("[0-9]+{10}")) {
				phone = Integer.parseInt(userInput);
			} else {
				System.out.println();
				System.err.println("Invalid email/phone number");
				continue;
			}

			System.out.print("Enter your password: ");
			String password = sc.nextLine();

			boolean loggedIn = false;
			for (User user : list) {
				if (user != null
						&& ((email != null && user.getEmail().equalsIgnoreCase(email))
								|| (phone != 0 && user.getPhone() == phone))
						&& user.getPassword().equalsIgnoreCase(password)) {
					System.out.println("\n\t******Login Successful*******\n");
					currentUser = user;
					loginAttempts = 3;
					instaApp();
					loggedIn = true;
					break;
				}
			}
			if (!loggedIn) {
				System.out.println();
				try {
					throw new LoginError("Invalid Credentials");
				} catch (LoginError e) {
					e.printStackTrace();
					loginAttempts--;
					System.err.println("Login attempts remaining: " + loginAttempts);
				}
			}
		}

		if (loginAttempts <= 0) {
			System.out.println("You are out of login attempts.");
			System.out.println("Exiting the app.....");
			InstagramApp.main(null);
		}
	}

	@Override
	public void addPost() {
		System.out.print("\nEnter post : ");
		currentUser.addPosts(sc.nextLine());
		System.out.println("Post added successfully");
		instaApp();
	}

	@Override
	public void addStory() {
		System.out.print("\nEnter Story : ");
		currentUser.setStory(sc.nextLine());
		System.out.println("Story added successfully");
		instaApp();
	}

	@Override
	public void deletePost() {
		boolean flag = false;
		System.out.print("\nEnter post to delete : ");
		String deletePost = sc.nextLine();
		for (int i = 0; i < currentUser.getPosts().size(); i++) {
			String post = currentUser.getPosts().get(i);
			if (post != null && post.equals(deletePost)) {
				System.out.println("Are you sure? (y/n) ");
				char ch = sc.next().toLowerCase().charAt(0);
				if (ch == 'y') {
					currentUser.getPosts().remove(i);
					System.out.println("Post deleted successfully");
				}
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("Enter a valid post to delete");
			deletePost();
		}
		instaApp();
	}

	@Override
	public void deleteStory() {
		boolean flag = false;
		System.out.print("\nEnter story to delete : ");
		String deleteStory = sc.nextLine();
		for (int i = 0; i < currentUser.getStory().size(); i++) {
			String story = currentUser.getStory().get(i);
			if (story != null && story.equals(deleteStory)) {
				System.out.println("Are you sure? (y/n) ");
				char ch = sc.next().toLowerCase().charAt(0);
				if (ch == 'y') {
					currentUser.getStory().remove(i);
					System.out.println("Story deleted successfully");
				}
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("Enter a valid story to delete");
			deleteStory();
		}
		instaApp();
	}

	@Override
	public void instaApp() {
		System.out.println("\n_________________________________________________________________");
		System.out.println("*+*+*+*+*+*+*+*+*+*+*+ Instagram +*+*+*+*+*+*+*+*+*+*+*| " + currentUser.getName() + " |");
		System.out.println("_________________________________________________________________");
		System.out.println("\n***********Story************");
		if (currentUser.getStory().size() != 0) {
			for (String myStory : currentUser.getStory()) {
				System.out.print(myStory + " ");
			}
		} else {
			System.out.print("My Story");
		}
		System.out.print(" | ");
		for (User users : list) {
			if (users != null && users != currentUser) {
				for (String story : users.getStory()) {
					System.out.print(story + " ");
				}
				System.out.print(" | ");
			}

		}

		System.out.println("\n******Instagram Feed*******");
		for (User user : list) {
			if (user != null) {
				List<String> userPosts = user.getPosts();
				Collections.sort(userPosts, new PostComparator());
				for (String post : userPosts) {
					System.out.println("\t" + post.split(":")[0]);
				}
			}
		}

		System.out.println(
				"\n1. Add Post. \n2. Add Story \n3. Delete Story. \n4. Delete Story. \n5. Account\n6. Close App.");
		System.out.print("Enter your option : ");
		int option = 0;
		try {
			option = sc.nextInt();
		} catch (Exception e) {
			System.out.println();
			System.err.println("Please enter a valid numeric option.");
			sc.nextLine();
			instaApp();
		}
		sc.nextLine();
		switch (option) {
		case 1:
			addPost();
			break;
		case 2:
			addStory();
			break;
		case 3:
			deletePost();
			break;
		case 4:
			deleteStory();
			break;
		case 5:
			viewAccount();
			break;
		case 6:
			System.exit(0);
		default:
			System.out.println("\nInvalid Option.\n");
		}

	}

	@Override
	public void viewAccount() {
		System.out.println("\n+++++++ User Details +++++++");
		System.out.println("Name : " + currentUser.getName());
		System.out.println("Username : " + currentUser.getUsername());
		System.out.println("Age : " + currentUser.getAge());
		System.out.println("Email : " + currentUser.getEmail());
		System.out.println("Phone Number : " + currentUser.getPhone());
		System.out.println("\n\t****My Posts****");
		int i = 1;
		for (String post : currentUser.getPosts()) {
			System.out.print(post + "\t");
			if (i % 3 == 0) {
				System.out.println();
			}
		}

		System.out.println(
				"\n1. Go to Feed.\n2. Add Post.\n3. Add Story. \n4. Delete Post. \n5. Delete Story.\n6. Update Details.\n7. Switch Account.\n8. Log out");
		System.out.print("Enter your choice : ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			instaApp();
			break;
		case 2:
			addPost();
			break;
		case 3:
			addStory();
			break;
		case 4:
			deletePost();
			break;
		case 5:
			deleteStory();
			break;
		case 6:
			updateAccount();
			break;
		case 7:
			login();
			break;
		case 8:
			System.exit(0);
		default:
			viewAccount();
		}

	}

	@Override
	public void updateAccount() {
		System.out.println("What do you want to update?");
		System.out.println("\n1. Name\n2. Username\n3. Age\n4. Email. \n5.Phone Number. \n6. Password \n7. Exit");
		System.out.print("Enter you choice : ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			System.out.print("Enter your name : ");
			currentUser.setName(sc.next());
			break;
		case 2:
			String username;
			while (true) {
				System.out.print("Enter your username : ");
				username = sc.nextLine();
				if (!username.matches("[[a-z_]+[0-9]*[a-z_]]{5,20}")) {
					System.out.println(
							"\nEnter a valid username of minimum length 5 containing only (a, b, c, ....z), number(0, 1, 2 ,...., 9), and underscore( _ )");
					System.out.println("Example : _niyaj123_, niyaj123\n");
				} else {
					break;
				}
			}
			break;
		case 3:
			System.out.print("Enter your age : ");
			int age = sc.nextInt();
			sc.nextLine();
			currentUser.setAge(age);
			break;
		case 4:
			String email;
			while (true) {
				System.out.print("Enter your email : ");
				email = sc.nextLine();
				if (!email.matches("[a-zA-Z0-9._%-]+@gmail[.]com")) {
					System.out.println("\nEnter a valid email");
					System.out.println("Example : niyaj@gmail.com, niyaj.123@gmail.com\n");
				} else {
					break;
				}
			}
			break;
		case 5:
			long phone;
			while (true) {
				System.out.print("Enter your phone number : ");
				phone = sc.nextLong();
				sc.nextLine();
				if (!Long.toString(phone).matches("[0-9]{10}")) {
					System.out.println("\nEnter 10 digit phone number containing only 0, 1, 2, ....., 9\n");
				} else {
					break;
				}
			}
			break;
		case 6:
			System.out.print("Enter your Password : ");
			currentUser.setPassword(sc.nextLine());
			break;
		case 7:
			break;
		default:
			System.out.println("!!!! Invalid choice !!!!!");
			updateAccount();
		}
		System.out.println("Updated successfully");
		viewAccount();

	}

}
