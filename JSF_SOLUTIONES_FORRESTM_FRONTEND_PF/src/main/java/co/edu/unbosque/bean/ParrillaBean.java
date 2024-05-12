package co.edu.unbosque.bean;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import co.edu.unbosque.model.Emisora;

import co.edu.unbosque.model.Pista;
import co.edu.unbosque.model.PistaDAO;

@ManagedBean(name = "parrillaBean")
@SessionScoped
public class ParrillaBean {
	
	private String nombre_pista, nombre_artista, archivo_pista,nombre_emisora,modo_emisora,musica_emisora;
	private int id_emisora;
	String url;

	private static Emisora e;

	private static Pista p;
	private PistaDAO pdao;
	
	public void fijar(PistaBean pb ,Integer idEmisora, String nombreEmisora, String musicaEmisora,String modoEmisora) throws IOException {
	
		this.nombre_emisora = nombreEmisora;
		this.modo_emisora = modoEmisora;
		this.musica_emisora = musicaEmisora;
		this.id_emisora = idEmisora;
		pb.descargar(modoEmisora,musicaEmisora);
		e = new Emisora(id_emisora,nombre_emisora,modo_emisora,musica_emisora);
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.redirect("parrilla.xhtml");
	
	}
	
	
	public void reproducir(String url) throws IOException {
		
	 
		this.url = url;
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.redirect("parrilla.xhtml");
	
	}

	public String getNombre_pista() {
		return nombre_pista;
	}

	public void setNombre_pista(String nombre_pista) {
		this.nombre_pista = nombre_pista;
	}

	public String getNombre_artista() {
		return nombre_artista;
	}

	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}

	public String getArchivo_pista() {
		return archivo_pista;
	}

	public void setArchivo_pista(String archivo_pista) {
		this.archivo_pista = archivo_pista;
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

	public String getMusica_emisora() {
		return musica_emisora;
	}

	public void setMusica_emisora(String musica_emisora) {
		this.musica_emisora = musica_emisora;
	}

	public int getId_emisora() {
		return id_emisora;
	}

	public void setId_emisora(int id_emisora) {
		this.id_emisora = id_emisora;
	}

	public static Emisora getE() {
		return e;
	}

	public static void setE(Emisora e) {
		ParrillaBean.e = e;
	}

	public static Pista getP() {
		return p;
	}

	public static void setP(Pista p) {
		ParrillaBean.p = p;
	}

	public PistaDAO getPdao() {
		return pdao;
	}

	public void setPdao(PistaDAO pdao) {
		this.pdao = pdao;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
