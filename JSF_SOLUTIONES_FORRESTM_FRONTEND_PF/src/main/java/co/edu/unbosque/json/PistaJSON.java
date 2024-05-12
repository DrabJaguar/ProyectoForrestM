package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.Parrilla;
import co.edu.unbosque.model.Pista;

public class PistaJSON {
	
	private static URL url;
	private static String site = "http://localhost:8081/api/";
	private String data;
	
	public PistaJSON() {
	
	}
	

	public int postJSON(Pista pdto, Emisora edto, Parrilla par) {
		 try {
				url = new URL(site + "pistas");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 HttpURLConnection http = null;
		    try {
		        http = (HttpURLConnection) url.openConnection();
		        http.setRequestMethod("POST");
		        http.setDoOutput(true);
		        http.setRequestProperty("Accept", "application/json");
		        http.setRequestProperty("Content-Type", "application/json");
		        data = "{"
		        	    + "\"fk_genero_pista\": {"
		        	    + "\"id_emisora\": " + edto.getId_emisora()
		        	    + ", \"nombre_emisora\": \"" + edto.getName_emisora()
		        	    + "\", \"modo_emisora\": \"" + edto.getModo_emisora()
		        	    + "\", \"tipo_emisora\": \"" + edto.getMusica_emisora()
		        	    + "\"},"
		        	    + "\"fk_parrilla_pista\": {"
		        	    + "\"id_parrilla\": " + par.getId_parrilla()
		        	    + ", \"fecha_parrilla\": \"" + par.getParrilla_date().toString()
		        	    + "\", \"fk_emisora_parrilla\": {"
		        	    + "\"id_emisora\": " + edto.getId_emisora()
		        	    + ", \"nombre_emisora\": \"" + edto.getName_emisora()
		        	    + "\", \"modo_emisora\": \"" + edto.getModo_emisora()
		        	    + "\", \"tipo_emisora\": \"" + edto.getMusica_emisora()
		        	    + "\"}},"
		        	    + "\"nombre_pista\": \"" + pdto.getNombre_pista()
		        	    + "\", \"artista_pista\": \"" + pdto.getArtista_pista()
		        	    + "\", \"archivo_pista\": \"" + pdto.getArchivo_pista()
		        	    + "\"}";
		        
		        System.out.println(data);
		        byte[] out = data.getBytes(StandardCharsets.UTF_8);
		        OutputStream stream = http.getOutputStream();
		        stream.write(out);
		        int respuesta = http.getResponseCode();

		        // Manejo de la respuesta según el código
		        if (respuesta == HttpURLConnection.HTTP_OK) {
		            // Procesar la respuesta exitosa
		        } else {
		            // Manejar la respuesta no exitosa
		            System.out.println("La solicitud POST no fue exitosa. Código de respuesta: " + respuesta);
		        }

		        return respuesta;
		        
		    	}catch (IOException e) {
			        // Manejar la excepción de manera más explícita
			        System.err.println("Error al realizar la solicitud POST: " + e.getMessage());
			        e.printStackTrace();  // Puedes comentar o eliminar esta línea si no deseas imprimir la traza completa
			        return -1;  // Otra forma de manejar el error, según tus necesidades
			    } finally {
			        if (http != null) {
			            http.disconnect();
			        }
			    }
		
	}
	
	
	public static ArrayList<Pista> getJSON () throws IOException, ParseException{
		url= new URL (site+"pistas");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Pista> lista = new ArrayList<Pista>();
		lista = parsingPistas(json);
		http.disconnect();
		return lista;

	}


	private static ArrayList<Pista> parsingPistas(String json) throws ParseException {
	    JSONParser jsonParser = new JSONParser();
	    ArrayList<Pista> lista = new ArrayList<>();

	    // Convertir el JSON a JSONArray
	    JSONArray pistasArray = (JSONArray) jsonParser.parse(json);

	    // Iterar sobre cada objeto de la lista de pistas
	    for (Object obj : pistasArray) {
	        JSONObject pistaObj = (JSONObject) obj;

	        // Crear un nuevo objeto Pista
	        Pista p = new Pista();

	        // Obtener y establecer el ID de la pista
	        Long idPista = (Long) pistaObj.get("id_pista");
	        p.setId_pista(idPista.intValue());

	        // Obtener el objeto fk_genero_pista y establecer sus valores
	        JSONObject generoObj = (JSONObject) pistaObj.get("fk_genero_pista");
	        Emisora generoPista = new Emisora();
	        generoPista.setId_emisora(Integer.parseInt(generoObj.get("id_emisora").toString()));
	        generoPista.setName_emisora((String) generoObj.get("nombre_emisora"));
	        generoPista.setModo_emisora((String) generoObj.get("modo_emisora"));
	        generoPista.setMusica_emisora((String) generoObj.get("tipo_emisora"));

	        // Establecer el objeto fk_genero_pista en la pista
	        p.setE(generoPista);
	        p.setGenero_pista(generoPista.getMusica_emisora());

	        // Obtener el objeto fk_parrilla_pista y establecer sus valores
	        JSONObject parrillaObj = (JSONObject) pistaObj.get("fk_parrilla_pista");
	        Parrilla parrillaPista = new Parrilla();
	        parrillaPista.setId_parrilla(Integer.parseInt(parrillaObj.get("id_parrilla").toString()));
	        parrillaPista.setParrilla_date(LocalDate.parse((String) parrillaObj.get("fecha_parrilla")));

	        // Establecer el objeto fk_parrilla_pista en la pista
	        p.setP(parrillaPista);

	        // Obtener y establecer el nombre de la pista
	        String nombrePista = (String) pistaObj.get("nombre_pista");
	        p.setNombre_pista(nombrePista);

	        // Obtener y establecer el artista de la pista
	        String artistaPista = (String) pistaObj.get("artista_pista");
	        p.setArtista_pista(artistaPista);

	        // Obtener y establecer el archivo de la pista
	        String archivoPista = (String) pistaObj.get("archivo_pista");
	        p.setArchivo_pista(archivoPista);
	        p.setEstado_pista(false);
	        // Agregar la pista a la lista
	        lista.add(p);
	    }

	    return lista;
	}

	
	
	
	
	

}
