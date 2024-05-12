package co.edu.unbosque.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Emisora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_emisora;
	private String nombre_emisora;
	private String modo_emisora;
	private String tipo_emisora;
	public Integer getId_emisora() {
		return id_emisora;
	}
	public void setId_emisora(Integer id_emisora) {
		this.id_emisora = id_emisora;
	}
	public String getNombre_emisora() {
		return nombre_emisora;
	}
	public void setNombre_emisora(String nombre_emisora) {
		this.nombre_emisora = nombre_emisora;
	}
	public String getModo_emisora() {
		return modo_emisora;
	}
	public void setModo_emisora(String modo_emisora) {
		this.modo_emisora = modo_emisora;
	}
	public String getTipo_emisora() {
		return tipo_emisora;
	}
	public void setTipo_emisora(String tipo_emisora) {
		this.tipo_emisora = tipo_emisora;
	}
	
	

}
