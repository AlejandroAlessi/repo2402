package easyb;

import org.junit.jupiter.api.Test;

import easyb.service.impl.HttpClientPropertiesService;
import easyb.service.vo.TitlePage;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientPropertiesServiceTest {
    @Test void should_returnAPage_when_FirstPageRequested() {
        HttpClientPropertiesService service = new HttpClientPropertiesService();

        TitlePage page = null;

        try{
            page = service.getProperties(0);
        }catch(Exception e){
            e.printStackTrace();   
            fail("should not fail");
        }

        assertNotNull(page,"Page shouldnt be null");
        assertTrue(!page.getPageNotProcessed(),"Page shouldnt have error");
    }
}
