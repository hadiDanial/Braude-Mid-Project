package unitTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.other.Report;

public class ReportTests {
	private String data1;
	private String data2;

	private HashMap<String, Number> map1;
	private HashMap<String, Number> map2;

	private Report report;

	@Before
	public void setUp() throws Exception {
		report = new Report();
		data1 = "1:5.3,3:15";
		data2 = "4:2.5,6:1,15:4.1";

		map1 = new HashMap<>();
		map2 = new HashMap<>();

		map1.put("4", 5.6);
		map1.put("5", 2);

		map2.put("24", 15);
		map2.put("28", 10);

		report = new Report();

	}

	@Test
	/**
	 * testing the conversion from hashmap to string for two days one after the other
	 * input HashMap
	 * outPut String
	 */
	public void testSetDataStringFromHashMapConsecutiveDays() {
		String expected = "4:5.6,5:2";
		report.setData(map1);
		String actual = report.getDataString();
		assertEquals(expected, actual);
	}
	@Test
	/**
	 * testing the conversion from hashmap to string for two days that are far from each other
	 * input HashMap
	 * outPut String
	 */
	public void testSetDataStringFromHashMapThatAreFar() {
		String expected = "24:15,28:10";
		report.setData(map2);
		String actual = report.getDataString();
		assertEquals(expected, actual);
	}

	@Test
	/**
	 * testing the conversion from String to HashMap for two days that are far from each other
	 * input String
	 * outPut HashMap
	 */
	public void testSetDataHashMapFromStringThatAreFar() {
		HashMap<String, Number> expected = map2;
		String in="24:15,28:10";
		report.setData(in);
		HashMap<String, Number> actual = report.getDataMap();
		assertEquals(expected, actual);
	}

	@Test
	/**
	 * testing the conversion from hashmap to string for two days one after the other
	 * input HashMap
	 * outPut String
	 */
	public void testSetDataHashMapFromStringConsecutiveDays() {
		HashMap<String, Number> expected = map1;
		String in="4:5.6,5:2";
		report.setData(in);
		HashMap<String, Number> actual = report.getDataMap();
		assertEquals(expected, actual);
	}

	@Test
	/**
	 * testing data by key (one Day of the month) by inputting a HashMap
	 * input String
	 * outPut Number
	 */
	public void testGetDataByKeyAndHashMap() {
		Number expected=5.6;
		report.setData(map1);
		Number actual=report.getDataByKey("4");
		assertEquals(expected, actual);
	}
	@Test
	/**
	 * testing data by key (one Day of the month) by inputting a HashMap
	 * input String
	 * outPut Number
	 */
	public void testGetDataByKeyAndString() {
		Number expected=4.1;
		report.setData(data2);
		Number actual=report.getDataByKey("15");
		assertEquals(expected, actual);
	}

}
