package com.fisa.test.jcol.TrainProblem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;


public class Process {

	private Map<String, Map<String, Integer>> adj;// Mapa de Adhiacentes 
    private int v;//numero de vertices
    private Set<String> vertexes;//todos los vertices existentes
    
    
    public Process() {
		super();
	}

	public Process(String str) {
		
        vertexes = new HashSet<String>();
        adj = new HashMap<String, Map<String,Integer>>();
        if (null == str || str.length() <=0 )
            return;
        
        String[] vertexLenths = str.split("\\,");
        for (String vertexLenth : vertexLenths) {
            if (vertexLenth.length() != 3) 
                continue;
            
            String s = String.valueOf(vertexLenth.charAt(0));
            String t = String.valueOf(vertexLenth.charAt(1));
            int w = vertexLenth.charAt(2) - '0';
            Map<String, Integer> sEdgeMap = adj.get(s);
            if (null == sEdgeMap) {
                sEdgeMap = new HashMap<String, Integer>();
                adj.put(s, sEdgeMap);
            }
            sEdgeMap.put(t, w);
            if (vertexes.add(s)) 
                v++;
            
            if (vertexes.add(t))
                v++;
 
        }
      
	} 
	
	public int findDistOfPath(String vertex) {
		
        String[] vertexes = vertex.split("-");
        if (null == vertexes || vertexes.length <= 0) {
            return -1;
        }
        String s = vertexes[0];
        int dist = 0;
        for (int i = 1; i < vertexes.length; i++) {
            String t = vertexes[i];
            Map<String, Integer> edgeMap = adj.get(s);
            if (null != edgeMap && edgeMap.containsKey(t)) {
                dist += edgeMap.get(t);
            } else {
                return -1;
            }
            s = t;
        }
        return dist;
    }

	public int findDistOfPath_(String vertex) {
		
		String[] vertices = vertex.split("-");
        if (null == vertices || vertices.length <= 0) {
            return -1;
        }
		String s = vertices[0];
        int dist = 0;
        int acum = 0;
        for (int i = 1; i < vertices.length; i++) {
            String t = vertices[i];
            Map<String, Integer> MapAux = adj.get(s);
          
            dist = MapAux.entrySet().stream()
                    .filter(m -> t.equals(m.getKey()))
                    .map(m -> m.getValue())		
                    .findFirst()
                    .orElseGet(() -> 0); 
            
            if(dist == 0) return -1; else acum+=dist;
            s = t;
        }
		return acum;
	}
	
	public int findShortestDist(String s, String t) {
		
        Map<String, Vertex> parentMap = new HashMap<>(this.v);//almacenar el vértice y la distancia más cercana a cada vértice
        for (String vertex : vertexes) {
        	parentMap.put(vertex, new Vertex(s, Integer.MAX_VALUE));
        }
        parentMap.put(s, null);
        @SuppressWarnings({ "unchecked", "rawtypes" })
		Queue<Vertex> pqueue = new PriorityQueue<>(new Comparator() {
        	@Override
        	public int compare(Object o1, Object o2) {
        		Vertex v1 = (Vertex) o1;
        		Vertex v2 = (Vertex) o2;
        		return v1.dist - v2.dist;
             }
        });
        
        
        pqueue.add(new Vertex(s, 0));
        Set<String> visited = new HashSet<String>(this.v);
        while (!pqueue.isEmpty()) {
        	
        	Vertex minVertex = pqueue.poll();
        	if (!visited.add(minVertex.v)) {
        		continue;
        	}
            Map<String, Integer> edgeMap = adj.get(minVertex.v);
            
            for (Map.Entry<String, Integer> entry : edgeMap.entrySet()) {
            	
	        	int dist = minVertex.dist + entry.getValue();
	            if (null != parentMap.get(entry.getKey()) &&
	            	dist < parentMap.get(entry.getKey()).dist) {//La distancia desde el vértice inicial es menor que la anterior. El vértice anterior de la ruta más corta actualizada es el vértice que se acaba de quitar de la cola de prioridad.
		            	 
	            	     parentMap.get(entry.getKey()).dist = dist;
		            	 parentMap.get(entry.getKey()).v = minVertex.v;
	             }
	
	            pqueue.add(new Vertex(entry.getKey(), dist));
           }
		}
        if (!s.equals(t)){
        	return parentMap.get(t).dist;
        }else{
        	return calculateDist(s , parentMap);
        }
	}

