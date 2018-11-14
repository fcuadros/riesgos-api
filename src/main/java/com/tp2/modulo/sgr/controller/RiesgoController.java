package com.tp2.modulo.sgr.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.tp2.modulo.sgr.model.RespuestaResponse;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.model.TipoRiesgo;
import com.tp2.modulo.sgr.service.RiesgoService;

@Path("/riesgos")
public class RiesgoController{
	
	RiesgoService riesgoService = new RiesgoService();
	Gson gson = new Gson();
	
	//	Lista todos los riesgos
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRiesgos() {
		
		ArrayList<Riesgo> listaRiesgos = riesgoService.getRiesgos();
		String json = gson.toJson(listaRiesgos);
		
		return json;
	}
	
	//	Lista un riesgo por id
	@GET
	@Path("/{riesgoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRiesgo() {
		
		ArrayList<Riesgo> listaRiesgos = riesgoService.getRiesgos();
		String json = gson.toJson(listaRiesgos);
		
		return json;
	}
	
	// Registra un riesgo
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registrarRiesgo(Riesgo riesgo) {
		
		RespuestaResponse response = riesgoService.registrarRiesgo(riesgo);
		String json = gson.toJson(response);
		
		return json;
	}
	
	// Actualiza un riesgo por id
	@PUT
	@Path("/{riesgoId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String actualizarRiesgo(@PathParam("riesgoId") int idRiesgo, Riesgo riesgo) {
		riesgo.setRiesgoId(idRiesgo);
		RespuestaResponse response = riesgoService.actualizarRiesgo(riesgo);
		String json = gson.toJson(response);
		
		return json;
	}
	
	// Elimina un riesgo por id
	@DELETE
	@Path("/{riesgoId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminarRiesgo(@PathParam("riesgoId") int idRiesgo) {
		RespuestaResponse response = riesgoService.eliminarRiesgo(idRiesgo);
		String json = gson.toJson(response);		
		return json;
	}
	
	@GET
	@Path("/obtenerNumeroRiegosPorNivel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String obtenerNumeroRiesgosPorNivel(@QueryParam("anio") int anio, @QueryParam("tipoRiesgo") int tipoRiesgo, @QueryParam("mes") int mes)
	{
		Map<String, Integer> map = riesgoService.obtenerNumeroRiesgosPorNivel(Integer.valueOf(anio), Integer.valueOf(mes), Integer.valueOf(tipoRiesgo));		    
		String json = gson.toJson(map);		    
		return json;
	}
		  
	@GET
	@Path("/getTipoRiesgo")	  
	@Produces(MediaType.APPLICATION_JSON)
	public String getTipoRiesgo()
	{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();		    
		ArrayList<TipoRiesgo> listaTipoRiesgo = riesgoService.getListaTipoRiesgo();		    
		map.put("listaRevisiones", listaTipoRiesgo);
		String json = gson.toJson(map);
		return json;
	}	
	
}
