package br.com.project.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Long> decodeLongList(String numerosString){
		
		String[] vet = numerosString.split(",");
		
		return Arrays.asList(vet).stream()
					.map( num -> Long.valueOf(num))
					.collect(Collectors.toList());
		
	}

}
