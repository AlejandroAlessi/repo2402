package easyb.service;

import easyb.service.vo.TitlePage;

public interface PropertiesPagedService{
    TitlePage getProperties(int pageNumber) throws Exception;
}