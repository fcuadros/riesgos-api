package com.tp2.modulo.sgr.model;

import java.util.Date;

public class Alerta {
	
	private int idAlerta;
	private String correo;
	private String opcion;
	private String estado;
	private Date fechaRegistro;
	private Date fechaModificacion;
	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public int getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(int idAlerta) {
		this.idAlerta = idAlerta;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRiesgo) {
		this.fechaRegistro = fechaRiesgo;
	}
	
}
