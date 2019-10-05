package control;

import modelo.Banco;
import modelo.Cuenta;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Comando usado para consignar dinero
 */
public class ComandoConsignar implements Comando {

	final static Logger logger = Logger.getLogger(ComandoConsignar.class);

	@Override
	public String getNombre() {
		return "Consignar dinero";
	}

	@SuppressWarnings("resource")
	@Override
	public void ejecutar(Banco contexto) throws Exception {
		
		System.out.println("Consignación de Dinero");
		System.out.println();
		
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);
		
		// Ingresa los datos
		System.out.println("Ingrese el número de cuenta");
		String numeroDeCuenta = console.nextLine();
		
		Cuenta cuenta = contexto.buscarCuenta(numeroDeCuenta);
		if (cuenta == null) {
			throw new Exception("No existe cuenta con el número " + numeroDeCuenta);
		}
		
		System.out.println("Ingrese el valor a consignar");
		String valor = console.nextLine();
	
		try {
			long valorNumerico = Long.parseLong(valor);
			cuenta.consignar(valorNumerico);
			logger.info("Cuenta: "+cuenta.getNumero() +","+"consigno: " +valorNumerico);
		
		} catch (NumberFormatException e) {
			throw new Exception("Valor a consignar no válido : " + valor);
		}
	}

}
