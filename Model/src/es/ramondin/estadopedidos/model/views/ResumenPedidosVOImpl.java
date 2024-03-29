package es.ramondin.estadopedidos.model.views;

import es.ramondin.estadopedidos.model.views.common.ResumenPedidosVO;

import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 25 14:53:47 CEST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ResumenPedidosVOImpl extends ViewObjectImpl implements ResumenPedidosVO {
    /**
     * This is the default constructor (do not remove).
     */
    public ResumenPedidosVOImpl() {
    }
    
    public void limpiaCacheConnx() {
        String queryOrig = this.getQuery();
        
        this.setQuery("");
        this.executeQuery();
        
        this.setQuery(queryOrig);
    }
    
    private void anadeSubtotal(RowSetIterator rsiResumenPed, Integer index, Integer actividad, String descActividad, Integer pedidosTotales, Integer adelantadoAceptado, Integer urgente, Integer noAdelantado, Integer adelantadoAceptadoRetrasado, Integer noAdelantadoRetrasado) {
        ResumenPedidosVORowImpl rowResumenPed = (ResumenPedidosVORowImpl)this.createRow();
        
        rowResumenPed.setActividad(actividad);
        rowResumenPed.setDescActividad(descActividad);
        rowResumenPed.setFamiestp(999999);
        rowResumenPed.setPedidosTotales(pedidosTotales);
        rowResumenPed.setAdelantadoAceptado(adelantadoAceptado);
        rowResumenPed.setUrgente(urgente);
        rowResumenPed.setNoAdelantado(noAdelantado);
        rowResumenPed.setAdelantadoAceptadoRetrasado(adelantadoAceptadoRetrasado);
        rowResumenPed.setNoAdelantadoRetrasado(noAdelantadoRetrasado);
        
        if (index < 0)
            index = 0;

        rsiResumenPed.insertRowAtRangeIndex(index, rowResumenPed);
    }
        
    public void executeWithParamsEdit(Integer desdeAnoPed, Integer hastaAnoPed, Integer desdeFecha, Integer hastaFecha, Integer margenDias) {
        this.setEjecutarQueryBV(1);
        this.setDesdeAnoPedBV(desdeAnoPed);
        this.setHastaAnoPedBV(hastaAnoPed);
        this.setDesdeFechaBV(desdeFecha);
        this.setHastaFechaBV(hastaFecha);
        this.setMargenDiasBV(margenDias);
        this.executeQuery();
        
        RowSetIterator rsiResumenPed = this.getRowSetIterator();
        
        Integer actividadAnt = null, actividadAct = null;
        String descActividad = null;
        Integer pedidosTotalesActiv = 0, adelantadoAceptadoActiv = 0, urgenteActiv = 0, noAdelantadoActiv = 0, adelantadoAceptadoRetrasadoActiv = 0, noAdelantadoRetrasadoActiv = 0;
        Integer pedidosTotales = 0, adelantadoAceptado = 0, urgente = 0, noAdelantado = 0, adelantadoAceptadoRetrasado = 0, noAdelantadoRetrasado = 0;
        
        if (rsiResumenPed.getRowCount() != 0) {
            ResumenPedidosVORowImpl rowResumenPed = (ResumenPedidosVORowImpl)rsiResumenPed.first();
            int rowIndex = 0;
            
            while (rowResumenPed != null) {
                actividadAct = rowResumenPed.getActividad().intValue();
                
                if (actividadAnt == null) {
                    actividadAnt = actividadAct;
                    descActividad = "SUBTOTAL " + rowResumenPed.getDescActividad();
                } else {
                    if (actividadAct != actividadAnt) {
                        anadeSubtotal(rsiResumenPed, rowIndex, actividadAnt, descActividad, pedidosTotalesActiv, adelantadoAceptadoActiv, urgenteActiv, noAdelantadoActiv, adelantadoAceptadoRetrasadoActiv, noAdelantadoRetrasadoActiv);
                        rowIndex++;
                        
                        pedidosTotalesActiv = 0;
                        adelantadoAceptadoActiv = 0;
                        urgenteActiv = 0;
                        noAdelantadoActiv = 0;
                        adelantadoAceptadoRetrasadoActiv = 0;
                        noAdelantadoRetrasadoActiv = 0;
                        
                        actividadAnt = actividadAct;
                        descActividad = "SUBTOTAL " + rowResumenPed.getDescActividad();
                    }
                }

                pedidosTotalesActiv += rowResumenPed.getPedidosTotales();
                adelantadoAceptadoActiv += rowResumenPed.getAdelantadoAceptado();
                urgenteActiv += rowResumenPed.getUrgente();
                noAdelantadoActiv += rowResumenPed.getNoAdelantado();
                adelantadoAceptadoRetrasadoActiv += rowResumenPed.getAdelantadoAceptadoRetrasado();
                noAdelantadoRetrasadoActiv += rowResumenPed.getNoAdelantadoRetrasado();
                
                pedidosTotales += rowResumenPed.getPedidosTotales();
                adelantadoAceptado += rowResumenPed.getAdelantadoAceptado();
                urgente += rowResumenPed.getUrgente();
                noAdelantado += rowResumenPed.getNoAdelantado();
                adelantadoAceptadoRetrasado += rowResumenPed.getAdelantadoAceptadoRetrasado();
                noAdelantadoRetrasado += rowResumenPed.getNoAdelantadoRetrasado();
                        
                rowResumenPed = (ResumenPedidosVORowImpl)rsiResumenPed.next();
                rowIndex++;
            }
            
            if (actividadAnt != null) {
                anadeSubtotal(rsiResumenPed, rowIndex, actividadAnt, descActividad, pedidosTotalesActiv, adelantadoAceptadoActiv, urgenteActiv, noAdelantadoActiv, adelantadoAceptadoRetrasadoActiv, noAdelantadoRetrasadoActiv);
                rowIndex++;
                
                anadeSubtotal(rsiResumenPed, rowIndex, 999999, "TOTAL", pedidosTotales, adelantadoAceptado, urgente, noAdelantado, adelantadoAceptadoRetrasado, noAdelantadoRetrasado);
            }
        }
    }


    /**
     * Returns the bind variable value for EjecutarQueryBV.
     * @return bind variable value for EjecutarQueryBV
     */
    public Integer getEjecutarQueryBV() {
        return (Integer)getNamedWhereClauseParam("EjecutarQueryBV");
    }

    /**
     * Sets <code>value</code> for bind variable EjecutarQueryBV.
     * @param value value to bind as EjecutarQueryBV
     */
    public void setEjecutarQueryBV(Integer value) {
        setNamedWhereClauseParam("EjecutarQueryBV", value);
    }

    /**
     * Returns the bind variable value for DesdeAnoPedBV.
     * @return bind variable value for DesdeAnoPedBV
     */
    public Integer getDesdeAnoPedBV() {
        return (Integer)getNamedWhereClauseParam("DesdeAnoPedBV");
    }

    /**
     * Sets <code>value</code> for bind variable DesdeAnoPedBV.
     * @param value value to bind as DesdeAnoPedBV
     */
    public void setDesdeAnoPedBV(Integer value) {
        setNamedWhereClauseParam("DesdeAnoPedBV", value);
    }

    /**
     * Returns the bind variable value for HastaAnoPedBV.
     * @return bind variable value for HastaAnoPedBV
     */
    public Integer getHastaAnoPedBV() {
        return (Integer)getNamedWhereClauseParam("HastaAnoPedBV");
    }

    /**
     * Sets <code>value</code> for bind variable HastaAnoPedBV.
     * @param value value to bind as HastaAnoPedBV
     */
    public void setHastaAnoPedBV(Integer value) {
        setNamedWhereClauseParam("HastaAnoPedBV", value);
    }

    /**
     * Returns the bind variable value for DesdeFechaBV.
     * @return bind variable value for DesdeFechaBV
     */
    public Integer getDesdeFechaBV() {
        return (Integer)getNamedWhereClauseParam("DesdeFechaBV");
    }

    /**
     * Sets <code>value</code> for bind variable DesdeFechaBV.
     * @param value value to bind as DesdeFechaBV
     */
    public void setDesdeFechaBV(Integer value) {
        setNamedWhereClauseParam("DesdeFechaBV", value);
    }

    /**
     * Returns the bind variable value for HastaFechaBV.
     * @return bind variable value for HastaFechaBV
     */
    public Integer getHastaFechaBV() {
        return (Integer)getNamedWhereClauseParam("HastaFechaBV");
    }

    /**
     * Sets <code>value</code> for bind variable HastaFechaBV.
     * @param value value to bind as HastaFechaBV
     */
    public void setHastaFechaBV(Integer value) {
        setNamedWhereClauseParam("HastaFechaBV", value);
    }

    /**
     * Returns the bind variable value for MargenDiasBV.
     * @return bind variable value for MargenDiasBV
     */
    public Integer getMargenDiasBV() {
        return (Integer)getNamedWhereClauseParam("MargenDiasBV");
    }

    /**
     * Sets <code>value</code> for bind variable MargenDiasBV.
     * @param value value to bind as MargenDiasBV
     */
    public void setMargenDiasBV(Integer value) {
        setNamedWhereClauseParam("MargenDiasBV", value);
    }
}
