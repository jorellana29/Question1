package com.fisa.test.jcol.TrainProblem;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.fisa.test.jcol.util.Reflection;
import com.fisa.test.jcol.util.Utils;

public class App 
{

    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
    	try {
			
    		String opt = "";
    		Scanner teclado = new Scanner(System.in);
    		Scanner entrada = new Scanner(System.in);
    	    String opc = "S";
    	    boolean seguir = true;
    		
	    		System.out.println("********************* PROBLEM TRAINS *********************\n");
	        do{
	        	
	        	if (opc.equals("s") || opc.equals("S")) {
		    		System.out.println("Escoja opción de Pregunta [1 al 10]: ");
		    		opt = teclado.nextLine();    		
		    		
		    		while (true) {
		    			if(!Utils.validInput(opt)) {
		    				
		    				System.out.println("Entrada Inválida!! ");
		    				System.out.println("Escoja opción de Pregunta [1 al 10]: ");
				    		opt = teclado.nextLine();  
		    			}else
		                    break;
					}
		    		
		        	String str = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		
		        	Object[] obj = null;
		            
		            Reflection ref = new Reflection();
		            int opcion = Integer.parseInt(opt);
		            String data[] = ref.getOpcionMenu(opcion).split("\\,");
		            
		             switch (data.length) {
		    		 case 3:
		    			  obj = new Object[]{data[2]};
		    			break;
		    			
		    		 case 4:
		    			 obj = new Object[]{data[2],data[3]};
		    			break;
		    			
		    		 case 5:
		    			 obj = new Object[]{data[2],data[3], Integer.parseInt(data[4])};
		    			break;
		
		    		default:
		    			System.out.println("Opción no permitida!!");		    			
		    			return;
		    		}
		            
		             Class<?> params[] = new Class[obj.length];
		             for (int i = 0; i < obj.length; i++) {
		                 if (obj[i] instanceof Integer) {
		                     params[i] = Integer.TYPE;
		                 } else if (obj[i] instanceof String) {
		                     params[i] = String.class;
		                 }
		             }
		            
		             String messageClass = data[1];					
		    	     List<String> list = Arrays.asList(messageClass.split("\\."));
		    	     String methodName = list.get(list.size() -1);
		    	     String className = messageClass.replace("." + methodName, "");
		             
		             
		             Class<?> cls = Class.forName(className);
		             Object classInstance = cls.getConstructor(new Class[]{ String.class }).newInstance(str);
		             Method myMethod = cls.getDeclaredMethod(methodName, params);
		             int result =  (int) myMethod.invoke(classInstance, obj);
		             if(result > 0)
		             	System.out.println(data[0] + ": " + result);
		             else
		            	 System.out.println("No existe ruta");
		             
		             System.out.print("¿Desea continuar? [S/N]");
				     opc = teclado.next();

	        	}else {
	        		seguir=false;
				}
             
    		}while (seguir);
             
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
         
    }
    
    
    
    
}
