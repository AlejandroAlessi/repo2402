package easyb.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Utils {

    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {
        URL resource = Utils.class.getClassLoader().getResource(filename);  
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));  
        return new String(bytes);  
    }
    
}
