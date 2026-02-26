package utils;

import hooks.ReportHooksWeb;

public class EvidenciaUtils {

    public static void registrarCaptura(String paso) {
        ReportHooksWeb.registrarPaso(paso);
        String nombreArchivo = paso.replaceAll("[^a-zA-Z0-9]", "_");
        CapturasPantallasWeb.capturaPantalla(nombreArchivo, paso);
    }
}