/*
* Mocked api service that simulates getting properties.
* Better use Mockito or another mocking lib, but regarding this excercise 
* is just fine to code a simple mock.
**/

package easyb;

import java.io.IOException;
import java.net.URISyntaxException;

import easyb.service.PropertiesPagedService;
import easyb.service.impl.ApiPropertiesMapper;
import easyb.service.impl.EasyProperties;
import easyb.service.impl.JsonPropertiesMapper;
import easyb.service.vo.TitlePage;
import easyb.util.Utils;

public class MockedService implements PropertiesPagedService{

    private static final String PAGE_1_FILE = "PAGE_1.json";
    private static final String PAGE_2_FILE = "PAGE_2.json";

    private TitlePage titlePage1;
    private TitlePage titlePage2;
    private JsonPropertiesMapper jsonMapper = new JsonPropertiesMapper();

    public TitlePage getProperties(int pageNumber) throws Exception {
        loadPages();
        if (pageNumber == 1){
            return titlePage1;
        }
        else if(pageNumber == 2){
            return titlePage2;
        }
        else{
            // dont know the actual behavior of api because of expired / invalid tokens at the console
            // just simulate a behaviour which implies that request a page with a number bigger than max pages
            // existing is not valid and generates Exception
            throw new Exception("No more properties pages");
        }
    }

    private void loadPages() throws URISyntaxException, IOException {
        titlePage1 = buildPageFromFile(PAGE_1_FILE);
        titlePage2 = buildPageFromFile(PAGE_2_FILE);
    }

    private TitlePage buildPageFromFile(String pageFile) throws URISyntaxException, IOException {
        EasyProperties propsFromApi =jsonMapper.readValue(Utils.readFileFromResources(pageFile));
        TitlePage titlePage = new ApiPropertiesMapper().mapFrom(propsFromApi);
        return titlePage;
    }    
}