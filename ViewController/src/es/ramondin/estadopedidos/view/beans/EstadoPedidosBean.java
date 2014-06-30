package es.ramondin.estadopedidos.view.beans;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.GregorianCalendar;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.input.RichInputDate;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class EstadoPedidosBean {
    private static final Integer ANO_PEDIDO_MAX = (new GregorianCalendar()).get(GregorianCalendar.YEAR);
    
    private Integer desdeAnoPedido = ANO_PEDIDO_MAX;
    private Integer hastaAnoPedido = ANO_PEDIDO_MAX;
    private RichInputDate ridDesdeFecha;
    private RichInputDate ridHastaFecha;
    
    private static final Integer MARGEN_DIAS_DEF = 5;
    private final Integer[] columnasFechaRMD = new Integer[]{3, 4, 5, 6};

    public EstadoPedidosBean() {
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String refrescarTabla() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date desdeFecha = ((Date)ridDesdeFecha.getValue());
        Integer desdeFechaInt = null;
        if (desdeFecha != null) {
            desdeFechaInt = Integer.parseInt(df.format(desdeFecha));
        }
        
        Date hastaFecha = ((Date)ridHastaFecha.getValue());
        Integer hastaFechaInt = null;
        if (hastaFecha != null) {
            hastaFechaInt = Integer.parseInt(df.format(hastaFecha));
        }
        
        OperationBinding operation = this.getBindings().getOperationBinding("limpiaCacheConnx");
        operation.execute();
        
        operation = this.getBindings().getOperationBinding("ExecuteWithParams");
        operation.getParamsMap().put("EjecutarQueryBV", 1);
        operation.getParamsMap().put("DesdeAnoPedBV", this.getDesdeAnoPedido());
        operation.getParamsMap().put("HastaAnoPedBV", this.getHastaAnoPedido());
        operation.getParamsMap().put("DesdeFechaBV", desdeFechaInt);
        operation.getParamsMap().put("HastaFechaBV", hastaFechaInt);
        operation.getParamsMap().put("MargenDiasBV", MARGEN_DIAS_DEF);
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

    public Integer getAnoPedidoMax() {
        return ANO_PEDIDO_MAX;
    }

    public void setDesdeAnoPedido(Integer desdeAnoPedido) {
        this.desdeAnoPedido = desdeAnoPedido;
    }

    public Integer getDesdeAnoPedido() {
        return desdeAnoPedido;
    }

    public void setHastaAnoPedido(Integer hastaAnoPedido) {
        this.hastaAnoPedido = hastaAnoPedido;
    }

    public Integer getHastaAnoPedido() {
        return hastaAnoPedido;
    }

    public Integer[] getColumnasFechaRMD() {
        return columnasFechaRMD;
    }
}
