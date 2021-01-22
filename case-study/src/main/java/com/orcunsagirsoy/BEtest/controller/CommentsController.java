package com.orcunsagirsoy.BEtest.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orcunsagirsoy.BEtest.service.ReadFromAPIService;
import com.orcunsagirsoy.BEtest.service.WriteToFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orcunsagirsoy.BEtest.model.Comments;



@RestController
public class CommentsController {
    private static final String uri = "https://my-json-server.typicode.com/typicode/demo/comments";


    public CommentsController() throws IOException {
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
}

    @Autowired
    WriteToFileService writeToFileService;

    @Autowired
    ReadFromAPIService readFromAPIService;


    @RequestMapping(method = RequestMethod.GET, path = "/getComments")
    public ResponseEntity<String> getComments() throws IOException {
        List<Comments> listCommandObjects;
        RestTemplate restTemplate=new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        try{
            listCommandObjects = readFromAPIService.fetchJSONandConvert(result);
        }catch (JsonProcessingException e){
            return new ResponseEntity<>("Error Code 404 :JSON Fetch error from remote API ",HttpStatus.NOT_FOUND);
        }

        try {
            writeToFileService.writeToFile(listCommandObjects);

        }catch (IOException e){
            return new ResponseEntity<>("Error Code 500 : File Write Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> defaultPath() {
        return new ResponseEntity<String>("Error Code 400 : Invalid Request ", HttpStatus.BAD_REQUEST);
    }
}