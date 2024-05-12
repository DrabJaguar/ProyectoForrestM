package co.edu.unbosque.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_pista;
    @ManyToOne
    @JoinColumn(name = "fk_genero_pista", referencedColumnName = "tipo_emisora")
    private Emisora fk_genero_pista;
	
    @ManyToOne
    @JoinColumn(name = "fk_parrilla_pista", referencedColumnName = "id_parrilla")
    private Parrilla fk_parrilla_pista;
    
    private String nombre_pista;
	private String artista_pista;
	private String archivo_pista;
	

	public Integer getId_pista() {
		return id_pista;
	}

	public void setId_pista(Integer id_pista) {
		this.id_pista = id_pista;
	}

	public Emisora getFk_genero_pista() {
		return fk_genero_pista;
	}

	public void setFk_genero_pista(Emisora fk_genero_pista) {
		this.fk_genero_pista = fk_genero_pista;
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

	public Parrilla getFk_parrilla_pista() {
		return fk_parrilla_pista;
	}

	public void setFk_parrilla_pista(Parrilla fk_parrilla_pista) {
		this.fk_parrilla_pista = fk_parrilla_pista;
	}
	
	
	

}
