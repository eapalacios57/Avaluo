package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros;

import java.util.ArrayList;
import java.util.List;

public class ParametrosModel {

    private List<UnidadModel> listUnidadModel;

    private List<MunicipioDepartamentoModel> listMunicipioDepartamentoModel;

    private List<ProductoRelacionadoModel> listProductoRelacionadoModel;

    private List<ParametrosValoresModel> listParametrosValoresModel;

    public ParametrosModel() {
    	listMunicipioDepartamentoModel = new ArrayList<>();
    	listProductoRelacionadoModel = new ArrayList<>();
    	listParametrosValoresModel = new ArrayList<>();
    }

    public List<UnidadModel> getListUnidadModel() {
        return listUnidadModel;
    }

    public void setListUnidadModel(List<UnidadModel> listUnidadModel) {
        this.listUnidadModel = listUnidadModel;
    }

    public List<MunicipioDepartamentoModel> getListMunicipioDepartamentoModel() {
        return listMunicipioDepartamentoModel;
    }

    public void setListMunicipioDepartamentoModel(List<MunicipioDepartamentoModel> listMunicipioDepartamentoModel) {
        this.listMunicipioDepartamentoModel = listMunicipioDepartamentoModel;
    }

    public List<ProductoRelacionadoModel> getListProductoRelacionadoModel() {
        return listProductoRelacionadoModel;
    }

    public void setListProductoRelacionadoModel(List<ProductoRelacionadoModel> listProductoRelacionadoModel) {
        this.listProductoRelacionadoModel = listProductoRelacionadoModel;
    }

    public List<ParametrosValoresModel> getListParametrosValoresModel() {
        return listParametrosValoresModel;
    }

    public void setListParametrosValoresModel(List<ParametrosValoresModel> listParametrosValoresModel) {
        this.listParametrosValoresModel = listParametrosValoresModel;
    }
}
