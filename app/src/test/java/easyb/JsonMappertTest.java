package easyb;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import easyb.service.impl.EasyProperties;
import easyb.service.impl.JsonPropertiesMapper;
import easyb.util.Utils;

public class JsonMappertTest {

    private static String JSON_FROM_API = null;

    @BeforeAll static void initProperJsonFromApi(){
        try {
            JSON_FROM_API = Utils.readFileFromResources("ResponseFromApi.json");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            // tests will break and thats right
            return;
        }
    }

    @Test void should_convertToPropertiesPage_when_ProperJSONIsProvided() {
        JsonPropertiesMapper mapper  = new JsonPropertiesMapper();

        try{
            assertTrue( mapper.readValue(JSON_FROM_API) instanceof EasyProperties);
        } catch (Exception e) {
            e.printStackTrace();
            fail("failed because couldnt convert json from api to the type declared", e);
        }
    }
}
