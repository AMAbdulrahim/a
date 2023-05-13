package com.example.programJFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpGetRequestSender {

  public static void main(String[] args) throws IOException {
	  //ADMIN TEST
	  //Admins dont have emails
	  User u1 = logInHandler("Ahmed", "8377");
	  System.out.println("Login successful with name: " + u1.name + ", and Type: " + u1.type);
	  
	  User u2 = userHandler("Mohammed");
	  System.out.println("User exists with name: " + u2.name + ", and Type: " + u2.type);
	  System.out.println();
	  //STUDENT TEST
	  User u3 = logInHandler("2892", "2753");
	  System.out.println("Login successful with name: " + u3.name + ", and Type: " + u3.type);
	  
	  User u4 = userHandler("6992");
	  System.out.println("User exists with name: " + u4.name + ", and Type: " + u4.type);
  }
  
  public static User logInHandler(int id, String password) throws IOException {
	  return logInHandler(("" + id), password);
  }
  
  public static User logInHandler(String username, String password) throws IOException {
	  String url = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn";
	  Map<String, String> params = Map.of("username", username, "password", password);
	  String response = sendGetRequest(url, params);
	  if(response == null)
		  return null;
	  response = response.replace("{", "");
	  response = response.replace("}", "");
	  response = response.replace("\"name\":", "");
	  response = response.replace("\"type\":", "");
	  response = response.replace(",", "");
	  // System.out.println("Full response: "+response+"\n");
	  
	  if(response.equals("\"admin\""))
		  return new User(username, "admin", null);
	  
	  int index1 = response.indexOf('"')+1;
	  int index2 = response.indexOf('"', index1 + 1);
	  
	  String name = response.substring(index1, index2);
	  index1 = index2 + 2;
	  index2 = response.indexOf('"', index1 + 1);
	  String type = response.substring(index1, index2);
	  
//	  System.out.println(name);
//	  System.out.println(type);
//	  System.out.println(email);
	  return new User(name, type,username);
  }
  
  public static User userHandler(int id) throws IOException {
	  return userHandler(("" + id));
  }
  
  public static User userHandler(String username) throws IOException {
	  String url = "https://us-central1-swe206-221.cloudfunctions.net/app/User";
	  Map<String, String> params = Map.of("username", username);
	  String response = sendGetRequest(url, params);
	  if(response == null)
		  return null;
	  response = response.replace("{", "");
	  response = response.replace("}", "");
	  response = response.replace("\"name\":", "");
	  response = response.replace("\"type\":", "");
	  response = response.replace(",", "");
	  // System.out.println("Full response: "+response+"\n");
	  
	  if(response.equals("\"admin\""))
		  return new User(username, "admin", null);
	  
	  int index1 = response.indexOf('"')+1;
	  int index2 = response.indexOf('"', index1 + 1);
	  
	  String name = response.substring(index1, index2);
	  index1 = index2 + 2;
	  index2 = response.indexOf('"', index1 + 1);
	  String type = response.substring(index1, index2);
	  
	  
//	  System.out.println(name);
//	  System.out.println(type);
//	  System.out.println(email);
	  return new User(name, type, username);
  }
  
  public static String sendGetRequest(String url, Map<String, String> params) throws IOException {
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    String response = "";
    
    try {
      // Build query string from parameters
      StringBuilder queryString = new StringBuilder();
      for (String key : params.keySet()) {
        if (queryString.length() > 0) {
          queryString.append("&");
        }
        queryString.append(key).append("=").append(params.get(key));
      }
      String fullUrl = url + "?" + queryString.toString();
      
      // Create connection object
      URL requestUrl = new URL(fullUrl);
      connection = (HttpURLConnection) requestUrl.openConnection();
      
      // Set request method
      connection.setRequestMethod("GET");
      
      // Get response code
      int responseCode = connection.getResponseCode();
      // System.out.println("Response code: " + responseCode);
      if(responseCode != 200)
    	  return null;
      
      // Get response body
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder responseBody = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        responseBody.append(line);
      }
      response = responseBody.toString();
    } finally {
      // Clean up resources
      if (reader != null) {
        reader.close();
      }
      if (connection != null) {
        connection.disconnect();
      }
    }
    
    return response;
  }
}