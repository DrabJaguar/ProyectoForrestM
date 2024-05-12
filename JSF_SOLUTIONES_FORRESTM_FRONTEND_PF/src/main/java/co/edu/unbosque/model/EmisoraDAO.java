package co.edu.unbosque.model;


import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.json.EmisoraJSON;

public class EmisoraDAO {

	//DATA ACCES OBJECT 
	
	private Emisora emisora;
	private ArrayList<Emisora> emisoraList;

	private EmisoraJSON json;

	
	
	
	public String agregar(Object registro) {
		emisora = (Emisora) registro;
		json = new EmisoraJSON();
		json.sendPost(emisora);
		return "Agregada con exito";
	}
	
	public ArrayList<Emisora> listarEmisoras() {
	try {
		emisoraList = new ArrayList<Emisora>();
		json=new EmisoraJSON();
		emisoraList.addAll(json.getJSON());
		
	 } catch (IOException | ParseException e) {
         // Manejar la excepción, imprimir el error, etc.
         e.printStackTrace();
     }
	return emisoraList;
	}
	
	
	public Emisora buscarPorId(int id_emisora) {
		try {
			if(id_emisora == 0) {
				id_emisora=1;
			}
			
			emisora = new Emisora();
			json=new EmisoraJSON();
			emisora = json.getOne(id_emisora);
			
		 } catch (IOException | ParseException e) {
	         // Manejar la excepción, imprimir el error, etc.
	         e.printStackTrace();
	     }
		
		return emisora;
	}
	
	
	
	
	
	
	
	
}
