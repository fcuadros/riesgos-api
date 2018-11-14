package com.tp2.modulo.sgr.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import javax.ws.rs.Path;

import com.tp2.modulo.sgr.model.RespuestaResponse;
import com.tp2.modulo.sgr.service.AlertaService;

@Path("/alertas")
public class AlertaController {

		AlertaService AlertaService = new AlertaService();
		Gson gson = new Gson();
		
	
		

	}
