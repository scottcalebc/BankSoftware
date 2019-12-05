package application.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Handlers reading and writing of serialized data to/from file
 * @author Christopher Caleb Scott
 *
 */
public class ApplicationFileWriter {
	
	/**
	 * data file used
	 */
	private static String dataFile = "posbank.data";
	
	public ApplicationFileWriter() {
		
	}
	
	/**
	 * Static method to write a list of users to file
	 * @param users
	 */
	public static void writeUserObjects(ArrayList<Users> users) {
		try {
			FileOutputStream f = new FileOutputStream(new File(dataFile));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(users);
			
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Reads data file and returns array list of users
	 * @return
	 */
	public static ArrayList<Users> readUserObjects() {
		ObjectInputStream oi;
		FileInputStream fi;
		ArrayList<Users>users = new ArrayList<Users>();
		try {
			fi = new FileInputStream(new File(dataFile));
			oi = new ObjectInputStream(fi);
			
			users = (ArrayList<Users>)oi.readObject();
			
			oi.close();
			fi.close();
			
			
		} catch (FileNotFoundException e) {
			return new ArrayList<Users>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
}
