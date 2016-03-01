package org.usfirst.frc2832.Robot_2016.vision;

import java.util.NavigableMap;
import java.util.TreeMap;

public class InterpolatedLookupTable {
	private NavigableMap<Double, Double> map;
	
	public InterpolatedLookupTable() {
		map = new TreeMap<>();
	}
	
	public void put(double key, double value) {
		map.put(key, value);
	}
	
	/**
	 * Gets a value, interpolating linearly
	 * @param key The key, or x-coordinate
	 * @return The value at key, or the analytic expansion of it
	 */
	public double get(double key) {
		if (map.containsKey(key))
			return (map.get(key));
		
		Double keyHigh = map.higherKey(key);
		Double keyLow  = map.lowerKey(key);
		
		if (keyHigh == null)
			keyHigh = map.lastKey();
		if (keyLow == null)
			keyLow = map.firstKey();
		
		double valLow = map.get(keyLow);
		double valHigh = map.get(keyHigh);
		
		if (keyHigh == keyLow)
			return (valLow);
		
		double t = (key - keyLow) / (keyHigh - keyLow);
		return ((1 - t) * valLow + (t) * valHigh);
	}
}
