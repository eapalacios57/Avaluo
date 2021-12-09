package com.segurosbolivar.avaluos.modelo.dto;

public class NodoConversion
{
  private String conversion;
  private String modoSuprimir;

  public NodoConversion(String unaConversion, String unModoSuprimir)
  {
    this.conversion = unaConversion;
    this.modoSuprimir = unModoSuprimir;
  }

  public String getConversion()
  {
    return this.conversion;
  }

  public void setConversion(String conversion)
  {
    this.conversion = conversion;
  }

  public String getModoSuprimir()
  {
    return this.modoSuprimir;
  }

  public void setModoSuprimir(String modoSuprimir)
  {
    this.modoSuprimir = modoSuprimir;
  }
}