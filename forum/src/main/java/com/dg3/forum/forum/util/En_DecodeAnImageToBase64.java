//package com.dg3.forum.forum.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Base64;
//
//public class En_DecodeAnImageToBase64 {
//	public static String encoder(String imagePath) {
//	    String base64Image = "";
//	    File file = new File(imagePath);
//	    try (FileInputStream imageInFile = new FileInputStream(file)) {
//	        // Reading a Image file from file system
//	        byte imageData[] = new byte[(int) file.length()];
//	        imageInFile.read(imageData);
//	        base64Image = Base64.getEncoder().encodeToString(imageData);
//	    } catch (FileNotFoundException e) {
//	        System.out.println("Image not found" + e);
//	    } catch (IOException ioe) {
//	        System.out.println("Exception while reading the Image " + ioe);
//	    }
//	    return base64Image;
//	}
//	
//	public static void decoder(String base64Image, String pathFile) {
//		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
//			// Converting a Base64 String into Image byte array
//			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
//			imageOutFile.write(imageByteArray);
//		} catch (FileNotFoundException e) {
//			System.out.println("Image not found" + e);
//		} catch (IOException ioe) {
//			System.out.println("Exception while reading the Image " + ioe);
//		}
//	}
//	
//	public static void main(String[] args) {
//		System.out.println( En_DecodeAnImageToBase64.encoder("C:\\Users\\Phat\\Downloads\\2022-04-09_08-17-29.png")
//		);
//		
//	En_DecodeAnImageToBase64.decoder(En_DecodeAnImageToBase64.encoder("C:\\Users\\Phat\\Downloads\\2022-04-09_08-17-29.png") ,"‪C:\\Users\\Phat\\Downloads\\decode2022-04-09_08-17-29.png");
//	
//		/*
//		 * 	public static void main(String[] args) {
//		String imagePath = "C:\\Users\\Phat\\Downloads\\2022-04-09_08-17-29.png";
//		System.out.println("=================Encoder Image to Base 64!=================");
//		String base64ImageString = encoder(imagePath);
//		System.out.println("Base64ImageString = " + base64ImageString);
//
//		System.out.println("=================Decoder Base64ImageString to Image!=================");
//		decoder(base64ImageString, "C:\\Users\\Phat\\Downloads\\decode2022-04-09_08-17-29.png");
//		System.out.println();
//		System.out.println("DONE!");
//
//	}
//		 */
//}}
