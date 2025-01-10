package uz.pdp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create("https://en.wikipedia.org/wiki/Wealth_of_Elon_Musk#:~:text=Elon%20Musk%20is%20the%20wealthiest,net%20worth%20of%20$400%20billion."))
                .build();

//        HttpResponse<String> response = client
//                .send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println(response);
//        System.out.println("response.body() = " + response.body());
//        System.out.println("response.headers() = " + response.headers());
//        User user = new User();
//        user.setId(1);
//        user.setName("So'ta");
//        user.setSurname("So'taqo'ziyev");
//        user.setPhone("92349234");
//
//        System.out.println(user);

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        HttpResponse<String> response2 = client
                .send(request2, HttpResponse.BodyHandlers.ofString());

        System.out.println("response2 = " + response2.body());
    }
}