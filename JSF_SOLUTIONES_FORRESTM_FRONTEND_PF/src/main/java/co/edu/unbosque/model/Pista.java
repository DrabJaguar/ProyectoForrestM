package co.edu.unbosque.model;

public class Pista {

	private int id_pista;
	private int fk_id_parrilla;
	private Emisora e;
	private Parrilla p;
	private String nombre_pista;
	private String artista_pista;
	private String archivo_pista;
	private String genero_pista;
	private boolean estado_pista;

	public Pista() {
		// TODO Auto-generated constructor stub
	}

	public Pista(int id_pista, int fk_id_parrilla, Emisora e, Parrilla p, String nombre_pista, String artista_pista,
			String archivo_pista, String genero_pista, boolean estado_pista) {
		super();
		this.id_pista = id_pista;
		this.fk_id_parrilla = fk_id_parrilla;
		this.e = e;
		this.p = p;
		this.nombre_pista = nombre_pista;
		this.artista_pista = artista_pista;
		this.archivo_pista = archivo_pista;
		this.genero_pista = genero_pista;
		this.estado_pista = estado_pista;
	}

	public Pista(int fk_id_parrilla, String nombre_pista, String artista_pista, String archivo_pista) {
		super();
		this.fk_id_parrilla = fk_id_parrilla;
		this.nombre_pista = nombre_pista;
		this.artista_pista = artista_pista;
		this.archivo_pista = archivo_pista;
	}

	public Pista(int id_pista, int fk_id_parrilla, String nombre_pista, String artista_pista, String archivo_pista) {
		super();
		this.id_pista = id_pista;
		this.fk_id_parrilla = fk_id_parrilla;
		this.nombre_pista = nombre_pista;
		this.artista_pista = artista_pista;
		this.archivo_pista = archivo_pista;
	}

	public int getId_pista() {
		return id_pista;
	}

	public void setId_pista(int id_pista) {
		this.id_pista = id_pista;
	}

	public int getFk_id_parrilla() {
		return fk_id_parrilla;
	}

	public void setFk_id_parrilla(int fk_id_parrilla) {
		this.fk_id_parrilla = fk_id_parrilla;
	}

	public String getNombre_pista() {
		return nombre_pista;
	}

	public void setNombre_pista(String nombre_pista) {
		this.nombre_pista = nombre_pista;
	}

	public String getArtista_pista() {
		return artista_pista;
	}

	public void setArtista_pista(String artista_pista) {
		this.artista_pista = artista_pista;
	}

	public String getArchivo_pista() {
		return archivo_pista;
	}

	public void setArchivo_pista(String archivo_pista) {
		this.archivo_pista = archivo_pista;
	}

	@Override
	public String toString() {
		return "Pista [id_pista=" + id_pista + ", fk_id_parrilla=" + fk_id_parrilla + ", nombre_pista=" + nombre_pista
				+ ", artista_pista=" + artista_pista + ", archivo_pista=" + archivo_pista + "]";
	}

	public Emisora getE() {
		return e;
	}

	public void setE(Emisora e) {
		this.e = e;
	}

	public Parrilla getP() {
		return p;
	}

	public void setP(Parrilla p) {
		this.p = p;
	}

	public String getGenero_pista() {
		return genero_pista;
	}

	public void setGenero_pista(String genero_pista) {
		this.genero_pista = genero_pista;
	}

	public boolean isEstado_pista() {
		return estado_pista;
	}

	public void setEstado_pista(boolean estado_pista) {
		this.estado_pista = estado_pista;
	}

}
