package com.dg3.forum.forum.util;

import org.apache.commons.io.FilenameUtils;

public class GetNameExtensionsForbase64 {
	public static String getPartExtensions(String nameFile) {
		return "data:image/"+FilenameUtils.getExtension(nameFile)+";base64,";
	}
	
}
