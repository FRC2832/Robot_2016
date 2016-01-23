package org.usfirst.frc2832.Robot_2016.HID;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Utility class to save and load many gamepad states at once
 */
public class SavedStates {
	
	private static ArrayList<GamepadState> recordedStates;
	private static boolean recording = false;
	
	public static boolean startRecording() {
		if (recording)
			return (false);
		
		recordedStates = new ArrayList<>();
		recording = true;
		return (true);
	}
	public static void record(GamepadState gs) {
		// Must take place after the previous one
		if (!recording || (recordedStates.size() > 0 && 
				gs.timestamp <= recordedStates.get(recordedStates.size() - 1).timestamp))
			return;
		recordedStates.add(gs);
	}
	public static void stopRecording() {
		recording = false;
	}
	
	
	public static ArrayList<GamepadState> getRecordedStates() {
		return (recordedStates);
	}
	
	private static String convertNameToPath(String name) {
		return ("/home/lvuser/" + name + ".dat");
	}
	
	/**
	 * Saves the recorded GamepadStates to a file.
	 * @param name The name of the file, not including path or extension
	 * @throws IOException 
	 */
	public static void save(String name) throws IOException {
		saveFile(recordedStates, convertNameToPath(name));
	}
	
	/**
	 * Loads an ArrayList of GamepadStates from a file.
	 * @param name The name of the file, not including path or extension
	 * @throws FileNotFoundException 
	 */
	public static ArrayList<GamepadState> load(String name) throws FileNotFoundException {
		return (loadFile(convertNameToPath(name)));
	}
	
	/**
	 * This saves the file
	 * @param states Some states
	 * @throws IOException 
	 */
	protected static void saveFile(ArrayList<GamepadState> states, String path) throws IOException {
		FileOutputStream fo = new FileOutputStream(path);
		ObjectOutputStream oi = new ObjectOutputStream(fo);
		
		oi.writeObject(states);
		
		oi.close();
	}
	
	protected static ArrayList<GamepadState> loadFile(String path) throws FileNotFoundException {
		Object result = null;
		FileInputStream fi = new FileInputStream(path);
		ObjectInputStream oi;
		
		try {
			oi = new ObjectInputStream(fi);
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
