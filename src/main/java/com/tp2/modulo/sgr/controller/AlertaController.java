package com.tp2.modulo.sgr.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tp2.modulo.sgr.model.Alerta;
import com.tp2.modulo.sgr.model.RespuestaResponse;
import com.tp2.modulo.sgr.service.AlertaService;


@Path("/alertas")
public class AlertaController {
	
	AlertaService alertaService = new AlertaService();
	Gson gson = new Gson();	
	
	// GET  /alertas - Devuelve una lista de alertas
	// GET  /alertas/12- Devuelve una alerta especifica #12
	// POST /alertas - Crea una nueva alerta
	// GET  /alertas/enviar/correo@domain.com - Realiza envio de un correo #correo@domain.com
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlertas() {
		
		ArrayList<Alerta> listaRiesgos = alertaService.getAlertas();
		String json = gson.toJson(listaRiesgos);		
		return json;
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registrarAlerta(Alerta alerta) {
		
		RespuestaResponse response = alertaService.registrarAlerta(alerta);
		String json = gson.toJson(response);		
		return json;
	}	
	
	@GET
	@Path("/{idOpcionMenu}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlertaPorOpcionMenu(@PathParam("idOpcionMenu") int idOpcionMenu) {
		
		Alerta alerta = alertaService.getAlertaByOpcionMenu(idOpcionMenu);
		String json = gson.toJson(alerta);		
		return json;
	}
	
	@GET
	@Path("/enviar/{correo}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlertas(@PathParam("correo") String correo) throws UnirestException {
		
		AlertaService alertaService = new AlertaService();		
		alertaService.sendSimpleMessagesv2(correo, "Notificación de riesgo","cuerpo", null);
		
		Gson gson = new Gson();		
		return gson.toJson("ok");		
	}

}
