package control;

import modelo.Banco;
import modelo.Cuenta;
import org.apache.log4j.Logger;

/**
 * Comando usado para listar las cuentas 
 */
public class ComandoListarCuentas implements Comando {
	final static Logger logger = Logger.getLogger(ComandoListarCuentas.class);
	@Override
	public String getNombre() {
		return "Listar Cuentas";
	}

	@Override
	public void ejecutar(Banco contexto) throws Exception {
		
		System.out.println("Listado de Cuentas");
		System.out.println();
	
		for (Cuenta cuenta : contexto.getCuentas()) {
			System.out.println(cuenta.getNumero() + " : $ " + cuenta.getSaldo());
		}

		logger.info("cuentas listadas");

	}

}
