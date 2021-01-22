package com.orcunsagirsoy.BEtest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orcunsagirsoy.BEtest.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@Service
public class WriteToFileService {

    @Autowired
    ObjectMapper mapper;
    FileWriter writer = new FileWriter("output.txt",false);

    public WriteToFileService() throws IOException {
    }

    public void writeToFile(List<Comments> listOfObjects) throws IOException{

        for(Comments comment: listOfObjects) {
                writer.write( "id : "+comment.getId()+ " , body : "+comment.getBody()+ " , postId : "+comment.getPostId() + System.lineSeparator());
                writer.flush();
        }

            //writer.close();
        }

    public void writeToFileTest(List<Comments> listOfObjects,File file) throws IOException{
        FileWriter writer = new FileWriter(file);
        for(Comments comment: listOfObjects) {
            writer.write( "id : "+comment.getId()+ " , body : "+comment.getBody()+ " , postId : "+comment.getPostId() + System.lineSeparator());
            writer.flush();
        }

        //writer.close();
    }

}
