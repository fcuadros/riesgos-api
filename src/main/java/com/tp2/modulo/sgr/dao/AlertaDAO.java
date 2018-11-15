package com.tp2.modulo.sgr.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tp2.modulo.sgr.database.ConnectionJDBC;
import com.tp2.modulo.sgr.model.Alerta;
import com.tp2.modulo.sgr.model.Riesgo;

public class AlertaDAO {

	ConnectionJDBC jdbc = new ConnectionJDBC();

	public ArrayList<Alerta> getAlertas() {

		ArrayList<Alerta> listaAlertas = new ArrayList<Alerta>();

		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call INDRASS_Alertas()}");) {

			boolean hadResults = cs.execute();

			System.out.println("Stored procedure called successfully!");

			while (hadResults) {
				ResultSet resultSet = cs.getResultSet();

				while (resultSet.next()) {
					Alerta alerta = new Alerta();

					alerta.setIdAlerta(resultSet.getInt("cod_alertas"));
					alerta.setCorreo(resultSet.getString("correo"));
					alerta.setOpcion(resultSet.getString("cod_opcion_menu"));
					alerta.setEstado(resultSet.getString("estado"));
					alerta.setFechaRegistro(resultSet.getDate("fecha_registro"));
					alerta.setFechaModificacion(resultSet.getDate("fecha_modificacion"));

					listaAlertas.add(alerta);
				}

				hadResults = cs.getMoreResults();
			}

			cs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaAlertas;
	}

	public boolean registrarAlerta(Alerta alerta) {

		boolean respuesta = false;
		Date fechaRegistro = new Date(new java.util.Date().getTime());

		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call INDRASI_Alerta(?,?,?,?)}");) {

			cs.setString(1, alerta.getCorreo());
			cs.setString(2, alerta.getOpcion());
			cs.setString(3, alerta.getEstado());
			cs.setDate(4, fechaRegistro);
			cs.execute();
			System.out.println("Stored procedure called successfully!");
			cs.close();
			respuesta = true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return respuesta;
	}

}
