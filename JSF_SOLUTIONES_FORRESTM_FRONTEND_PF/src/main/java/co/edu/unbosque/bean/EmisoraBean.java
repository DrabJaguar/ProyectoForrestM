package co.edu.unbosque.bean;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.EmisoraDAO;
import co.edu.unbosque.model.Parrilla;
import co.edu.unbosque.model.ParrillaDAO;



@ManagedBean
@RequestScoped
public class EmisoraBean {

	 private int id_emisora;
	 private String name_emisora;
	 private String modo_emisora;
	 private String musica_emisora;
	
	 private Parrilla parrilla;
	 private ParrillaDAO pdao;
   

    private static ArrayList<Emisora> elist;
    private EmisoraDAO edao; 
    private Emisora edto;
    
   
    
    //variables que cargan el front 
    private String[] tipos_musica = { "De Planchar", "Vallenato", "Ranchera", "Regueton", "Salsa", "Rock" ,"Salsachoke" };
    private String[] modos_emisora = { "AM", "FM", "Streaming" };

    public EmisoraBean() {
        edao = new EmisoraDAO();
        elist = new ArrayList<>();
        listarEmisora();
    }
    


    public void crearEmisora() throws IOException {
        edto = new Emisora(name_emisora, modo_emisora, musica_emisora);
        edao = new EmisoraDAO();
        System.out.println(edao.agregar(edto));
     
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.redirect("index.xhtml");
    }
    


	public void listarEmisora() {
    	
        edao = new EmisoraDAO();
        elist.addAll(edao.listarEmisoras());
        for (Emisora emisora : elist) {
            System.out.println(emisora.toString());
            LocalDate fecha = LocalDate.now();
            parrilla = new Parrilla(fecha);
            pdao = new ParrillaDAO();
            System.out.println(pdao.guardarParrilla(parrilla, emisora));
            
        }
        
    }

    // Getters y setters
    
    
    
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

    public String[] getTipos_musica() {
        return tipos_musica;
    }

    public void setTipos_musica(String[] tipos_musica) {
        this.tipos_musica = tipos_musica;
    }

    public String[] getModos_emisora() {
        return modos_emisora;
    }

    public void setModos_emisora(String[] modos_emisora) {
        this.modos_emisora = modos_emisora;
    }

    public ArrayList<Emisora> getElist() {
        return elist;
    }

    public void setElist(ArrayList<Emisora> elist) {
        this.elist = elist;
    }

    public EmisoraDAO getEdao() {
        return edao;
    }

    public void setEdao(EmisoraDAO edao) {
        this.edao = edao;
    }



	public Emisora getEdto() {
		return edto;
	}



	public void setEdto(Emisora edto) {
		this.edto = edto;
	}




    
	
    

}
