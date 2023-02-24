package easyb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import easyb.service.vo.TitlePage;

public class ApiPropertiesMapper {

    public TitlePage mapFrom(EasyProperties easyProps){
        TitlePage page = new TitlePage(buildListOfNames(easyProps.getContent()),
                                     easyProps.getPagination().page,
                                     easyProps.getPagination().thereAreMorePages());

        return page;
    }

    private List<String> buildListOfNames(List<EasyProperties.Page> content) {
        if (content == null)
            return new ArrayList<String>();

        return content.stream().map(EasyProperties.Page::getTitle).collect(Collectors.toList());
    }

}
