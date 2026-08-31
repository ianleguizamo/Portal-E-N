package utils;

import hooks.ReportHooksWeb;

public class EvidenciaUtils {

    /**
     * Anota el paso en el informe Word y guarda la captura.
     *
     * <p>Antes de disparar la camara se espera a que el portal quite su pantalla de carga.
     * Sin eso, una captura tomada justo despues de un clic sale con el spinner "Espera un
     * momento" en vez de la pantalla: el escenario pasa igual en verde y el fallo solo se
     * descubre abriendo el Word. Al estar aqui, la proteccion vale para todas las
     * evidencias del proyecto y no hay que acordarse de ponerla en cada Task.
     */
    public static void registrarCaptura(String paso) {
        EsperarFinDeCarga.ahora();

        ReportHooksWeb.registrarPaso(paso);
        String nombreArchivo = paso.replaceAll("[^a-zA-Z0-9]", "_");
        CapturasPantallasWeb.capturaPantalla(nombreArchivo, paso);
    }
}