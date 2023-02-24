package easyb.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import easyb.service.PropertiesPagedService;
import easyb.service.vo.TitlePage;

public class HttpClientPropertiesService implements PropertiesPagedService{

    private static final int LIMIT = 20;
    private static String BASE_URL;
    private static String API_KEY;

    static{
        loadProps();
    }


    public HttpClientPropertiesService(){
    }

    @Override
    public TitlePage getProperties(int pageNumber) throws Exception {
       
        JsonPropertiesMapper objectMapper = new JsonPropertiesMapper();

        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(buildBaseUrl(pageNumber)))
        .GET()
        .header("X-Authorization", API_KEY)
        .header("accept", "application/json")
        .build();

        
        CompletableFuture<EasyProperties> cp = HttpClient.newHttpClient()
                         .sendAsync(request, BodyHandlers.ofString())
                         .thenApply(HttpResponse::body)
                         .thenApply(objectMapper::readValue);
        
                         
        return new ApiPropertiesMapper().mapFrom(cp.get());
    }

    private String buildBaseUrl(int pageNumber) {
        return BASE_URL + "page="+ pageNumber+ "&limit=" + LIMIT;
    }

    private static void loadProps() {
        String path = Thread.currentThread().getContextClassLoader().getResource("app.properties").getPath();
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            // throw unchecked exception. class can't init.
            throw new RuntimeException(e);
        }

        BASE_URL = props.getProperty("BASE_URL");
        API_KEY = props.getProperty("API_KEY");
    }

}   

