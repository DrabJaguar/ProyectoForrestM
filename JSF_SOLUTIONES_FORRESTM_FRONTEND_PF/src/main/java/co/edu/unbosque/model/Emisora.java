package co.edu.unbosque.model;

public class Emisora {

	
	private int id_emisora;
	private String name_emisora;
	private String modo_emisora;
	private String musica_emisora;
	
	
	public Emisora() {
		// TODO Auto-generated constructor stub
	}


	public Emisora(int id_emisora, String name_emisora, String modo_emisora, String musica_emisora) {
		super();
		this.id_emisora = id_emisora;
		this.name_emisora = name_emisora;
		this.modo_emisora = modo_emisora;
		this.musica_emisora = musica_emisora;
	}


	public Emisora(String name_emisora, String modo_emisora, String musica_emisora) {
		super();
		this.name_emisora = name_emisora;
		this.modo_emisora = modo_emisora;
		this.musica_emisora = musica_emisora;
	}


	public int getId_emisora() {
		return id_emisora;
	}


	public void setId_emisora(int id_emisora) {
		this.id_emisora = id_emisora;
	}


	public String getName_emisora() {
		return name_emisora;
	}


	public void setName_emisora(String name_emisora) {
		this.name_emisora = name_emisora;
	}


	public String getModo_emisora() {
		return modo_emisora;
	}


	public void setModo_emisora(String modo_emisora) {
		this.modo_emisora = modo_emisora;
	}


	public String getMusica_emisora() {
		return musica_emisora;
	}


	public void setMusica_emisora(String musica_emisora) {
		this.musica_emisora = musica_emisora;
	}


	@Override
	public String toString() {
		return "EmisoraDTO [id_emisora=" + id_emisora + ", name_emisora=" + name_emisora + ", modo_emisora="
				+ modo_emisora + ", musica_emisora=" + musica_emisora + "]";
	}
	
	
	
	
	
	
	
	
}
