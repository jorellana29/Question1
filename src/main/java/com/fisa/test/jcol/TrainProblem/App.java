package com.fisa.test.jcol.TrainProblem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	TrainsGraph graph;
    	String str = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
    	//String str = readFromFile("src/main/resources/input.txt");
        graph = new TrainsGraph(str);
        
        //Test1
        
        //String path = "A-B-P";
        //int result = graph.findDistOfPath(path.split("-"));
        //System.out.println(result);
        //Assert.assertEquals(9, result);
        
        //int dist = graph.findShortestDist("A", "C");
        //System.out.println(dist);

        //tEST 7
        
        //int count = graph.countPathByStop("A", "C" , 4);
        //System.out.println("test6: A->C, count:" + count);
        
        String path = "A-E-D";
        int result = graph.findDistOfPath(path.split("-"));
        System.out.println(result);
        
        //int count = graph.countPathByDist("C", "C" , 30);
        //System.out.println("test10:C->C,count:"+count);

    }
    
    public String readFromFile(String fileName){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("readFromFile file not found");
        }catch (IOException e){
            System.out.println("readFromFile io exception,"+e.getMessage());
        }finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    System.out.println("reader close exception,"+e.getMessage());
                }
            }
        }
        return sb.toString();
    }

    

}
