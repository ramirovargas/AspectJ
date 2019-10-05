package control;

import modelo.Banco;
import modelo.Cuenta;

import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
/**
 * Comando usado para retirar dinero
 */
public class ComandoRetirar implements Comando {

	final static Logger logger = Logger.getLogger(ComandoRetirar.class);
	@Override
	public String getNombre() {
		return "Retirar dinero";
	}

	@SuppressWarnings("resource")
	@Override
	public void ejecutar(Banco contexto) throws Exception {
		
		System.out.println("Retiro de Dinero");
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
		
		System.out.println("Ingrese el valor a retirar");
		String valor = console.nextLine();
	
		try {
			long valorNumerico = Long.parseLong(valor);
			cuenta.retirar(valorNumerico);
			logger.info("Cuenta: "+cuenta.getNumero() +","+"retiro: " +valorNumerico);

		
		} catch (NumberFormatException e) {
			throw new Exception("Valor a retirar no válido : " + valor);
		}
	}

}
