package com.orcunsagirsoy.BEtest.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orcunsagirsoy.BEtest.model.Comments;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

public class ReadFromAPIServiceTest {
     static final String uri = "https://my-json-server.typicode.com/typicode/demo/comments";

     Comments comments;
     ObjectMapper mapper;
     RestTemplate restTemplate;


    @Before
    public void setUp() throws IOException {
        comments = new Comments();
        mapper = new ObjectMapper();
        restTemplate=new RestTemplate();

    }


    @Test
    public void checkFetchedResult() throws Exception {
        List<Comments> listCommandObjects=null;
        String result = restTemplate.getForObject(uri, String.class);
        try {
            listCommandObjects = mapper.readValue(result, new TypeReference<List<Comments>>(){});
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        assertEquals((Long)listCommandObjects.get(0).getId(),(Long) 1l);
        assertEquals(listCommandObjects.get(0).getBody(),"some comment");
        assertEquals((Long)listCommandObjects.get(0).getPostId(),(Long) 1l);
        assertEquals((Long)listCommandObjects.get(1).getId(),(Long) 2l);
        assertEquals(listCommandObjects.get(1).getBody(),"some comment");
        assertEquals((Long)listCommandObjects.get(1).getPostId(),(Long) 1l);
    }



}
