package com.fisa.test.jcol.TrainProblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrainProblemTest {

	private Process grafo;

	@Before
	public void initTrainProblem() {
        
		 String str = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		 grafo = new Process(str);
    }
	
	@Test
    public void test1() {
        String path = "A-B-C";
        int result = grafo.findDistOfPath(path);
        Assert.assertEquals(9, result);
    }
 
    @Test
    public void test2() {
        String path = "A-D";
        int result = grafo.findDistOfPath(path);
        Assert.assertEquals(5, result);
    }
 
    @Test
    public void test3() {
        String path = "A-D-C";
        int result = grafo.findDistOfPath(path);
        Assert.assertEquals(13, result);
    }
 
    @Test
    public void test4() {
        String path = "A-E-B-C-D";
        int result = grafo.findDistOfPath(path);
        Assert.assertEquals(22, result);
    }
 
    @Test
    public void test5() {
        String path = "A-E-D";
        int result = grafo.findDistOfPath(path);
        Assert.assertEquals(-1, result);
    }
 
    @Test
    public void test6() {
        int count = grafo.countPathByStop("C", "C",4);
        System.out.println("Pregunta 6: C->C, count:"+count);
        Assert.assertEquals(2, count);
    }
 
    @Test
    public void test7() {
        int count = grafo.countPathByStop("A", "C" , 4);
        System.out.println("Pregunta 7: A->C, count:"+count);
        Assert.assertEquals(3, count);
    }
 
    @Test
    public void test8() {
        int dist = grafo.findShortestDist("A", "C");
        Assert.assertEquals(9, dist);
    }
 
    @Test
    public void test9() {
        int dist = grafo.findShortestDist("B", "B");
        System.out.println("Pregunta 9: B->B, count:"+dist);
        Assert.assertEquals(9, dist);
    }
 
    @Test
    public void test10() {
        int count = grafo.countPathByDist("C", "C" , 30);
        System.out.println("Pregunta 10: C->C, count:"+count);
        Assert.assertEquals(3, count);
    }

	
 
}
