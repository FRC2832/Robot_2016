package org.usfirst.frc2832.Robot_2016.HID;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Utility class to save and load many gamepad states at once
 * /home/lvuser/autondata/FILENAME is where we should save them
 */
public class SavedStates {
	/**
	 * This saves the file
	 * @param states Some states
	 */
	public static void save(ArrayList<GamepadState> states, String path) {
		try {
			FileOutputStream fo = new FileOutputStream(path);
			ObjectOutputStream oi = new ObjectOutputStream(fo);
			
			oi.writeObject(states);
			
			oi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<GamepadState> load(String path) {
		Object result = null;
		try {
			FileInputStream fi = new FileInputStream(path);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			result = oi.readObject();
			
			oi.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (result instanceof ArrayList)
			return ((ArrayList<GamepadState>) result);
		
		return (null);
	}
}
