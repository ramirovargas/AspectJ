package aspectsCajero;

import org.apache.log4j.Logger;

public aspect logTranx {
    final static Logger logger = Logger.getLogger(logTranx.class);
    pointcut todosLosMetodos(): execution (public * * (..) );
    before(): todosLosMetodos () { logger.info("Ejecutando : " + thisJoinPoint); }
}
