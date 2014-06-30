package es.ramondin.estadopedidos.view.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.input.RichInputDate;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


public class ResumenPedidosBean {
    private RichInputDate ridDesdeFecha;
    private RichInputDate ridHastaFecha;

    private static final Integer MARGEN_DIAS_DEF = 5;
    private final Integer[] columnasFechaRMD = new Integer[] { 3, 4, 5, 6 };

    public ResumenPedidosBean() {
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String refrescarTabla() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        DateFormat dfAno = new SimpleDateFormat("yyyy");
        Date desdeFecha = ((Date)ridDesdeFecha.getValue());
        Integer desdeFechaInt = null;
        Integer desdeAnoPed = null;
        if (desdeFecha != null) {
            desdeFechaInt = Integer.parseInt(df.format(desdeFecha));
            desdeAnoPed = Integer.parseInt(dfAno.format(desdeFecha));
        }

        Date hastaFecha = ((Date)ridHastaFecha.getValue());
        Integer hastaFechaInt = null;
        Integer hastaAnoPed = null;
        if (hastaFecha != null) {
            hastaFechaInt = Integer.parseInt(df.format(hastaFecha));
            hastaAnoPed = Integer.parseInt(dfAno.format(hastaFecha));
        }

        OperationBinding operation = this.getBindings().getOperationBinding("limpiaCacheConnx");
        operation.execute();

//        operation = this.getBindings().getOperationBinding("ExecuteWithParams");
//        operation.getParamsMap().put("EjecutarQueryBV", 1);
//        operation.getParamsMap().put("DesdeAnoPedBV", desdeAnoPed);
//        operation.getParamsMap().put("HastaAnoPedBV", hastaAnoPed);
//        operation.getParamsMap().put("DesdeFechaBV", desdeFechaInt);
//        operation.getParamsMap().put("HastaFechaBV", hastaFechaInt);
//        operation.getParamsMap().put("MargenDiasBV", MARGEN_DIAS_DEF);
//        operation.execute();
        
        operation = this.getBindings().getOperationBinding("executeWithParamsEdit");
        operation.getParamsMap().put("desdeAnoPed", desdeAnoPed);
        operation.getParamsMap().put("hastaAnoPed", hastaAnoPed);
        operation.getParamsMap().put("desdeFecha", desdeFechaInt);
        operation.getParamsMap().put("hastaFecha", hastaFechaInt);
        operation.getParamsMap().put("margenDias", MARGEN_DIAS_DEF);
        operation.execute();

        return null;
    }

    public void setRidDesdeFecha(RichInputDate ridDesdeFecha) {
        this.ridDesdeFecha = ridDesdeFecha;
    }

    public RichInputDate getRidDesdeFecha() {
        return ridDesdeFecha;
    }

    public void setRidHastaFecha(RichInputDate ridHastaFecha) {
        this.ridHastaFecha = ridHastaFecha;
    }

    public RichInputDate getRidHastaFecha() {
        return ridHastaFecha;
    }

    public Integer[] getColumnasFechaRMD() {
        return columnasFechaRMD;
    }
}
