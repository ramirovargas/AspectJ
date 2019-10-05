import cajero.CajeroTipoA;
import control.*;
import modelo.Banco;
import modelo.Cuenta;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@EnableAspectJAutoProxy
public class AppCajero {

    /**
     * Programa principal
     *
     * @param args parámetros de línea de comandos. Son ignorados por el programa.
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // crea el banco
        Banco banco = new Banco();

        // crea unas cuentas, para la prueba
        banco.agregarCuenta(new Cuenta("1", "clave", 1000000));
        banco.agregarCuenta(new Cuenta("2", "clave", 2000000));
        banco.agregarCuenta(new Cuenta("3", "clave", 3000000));


        // se crea dependiendo del cajero
        CajeroTipoA ca = new CajeroTipoA();


        // crea los comandos que se van a usar en la aplicación
        List<Comando> comandos = ca.cargaComandos();


        // Ciclo del Programa
        // ==================

        System.out.println("AppCajero Automático");
        System.out.println("=================");
        System.out.println();
        System.out.println("--------------------- Autenticar a el usuario -------------------\n");
        Scanner login = new Scanner(System.in);
        String usuario = login.nextLine();
        String password = login.nextLine();

        if (usuario.equals("123") && password.equals("123")) {


            boolean fin = false;
            do {


                muestraMenuConComandos(comandos);
                System.out.println("X.- Salir");

                // la clase Console no funciona bien en Eclipse
                Scanner console = new Scanner(System.in);
                String valorIngresado = console.nextLine();

                // obtiene el comando a ejecutar
                Comando comandoSeleccionado = retornaComandoSeleccionado(comandos, valorIngresado);
                if (comandoSeleccionado != null) {

                    // intenta ejecutar el comando
                    try {
                        comandoSeleccionado.ejecutar(banco);

                    } catch (Exception e) {
                        // si hay una excepción, muestra el mensaje
                        System.err.println(e.getMessage());
                    }

                }
                // si no se obtuvo un comando, puede ser la opción de salir
                else if (valorIngresado.equalsIgnoreCase("X")) {
                    fin = true;
                }

                System.out.println();


            } while (!fin);
        } else {
            System.out.println("usuario mal identificado");
        }
        System.out.println("Gracias por usar el programa.");
    }


    // Muestra el listado de comandos, para mostrar un menú al usuario
    // muestra el índice en el arreglo de comandos y el nombre del comando
    private static void muestraMenuConComandos(List<Comando> comandos) {

        // muestra los nombres de los comandos
        for (int i = 0; i < comandos.size(); i++) {
            System.out.println(i + ".-" + comandos.get(i).getNombre());
        }
    }


    // dado el texto ingresado por el usuario, retorna el comando correspondiente
    // retorna null si el texto no es un número o no existe ningún comando con ese índice
    private static Comando retornaComandoSeleccionado(List<Comando> comandos, String valorIngresado) {

        Comando comandoSeleccionado = null;

        // el valorIngresado es un número ?
        if (valorIngresado.matches("[0-9]")) {
            int valorSeleccionado = Integer.valueOf(valorIngresado);
            // es un índice válido para la lista de comandos
            if (valorSeleccionado < comandos.size()) {
                comandoSeleccionado = comandos.get(valorSeleccionado);
            }
        }

        return comandoSeleccionado;
    }

}
