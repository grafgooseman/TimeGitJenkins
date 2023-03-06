package time;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, 
				() -> {Time.getTotalSeconds("10:00");});
	}
	
	@Test
	void testGetTotalSecondsBoundry() {
		int seconds = Time.getTotalSeconds("23:59:59");
		assertTrue("The seconds were not calculated properly", seconds==86399);
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetSeconds(String candidate) {
		String[] inputs = { "05:00:00", "05:15:15", "05:59:59" };
		int i = java.util.Arrays.asList(inputs).indexOf(candidate);
		int[] results = {00, 15, 59}; 
		
		assertTrue("The seconds were not calculated properly", 
				Time.getSeconds(candidate) == results[i]);
		
	}
	
	void testGetSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, 
				() -> {Time.getSeconds("10:00");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetTotalMinutes(String candidate) {
		String[] inputs = { "05:00:00", "05:15:15", "05:59:59" };
		int i = java.util.Arrays.asList(inputs).indexOf(candidate);
		int[] results = {00, 15, 59}; 
		
		assertTrue("The seconds were not calculated properly", 
				Time.getTotalMinutes(candidate) == results[i]);
		
	}

	void testGetTotalMinutesBad() {
		assertThrows(StringIndexOutOfBoundsException.class, 
				() -> {Time.getSeconds("10:00");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetTotalHours(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly", hours ==5);
	}
	
	void testGetTotalHoursBad() {
		assertThrows(StringIndexOutOfBoundsException.class, 
				() -> {Time.getSeconds("10:00");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:05:05:005", "00:00:00:000", "59:59:59:999" })
	void testGetMillisecondsGood(String candidate) {
		String[] inputs = { "05:05:05:005", "00:00:00:000", "59:59:59:999" };
		int i = java.util.Arrays.asList(inputs).indexOf(candidate);
		long[] results = {18305005, 0, 215999999l}; 
		
		assertTrue("The milliseconds ("+Time.getTotalMilliseconds(candidate)+") were not calculated properly", 
				Time.getTotalMilliseconds(candidate) == results[i]);
	}
	@Test
	void testGetMillisecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, 
				() -> {Time.getTotalMilliseconds("10:00");});
	}

}
