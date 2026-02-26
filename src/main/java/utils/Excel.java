package utils;

public class Excel {

  private String rutaExcel;
  private String hojaExcel;
  private boolean contieneCabecera;
  private int filaLeer;

  public Excel(String rutaExcel, String hojaExcel, boolean contieneCabecera, int filaLeer) {
    super();
    this.rutaExcel = rutaExcel;
    this.hojaExcel = hojaExcel;
    this.contieneCabecera = contieneCabecera;
    this.filaLeer = filaLeer;
  }

  public String getRutaExcel() {
    return rutaExcel;
  }

  public void setRutaExcel(String rutaExcel) {
    this.rutaExcel = rutaExcel;
  }

  public String getHojaExcel() {
    return hojaExcel;
  }

  public void setHojaExcel(String hojaExcel) {
    this.hojaExcel = hojaExcel;
  }

  public boolean isContieneCabecera() {
    return contieneCabecera;
  }

  public void setContieneCabecera(boolean contieneCabecera) {
    this.contieneCabecera = contieneCabecera;
  }

  public int getFilaLeer() {
    return filaLeer;
  }

  public void setFilaLeer(int filaLeer) {
    this.filaLeer = filaLeer;
  }
}
