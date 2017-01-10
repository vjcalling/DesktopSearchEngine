package com.project.desktopsearchengine.files;

import java.util.HashMap;

public interface FileHandler {

	public HashMap<String, Integer> readFileAndGetWords(String filePath);
}
