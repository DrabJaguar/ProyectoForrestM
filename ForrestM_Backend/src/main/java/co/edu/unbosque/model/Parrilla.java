package co.edu.unbosque.model;

import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Parrilla {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id_parrilla;

	    @Temporal(TemporalType.DATE)
	    private Date fecha_parrilla;

	    @ManyToOne
	    @JoinColumn(name = "fk_emisora_parrilla", referencedColumnName = "id_emisora")   
	    private Emisora fk_emisora_parrilla;

	   
		public Integer getId_parrilla() {
			return id_parrilla;
		}

		public void setId_parrilla(Integer id_parrilla) {
			this.id_parrilla = id_parrilla;
		}

		public Date getFecha_parrilla() {
			return fecha_parrilla;
		}

		public void setFecha_parrilla(Date fecha_parrilla) {
			this.fecha_parrilla = fecha_parrilla;
		}

		public Emisora getFk_emisora_parrilla() {
			return fk_emisora_parrilla;
		}

		public void setFk_emisora_parrilla(Emisora fk_emisora_parrilla) {
			this.fk_emisora_parrilla = fk_emisora_parrilla;
		}

		
		
	    
	    
	    
}
