package co.edu.unbosque.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.Emisora;

public class EmisoraJSON {
	
	private static URL url;
	private static String site = "http://localhost:8081/api/";
	private String data;
	
	public EmisoraJSON() {
		// TODO Auto-generated constructor stub
	}


	public int sendPost(Emisora emisora) {
		  try {
				url = new URL(site + "emisoras");
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
		                + "\"nombre_emisora\":\"" + emisora.getName_emisora()
		                + "\",\"modo_emisora\": \"" + emisora.getModo_emisora()
		                + "\",\"tipo_emisora\": \"" + emisora.getMusica_emisora()
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


	public static ArrayList<Emisora> getJSON() throws IOException, ParseException{
		url= new URL (site+"emisoras");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Emisora> lista = new ArrayList<Emisora>();
		lista = parsingEmisoras(json);
		http.disconnect();
		return lista;
	}


	private static ArrayList<Emisora> parsingEmisoras(String json)throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Emisora> lista = new ArrayList<Emisora>();
		JSONArray usuarios = (JSONArray) jsonParser.parse(json);
		Iterator i = usuarios.iterator();
		while(i.hasNext()) {
			JSONObject innerOBJ = (JSONObject) i.next();
			Emisora edto = new Emisora();
			edto.setId_emisora(Integer.parseInt(innerOBJ.get("id_emisora").toString()));
			edto.setName_emisora(innerOBJ.get("nombre_emisora").toString());
			edto.setModo_emisora(innerOBJ.get("modo_emisora").toString());
			edto.setMusica_emisora(innerOBJ.get("tipo_emisora").toString());
			lista.add(edto);
		}
		return lista;
		
	
	}


	public Emisora getOne(int id) throws IOException, ParseException{
		
		if(id == 0 ) {
			id=1;
			URL url = new URL(site + "emisoras/" + id);
	        HttpURLConnection http = (HttpURLConnection) url.openConnection();
	        http.setRequestMethod("GET");
	        http.setRequestProperty("Accept", "application/json");
	        InputStream respuesta = http.getInputStream();
	        
	        // Leer el contenido del InputStream y convertirlo a una cadena JSON
	        BufferedReader reader = new BufferedReader(new InputStreamReader(respuesta));
	        StringBuilder responseStringBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            responseStringBuilder.append(line);
	        }
	        String jsonResponseString = responseStringBuilder.toString();
	        
	        // Parsear la cadena JSON
	        JSONParser parser = new JSONParser();
	        JSONObject jsonResponse = (JSONObject) parser.parse(jsonResponseString);
	        
	        // Obtener los datos del JSON
	        Long idEmisora = (Long) jsonResponse.get("id_emisora");
	        String nombreEmisora = (String) jsonResponse.get("nombre_emisora");
	        String modoEmisora = (String) jsonResponse.get("modo_emisora");
	        String tipoEmisora = (String) jsonResponse.get("tipo_emisora");
	        
	    
	        // Crear y retornar un objeto EmisoraDTO con los datos obtenidos
	        Emisora em = new Emisora();
	        em.setId_emisora(idEmisora.intValue());
	        em.setName_emisora(nombreEmisora);
	        em.setModo_emisora(modoEmisora);
	        em.setMusica_emisora(tipoEmisora);
	        return em;
		}else {
			
		}
		return null;
		
		
	       
	}
	
	

}
