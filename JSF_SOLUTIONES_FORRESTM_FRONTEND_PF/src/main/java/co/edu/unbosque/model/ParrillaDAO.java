package co.edu.unbosque.model;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.json.ParrillaJSON;

public class ParrillaDAO {

	private ArrayList<Parrilla> parrillaList;
	private ParrillaJSON json;
	private Parrilla pdto;
	
	
	public String guardarParrilla (Parrilla pdto , Emisora emi) {
		System.out.println("Creando Parrilla");
		json = new ParrillaJSON();
		json.postJSON(pdto,emi);
		return "Parrilla creada con exito!";
	}
	
	public ArrayList<Parrilla> listarParrilla(){
		try {
			parrillaList = new ArrayList<Parrilla>();
			json = new ParrillaJSON();
			parrillaList.addAll(json.getJSON());
			
		}catch (IOException | ParseException e) {
	         // Manejar la excepción, imprimir el error, etc.
	         e.printStackTrace();
	     }
		return parrillaList;
	}
}