	private int calculateDist(String s, Map<String, Vertex> parentMap) {
        int dist = Integer.MAX_VALUE;
        for (Map.Entry<String, Vertex> entry : parentMap.entrySet()) {
            if (entry.getValue() != null
                    && entry.getValue().v.equals(s)
                    && entry.getValue().dist < dist){
                                 String next = entry.getKey();//Primero se encuentra el vértice más cercano al vértice de destino
                int curDist = entry.getValue().dist + findShortestDist(next , s);
                if (curDist < dist){
                    dist = curDist;
                }
            }
        }
        return dist;
    }
	
	public List<Object[]> getPathsOfTwoVertex(String start,String end){
		
        Stack<String> stack = new Stack<String>();
        List<Object[]> paths = new LinkedList<Object[]>();
        Set<String> visited = new HashSet<String>(this.v);
        dfsPath(start , start , end , null, stack , paths , visited);
        for(Object[] path : paths){
            printPath(path);
        }
        return paths;
    }

	public int countPathByDist(String start,String end , int maxDist){
        List<Object[]> paths = getPathsOfTwoVertex(start , end);
        int count = 0;
        if (null == paths || paths.size() <= 0){
            return count;
        }
 
        List<Integer> dists = new LinkedList<>();
        for(Object[] path : paths){
            List<String> list = new ArrayList<>(path.length);
            for (Object o : path){
                list.add(String.valueOf(o));
            }
            String b = list.stream()
 			           .map(n -> String.valueOf(n))
			           .collect(Collectors.joining("-", "", ""));
            int dist = findDistOfPath(b);
            if (dist < maxDist){
                dists.add(dist);
                count++;
            }
        }
        return count;
    }
	
	public int countPathByStop(String start,String end , int maxStop){
        List<Object[]> paths = getPathsOfTwoVertex(start , end);
        int count = 0;
        if (null == paths || paths.size() <= 0){
            return count;
        }
 
        for(Object[] path : paths){
            if (path.length <= maxStop){
                count++;
            }
        }
        return count;
    }
	
	public void dfsPath(String start,String index,String end ,String prev , Stack<String> stack, 
			List<Object[]> paths,Set<String> visited) {
		
        stack.push(index);
        
        if (index.equals(end) && prev != null){
            paths.add(stack.toArray());
            stack.pop();
           System.out.println("Buscando el final del punto: " + index + ", se obtiene la ruta retrocediendo uno por uno, se comprueba que el nodo: "+index+" tiene otros caminos");
        }else{
            Map<String, Integer> edgeMap = adj.get(index);
                         System.out.println("El nodo de búsqueda: "+index+" Tiene el/los nodo(s): "+ edgeMap);
            if (null != edgeMap && edgeMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : edgeMap.entrySet()) {
                                         System.out.println("Nodo de búsqueda: "+index+" Post nodo:" + entry.getKey());
                    if (!stack.contains(entry.getKey()) || !visited.contains(entry.getKey())){

                        dfsPath(start, entry.getKey(), end , index , stack , paths , visited);
                    }
                }
                visited.add(stack.pop());
                if (stack.size()>0){
                        System.out.println("Nodo: " + index +" La búsqueda está completa."
                        		+ "Se retrocede un punto, y se comprueba si hay otra ruta en el nodo: " + index + "");
                } else{
                        System.out.println("Fin de la iteración no existe otras rutas:");
                }
            }
        }
    }
	
	private void printPath(Object[] path) {
        StringBuilder sb = new StringBuilder();
        for (Object o : path) {
            sb.append(o);
        }
        System.out.println(sb);
    }


}
