import com.mashape.unirest.http.exceptions.UnirestException;
import com.tp2.modulo.sgr.service.AlertaService;

public class Test {

	public static void main(String[] args) throws UnirestException {
		// TODO Auto-generated method stub
		
		//Envío de correos test
		
		AlertaService alerta = new AlertaService();
		
		
		alerta.sendSimpleMessagesv2("djuarez7@hotmail.com", "hola", "hola 2", null);

	}

}
