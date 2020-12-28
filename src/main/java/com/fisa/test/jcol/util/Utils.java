package com.fisa.test.jcol.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

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
            System.out.println("Error en lectura el archivo/directorio no existe");
        }catch (IOException e){
            System.out.println("Error en procesos, "+e.getMessage());
        }finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    System.out.println("Error al cerrar el Archivo,"+e.getMessage());
                }
            }
        }
        return sb.toString();
    }
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static boolean validInput(String value) {
    	int z = 0;
    	if(Utils.isNumeric(value)) {
    		z = Integer.parseInt(value);
    		if(z >= 1 && z <= 10) 
    			return true;
    		else
    			return false;
    	}else
    		return false;
    }

}
