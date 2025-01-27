package com.ufo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufo.tests.vendarportal.VendorPortalTest;
import com.ufo.tests.vendarportal.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    // use genric type so you dont need to create many utilities for high test data
    public static <T> T getTestData (String path ,Class<T> type){
        try {
            InputStream stream = ResourceLoader.getResources(path);
            return objectMapper.readValue(stream, type);
        }catch (Exception e)
        {
            System.out.print("Unable to load the file");
            log.error("Unable to read test data {}",path,e);
        }
        return null;
    }



}
