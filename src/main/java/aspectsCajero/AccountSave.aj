package aspectsCajero;

import org.apache.log4j.Logger;

public aspect AccountSave {
    final int MIN_BALANCE = 200000;
    final static Logger logger = Logger.getLogger(AccountSave.class);
// método a reemplazar
   pointcut metodoAReemplazar() : call (void modelo.Cuenta.retirar*(long));
   // reemplace el método // nótese que retorna un double, como el método original
    void around() : metodoAReemplazar() {
        System.out.println("testing #$&5");
        logger.info("vaya vaya");
    }
}
