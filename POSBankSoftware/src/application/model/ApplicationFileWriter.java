package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ApplicationFileWriter {
	
	
	private static String userFilename = "users.txt";
	private static String accountsFilename = "accounts.txt";
	private static String transactionsFilename = "transactions.txt";
	
	private static boolean userState = false;
	private static boolean accountState = false;
	private static boolean transactionState = false;
	
	private static ArrayList<Users> users = new ArrayList<Users>();
	
	public ApplicationFileWriter() {
		
	}
	
	public static boolean getState() {
		return userState || accountState || transactionState;
	}
	
	
	public static ArrayList<Users> getUsers() {
		
		if (users.isEmpty()) {
			System.out.println("Reading users file");
			File userFile = new File(userFilename);
			
			ArrayList<Users> list = new ArrayList<Users>();
			try {
				Scanner inputstreamUsers = new Scanner(userFile);
				
				while(inputstreamUsers.hasNext()) {
					String[] data = inputstreamUsers.nextLine().split(",");
					list.add(new Users(data[1],data[2], Integer.parseInt(data[0])));
				}
				inputstreamUsers.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			users = list;
			
			return list;
		} else {
			System.out.println("Returning buffered users");
			return users;
		}	
		
	}
	
	public static void addUser(Users user) {
		if (!users.isEmpty() && users.stream().filter(x -> x.getUserName().equals(user.getUserName())).collect(Collectors.toList()).isEmpty()) {
			userState = true;
			users.add(user);
		} else {
			System.out.println("User exists not adding");
		}
	}
	
	public static void writeUsers() throws IOException {
	
		BufferedWriter writer = new BufferedWriter(new FileWriter(userFilename));
		for(Users user : users) {
			writer.write(user.toString());
		}
		writer.close();	
	}
	
	public static void writeUserObjects(ArrayList<Users> users) {
		try {
			FileOutputStream f = new FileOutputStream(new File("test.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			for(Users usr : users) {
				o.writeObject(usr);
			}
			
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Users> readUserObjects() {
		try {
			FileInputStream fi = new FileInputStream(new File("test.dat"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			while(true) {
				users.add((Users)oi.readObject());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("Read all users");
			return users;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	public static void close() {
		if (userState) {
			try {
				writeUsers();
			} catch (IOException e) {
				System.out.printf("ERROR: writing to file: %s\n", e.getMessage());
			}
		}
		if (accountState) {
			
		}
		if (transactionState) {
			
		}
		
	}
	
	
}
