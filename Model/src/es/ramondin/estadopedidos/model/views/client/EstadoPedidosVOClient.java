package es.ramondin.estadopedidos.model.views.client;

import es.ramondin.estadopedidos.model.views.common.EstadoPedidosVO;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 25 11:09:20 CEST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EstadoPedidosVOClient extends ViewUsageImpl implements EstadoPedidosVO {
    /**
     * This is the default constructor (do not remove).
     */
    public EstadoPedidosVOClient() {
    }

    public void limpiaCacheConnx() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"limpiaCacheConnx",null,null);
        return;
    }
}
