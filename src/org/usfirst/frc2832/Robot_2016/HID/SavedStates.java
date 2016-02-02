package org.usfirst.frc2832.Robot_2016.HID;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Utility class to save and load many gamepad states at once
 * This class has two parts:
 * 	- GamepadState saving/loading: used for recordable autonomous
 *  - Indexing: to keep track of the saved GamepadState arrays for dynamic loading upon
 *  robot launch
 *  
 *  It's pretty cool.
 */
public class SavedStates {
	
	private static ArrayList<GamepadState> recordedStates;
	private static ArrayList<String> index;
	private static boolean recording = false;
	
	public static boolean startRecording() {
		if (recording)
			return false;
		
		recordedStates = new ArrayList<>();
		recording = true;
		return true;
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
		return recordedStates;
	}
	
	private static String convertNameToPath(String name) {
		return "/home/lvuser/" + name;
	}
	/**
	 * Returns saved names of autonomous files from the index
	 * A NullPointerException will be thrown if you don't call loadIndex first 
	 * @return
	 */
	public static String[] getIndex() {
		if (index == null) {
			try {
				loadIndex();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return index.toArray(new String[index.size()]);
	}
	
	/**
	 * Saves the recorded GamepadStates to a file.
	 * This method also registers the file into the autonomous index.  If the index
	 * has not been loaded yet, it will load the current version.
	 * @param name The name of the file, not including path or extension
	 * @throws IOException 
	 */
	public static void save(String name) throws IOException {
		saveFile(recordedStates, convertNameToPath(name) + ".dat");
		
		if (index == null)
			loadIndex();
		
		if (!index.contains(name)) {
			index.add(name);
			saveIndex();
		}
	}
	
	/**
	 * Loads the indexing file, which should contain all of the autonomous filenames.
	 * @throws IOException
	 */
	public static void loadIndex() throws IOException {
		FileReader fr = new FileReader(convertNameToPath("index.txt"));
		BufferedReader br = new BufferedReader(fr);
		
		index = new ArrayList<>();
		
		// Read all lines into index
		String line;
		while ((line = br.readLine()) != null)
			index.add(line);
		
		fr.close();
	}
	
	private static void saveIndex() throws IOException {
		FileWriter fw = new FileWriter(convertNameToPath("index.txt"));
		BufferedWriter bw = new BufferedWriter(fw);
		
		// Writes all indices to the file; no newline at end!
		for (int i = 0; i < index.size(); i++) {
			if (i != 0)
				bw.newLine();
			bw.write(index.get(i));
		}
		
		bw.flush();
		
		fw.close();
	}
	
	/**
	 * Loads an ArrayList of GamepadStates from a file.
	 * @param name The name of the file, not including path or extension
	 * @throws FileNotFoundException 
	 */
	public static ArrayList<GamepadState> load(String name) throws FileNotFoundException {
		return loadFile(convertNameToPath(name) + ".dat");
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
		
		fo.close();
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
			return (ArrayList<GamepadState>) result;
		
		return null;
	}
}
