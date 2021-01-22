package com.orcunsagirsoy.BEtest.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orcunsagirsoy.BEtest.model.Comments;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
public class WriteToFileServiceTest {


    private WriteToFileService writeToFileService;
    private Comments comments;
    private ObjectMapper mapper;



    @Before
    public void setUp() throws IOException {
        comments = new Comments();
        mapper = new ObjectMapper();

        try {
            writeToFileService=new WriteToFileService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getIdOfFirst() throws Exception {
        List<Comments> listOfDtos = Lists.newArrayList(
                new Comments(1L, "deneme testi 1", 10L), new Comments(2L, "deneme testi 2", 20L));

        String jsonArray = mapper.writeValueAsString(listOfDtos);
        Comments[] asArray = mapper.readValue(jsonArray, Comments[].class);
        assertEquals((Long)asArray[0].getId(),(Long) 6L);

    }

    @Test
    public void getBodyOfSecond() throws Exception {

        List<Comments> listOfDtos = Lists.newArrayList(
                new Comments(1L, "deneme testi 1", 10L), new Comments(2L, "deneme testi 2", 20L));

        String jsonArray = mapper.writeValueAsString(listOfDtos);
        Comments[] asArray = mapper.readValue(jsonArray, Comments[].class);
        assertEquals((String) asArray[1].getBody(),"deneme testi 2");

    }

    @Test
    public void checkWrittenFileIsCorrect() throws Exception {

        List<Comments> listOfDtos = Lists.newArrayList(
                new Comments(1L, "deneme testi 1", 10L), new Comments(2L, "deneme testi 2", 20L));
        String jsonArray = mapper.writeValueAsString(listOfDtos);
        Comments[] asArray = mapper.readValue(jsonArray, Comments[].class);


        File file =new File("outputTest.txt");
        writeToFileService.writeToFileTest(listOfDtos,file);
        try {
            Scanner sc = new Scanner(file);
            int i=0;
            while (sc.hasNextLine()){
                String expected=sc.nextLine();
                String actual="id : "+asArray[i].getId()+ " , body : "+asArray[i].getBody()+ " , postId : "+asArray[i].getPostId();
                assertEquals(expected,actual);
                i++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
