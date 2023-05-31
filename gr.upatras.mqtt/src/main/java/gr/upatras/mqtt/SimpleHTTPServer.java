package gr.upatras.mqtt;

import java.net.*;
import java.net.http.*;

public class SimpleHTTPServer {
	
	private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

	public static void main(String[] args) throws Exception {
		SimpleHTTPServer obj = new SimpleHTTPServer();
//		final ServerSocket server = new ServerSocket(8080);
//		System.out.println("Listening for connection on port 8080 ....");
//		Socket socket = server.accept();
		
		
		
//		System.out.println("Testing 1 - Send Http GET request");
//        obj.sendGet();

        System.out.println("Testing 2 - Send Http POST request");
        obj.sendPost();
        
        

	}
	
//	private void sendGet() throws Exception {
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create("https://httpbin.org/get"))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        // print status code
//        System.out.println(response.statusCode());
//
//        // print response body
//        System.out.println(response.body());
//
//    }
	
	private void sendPost() throws Exception {
		String msg = "Eclipse is an IDE only a mother can love.";
        
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(msg))
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "text/plain;charset=UTF-8")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
        
        SimpleMqttClient smc = new SimpleMqttClient();
        smc.runClient(response.body());

    }

}
