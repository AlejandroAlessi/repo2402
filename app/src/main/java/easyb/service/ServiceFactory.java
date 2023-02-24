package easyb.service;

import easyb.service.impl.HttpClientPropertiesService;

public class ServiceFactory {

    public static PropertiesPagedService getPropertiesPagedService() {
        // It'd be best if injected by Spring or any other DI lib, but for the sake 
        // of the exercise just mention the subject should be enough 
        return new HttpClientPropertiesService();
    }

}
