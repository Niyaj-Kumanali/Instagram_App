package controller;

import java.util.Scanner;
import service.Instagram;
import serviceImpl.InstagramImpl;

public class InstagramApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Instagram app = new InstagramImpl();
        System.out.println("_______________________________________________________");
        System.out.println("*+*+*+*+*+*+*+*+*+*+*+ Instagram +*+*+*+*+*+*+*+*+*+*+*");
        System.out.println("_______________________________________________________");

        while (true) {
            try {
                System.out.println();
                System.out.println("1. New User? Register");
                System.out.println("2. Already have an account? Login");
                System.out.println("3. Close App");
                int option;
                System.out.print("Enter your option : ");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        app.register();
                        break;
                    case 2:
                        app.login();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println();
                        System.err.println("!!!! Invalid option !!!!");
                }
            } catch (Exception e) {
                System.out.println();
                System.err.println("Please enter a valid numeric option.");
                sc.nextLine(); 
            }
        }
    }
}
