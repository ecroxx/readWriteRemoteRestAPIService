package com.orcunsagirsoy.BEtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orcunsagirsoy.BEtest.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReadFromAPIService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    RestTemplate restTemplate;
    public ReadFromAPIService(){
    }

    public List<Comments> fetchJSONandConvert(String result) throws JsonProcessingException {


        List<Comments> listCommandObjects = mapper.readValue(result, new TypeReference<List<Comments>>(){});
        return listCommandObjects;
    }
}
