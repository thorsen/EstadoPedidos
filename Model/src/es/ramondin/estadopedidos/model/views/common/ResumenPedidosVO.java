package es.ramondin.estadopedidos.model.views.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 25 14:54:16 CEST 2014
// ---------------------------------------------------------------------
public interface ResumenPedidosVO extends ViewObject {
    void limpiaCacheConnx();

    void executeWithParamsEdit(Integer desdeAnoPed, Integer hastaAnoPed, Integer desdeFecha, Integer hastaFecha, Integer margenDias);
}
