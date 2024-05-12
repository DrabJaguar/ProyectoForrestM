package co.edu.unbosque.model;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.json.PistaJSON;

public class PistaDAO {

	private ArrayList<Pista>pistaList;
	private PistaJSON json;
	private Emisora edto;
	
	public PistaDAO() {
		// TODO Auto-generated constructor stub
	}

	public void guardarPista(Pista pdto, int id_emisora, String nombre_emisora, String modo_emisora,
		String musica_emisora,Parrilla par) {
		System.out.println("Guardando");
		json = new PistaJSON();
		edto = new Emisora(id_emisora, nombre_emisora,modo_emisora,musica_emisora);
		json.postJSON(pdto, edto,par);
		
	}
	
	public ArrayList<Pista>listarPistas(){
		try {
			pistaList = new ArrayList<Pista>();
			json = new PistaJSON();
			pistaList.addAll(json.getJSON());
			
		 } catch (IOException | ParseException e) {
			((Throwable) e).printStackTrace();
		}
		return pistaList;
	}
	
}
