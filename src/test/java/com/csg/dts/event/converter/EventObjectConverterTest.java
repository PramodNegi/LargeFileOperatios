package com.csg.dts.event.converter;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import com.csg.dts.event.model.Event;
import com.csg.dts.execption.ApplicationException;

public class EventObjectConverterTest {

	@Test
	void testConvert() throws ApplicationException, IOException {
		Converter<String, Event> eventObjectConverter = new EventObjectConverter();
		File file = ResourceUtils.getFile("classpath:F_Small_File.json");
		try (FileInputStream inputStream = new FileInputStream(file); Scanner sc = new Scanner(inputStream, "UTF-8")) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Event event = eventObjectConverter.convert(line);
				assertTrue("Id Mismatch", event.getId().startsWith("scsmbstgr"));
			}
		}
	}
	
	@Test()
	void testConvertWithIncorrectContent() throws ApplicationException, IOException {
		Converter<String, Event> eventObjectConverter = new EventObjectConverter();
		File file = ResourceUtils.getFile("classpath:Incorrect_File.json");
		try (FileInputStream inputStream = new FileInputStream(file); Scanner sc = new Scanner(inputStream, "UTF-8")) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Assertions.assertThrows(ApplicationException.class, () -> eventObjectConverter.convert(line));
			}
		}
	}
}
