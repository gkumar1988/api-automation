package com.example.apiautomation.model;

import lombok.Getter;

@Getter
public class RunTestRequestBody {

    private String xmlFile;
    private String environment;

    public String getXmlFile() {
        return xmlFile;
    }

    public String getEnvironment() {
        return environment;
    }


}
