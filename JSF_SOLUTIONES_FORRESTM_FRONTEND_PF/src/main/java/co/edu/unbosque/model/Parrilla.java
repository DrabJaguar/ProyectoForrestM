package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.Date;

public class Parrilla {
	
	private int id_parrilla;
	private LocalDate parrilla_date;
	private int fk_id_emisora;
	
	public Parrilla() {
		// TODO Auto-generated constructor stub
	}

	public Parrilla(int id_parrilla, LocalDate parrilla_date) {
		super();
		this.id_parrilla = id_parrilla;
		this.parrilla_date = parrilla_date;
	}


	public Parrilla( LocalDate parrilla_date) {
		super();
		this.parrilla_date = parrilla_date;
	}



	public LocalDate getParrilla_date() {
		return parrilla_date;
	}

	public void setParrilla_date(LocalDate parrilla_date) {
		this.parrilla_date = parrilla_date;
	}

	public int getId_parrilla() {
		return id_parrilla;
	}

	public void setId_parrilla(int id_parrilla) {
		this.id_parrilla = id_parrilla;
	}

	public int getFk_id_emisora() {
		return fk_id_emisora;
	}

	public void setFk_id_emisora(int fk_id_emisora) {
		this.fk_id_emisora = fk_id_emisora;
	}
	
	
	
	
	
	
}
