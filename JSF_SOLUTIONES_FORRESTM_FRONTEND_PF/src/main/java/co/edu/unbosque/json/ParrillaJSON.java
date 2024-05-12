package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.Parrilla;

public class ParrillaJSON {

	private static URL url;
	private static String site = "http://localhost:8081/api/";
	private String data;
	
	public ParrillaJSON() {
		
	}

	public int postJSON(Parrilla pdto, Emisora emi) {
		  try {
				url = new URL(site + "parrillas");
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
		        data="{\n" +
		        	    "  \"fecha_parrilla\": \"" + pdto.getParrilla_date().toString() + "\",\n" +
		        	    "  \"fk_emisora_parrilla\": {\n" +
		        	    "    \"id_emisora\": " + emi.getId_emisora() + ",\n" +
		        	    "    \"nombre_emisora\": \"" + emi.getName_emisora() + "\",\n" +
		        	    "    \"modo_emisora\": \"" + emi.getModo_emisora() + "\",\n" +
		        	    "    \"tipo_emisora\": \"" + emi.getMusica_emisora() + "\"\n" +
		        	    "  }\n" +
		        	    "}";
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

	public ArrayList<Parrilla> getJSON() throws IOException, ParseException {
		url= new URL (site+"parrillas");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Parrilla> lista = new ArrayList<Parrilla>();
		lista = parsingParrilla(json);
		http.disconnect();
		return lista;
		
	}

	private ArrayList<Parrilla> parsingParrilla(String json) throws ParseException {
	    ArrayList<Parrilla> lista = new ArrayList<>();

	    JSONParser jsonParser = new JSONParser();
	    JSONArray jsonArray = (JSONArray) jsonParser.parse(json);

	    for (Object obj : jsonArray) {
	        JSONObject parrillaObj = (JSONObject) obj;

	        Parrilla p = new Parrilla();
	        p.setId_parrilla(Integer.parseInt(parrillaObj.get("id_parrilla").toString()));

	        String fechaString = parrillaObj.get("fecha_parrilla").toString();
	        LocalDate fechaParrilla = LocalDate.parse(fechaString);
	        p.setParrilla_date(fechaParrilla);

	        JSONObject emisoraObj = (JSONObject) parrillaObj.get("fk_emisora_parrilla");
	        Emisora emi = new Emisora();
	        emi.setId_emisora(Integer.parseInt(emisoraObj.get("id_emisora").toString()));

	        p.setFk_id_emisora((emi.getId_emisora())); 

	        lista.add(p);
	    }

	    return lista;
	}

}
