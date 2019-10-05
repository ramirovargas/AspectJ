package cajero;

import control.*;

import java.util.ArrayList;
import java.util.List;

public class CajeroTipoA implements Cajero {
    @Override
    public List<Comando> cargaComandos() {
        // crea los comandos que se van a usar en la aplicación
        List<Comando> comandos = new ArrayList<Comando>();

        comandos.add(new ComandoListarCuentas());
        comandos.add(new ComandoRetirar());
        comandos.add(new ComandoConsignar());
        comandos.add(new ComandoTransferir());

        return comandos;
    }
}
