package co.edu.unbosque.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.Parrilla;
import co.edu.unbosque.model.ParrillaDAO;
import co.edu.unbosque.model.Pista;
import co.edu.unbosque.model.PistaDAO;

@ManagedBean(name = "pistaBean")
@SessionScoped
public class PistaBean {
	
	private String nombre_pista, nombre_artista, archivo_pista,nombre_emisora,modo_emisora,musica_emisora;
	private int id_emisora;
	
	private Parrilla parilla;
	private ParrillaDAO padao;
	private static Parrilla par;
	private static Emisora e;
	private  ArrayList<Pista>pistalist;
	private  ArrayList<Pista>programacion;
	private static Pista p;
	private PistaDAO pdao;
	
	public PistaBean() {
		// TODO Auto-generated constructor stub
		pdao = new PistaDAO();
		pistalist = new ArrayList<>();
		programacion = new ArrayList<>();
	
	}
	
	
	public void listarPistas(String musicaEmisora) {
	    System.out.println("Listando");
	    pdao = new PistaDAO();
	    List<Pista> pistas = pdao.listarPistas(); // Obtener todas las pistas
	    
	    // Crear una nueva lista para almacenar las pistas que coincidan con el valor de fk_id_parrilla
	    List<Pista> pistasFiltradas = new ArrayList<>();
	    
	    // Iterar sobre todas las pistas
	    for (Pista p : pistas) {
	        if (p.getGenero_pista().equals(musicaEmisora)) {
	            pistasFiltradas.add(p); // Agregar las pistas que coinciden con el valor de fk_id_parrilla
	        }
	    }
	    
	    // Reemplazar la lista actual con las pistas filtradas
	
	    pistalist.addAll(pistasFiltradas);
	}


	public void descargar(String modoEmisora, String genero) throws IOException {
	    System.out.println("Descargando"+modoEmisora+" "+genero);
	    List<Pista> pistasDescargadas = new ArrayList<>();

	    for (Pista p : programacion) {
	    	  System.out.println(p.getGenero_pista() + " " + p.isEstado_pista());
	        if (p.getGenero_pista().equals(genero) && p.isEstado_pista()==true) {
	            pistasDescargadas.add(p);
	            System.out.println("Adding");
	        }
	    }
	    programacion.clear();
	    programacion.addAll(pistasDescargadas);
	  
	}



		
	public void guardar(String nombre_pista) throws IOException {
	   
	
		

		Iterator<Pista> iterator = pistalist.iterator();
	    while (iterator.hasNext()) {
	        Pista p = iterator.next();
	        if (p.getNombre_pista().equals(nombre_pista)) {
	            iterator.remove(); // Elimina el elemento actual de forma segura
	            p.setEstado_pista(true);
	            programacion.add(p);
	           
	        }
	    }
	    
	  
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.redirect("pista.xhtml");
	}

	
	
	
	
	


	public void crearPista() throws IOException {
	
		pdao = new PistaDAO();
		pistalist = new ArrayList<>();
		
		
		par = new Parrilla();
		
		System.out.println("Creando Pista");
		
		padao = new ParrillaDAO();
		for (Parrilla pa : padao.listarParrilla()) {
			if(pa.getFk_id_emisora() == e.getId_emisora()) {
				par= pa;
			}
		}
		
		p = new Pista(par.getId_parrilla(),this.getNombre_pista(), this.getNombre_artista(),"https://www.youtube.com/embed/"+this.getArchivo_pista());
		pdao = new PistaDAO();
		pdao.guardarPista(p,e.getId_emisora(),e.getName_emisora(),e.getModo_emisora(),e.getMusica_emisora(),par);
	
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.redirect("index.xhtml");
		
		
	}
	
	public void fijar(Integer idEmisora, String nombreEmisora, String musicaEmisora,String modoEmisora) throws IOException {
		pdao = new PistaDAO();
		programacion.clear();
		pistalist = new ArrayList<>();
		listarPistas(musicaEmisora);
		this.nombre_emisora = nombreEmisora;
		this.modo_emisora = modoEmisora;
		this.musica_emisora = musicaEmisora;
		this.id_emisora = idEmisora;
		
		e = new Emisora(id_emisora,nombre_emisora,modo_emisora,musica_emisora);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.redirect("pista.xhtml");
	
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
		PistaBean.e = e;
	}


	

	


	public ArrayList<Pista> getPistalist() {
		return pistalist;
	}


	public void setPistalist(ArrayList<Pista> pistalist) {
		this.pistalist = pistalist;
	}


	public static Pista getP() {
		return p;
	}


	public static void setP(Pista p) {
		PistaBean.p = p;
	}


	public PistaDAO getPdao() {
		return pdao;
	}


	public void setPdao(PistaDAO pdao) {
		this.pdao = pdao;
	}


	public Parrilla getParilla() {
		return parilla;
	}


	public void setParilla(Parrilla parilla) {
		this.parilla = parilla;
	}


	public ParrillaDAO getPadao() {
		return padao;
	}


	public void setPadao(ParrillaDAO padao) {
		this.padao = padao;
	}


	public static Parrilla getPar() {
		return par;
	}


	public static void setPar(Parrilla par) {
		PistaBean.par = par;
	}


	public ArrayList<Pista> getProgramacion() {
		return programacion;
	}


	public void setProgramacion(ArrayList<Pista> programacion) {
		this.programacion = programacion;
	}
	
	
	
	

}
