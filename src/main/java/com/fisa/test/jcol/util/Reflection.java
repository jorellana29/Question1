package com.fisa.test.jcol.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Reflection {

	private Map<Integer,String> opcionMenu;	
	
	public Reflection() {
		opcionMenu = new LinkedHashMap<>();
		opcionMenu.put(1, "Longitud ruta,com.fisa.test.jcol.TrainProblem.Process.findDistOfPath,A-B-C");
		opcionMenu.put(2, "Longitud ruta,com.fisa.test.jcol.TrainProblem.Process.findDistOfPath,A-D");
		opcionMenu.put(3, "Longitud ruta,com.fisa.test.jcol.TrainProblem.Process.findDistOfPath,A-D-C");
		opcionMenu.put(4, "Longitud ruta,com.fisa.test.jcol.TrainProblem.Process.findDistOfPath,A-E-B-C-D");
		opcionMenu.put(5, "Longitud ruta,com.fisa.test.jcol.TrainProblem.Process.findDistOfPath,A-E-D");
		opcionMenu.put(6, "Total paradas,com.fisa.test.jcol.TrainProblem.Process.countPathByStop,C,C,4");
		opcionMenu.put(7, "Total paradas,com.fisa.test.jcol.TrainProblem.Process.countPathByStop,A,C,4");
		opcionMenu.put(8, "Longitud ruta más corta,com.fisa.test.jcol.TrainProblem.Process.findShortestDist,A,C");
		opcionMenu.put(9, "Longitud ruta más corta,com.fisa.test.jcol.TrainProblem.Process.findShortestDist,B,B");
		opcionMenu.put(10,"Número de rutas,com.fisa.test.jcol.TrainProblem.Process.countPathByDist,C,C,30");
	}
	
	public String getOpcionMenu(int opcion) {
		
		if(!(opcion >= 1 && opcion <= 10))
			return "ERROR";
		
		return opcionMenu.get(opcion);
		
	}

	public void setOpcionMenu(Map<Integer, String> opcionMenu) {
		this.opcionMenu = opcionMenu;
	}


}
