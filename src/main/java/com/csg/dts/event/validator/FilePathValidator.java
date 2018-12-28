package com.csg.dts.event.validator;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FilePathValidator implements Validator<String> {

	@Override
	public boolean isValid(String path) {
		File file = new File(path);
		return file.isFile();
	}

}
