package cl.duoc.speedfast.model;

/**
 * Subclase de Pedido. Sus atributos son CAPACIDAD_MAXIMA_KG y pesoEncomienda: el primero es
 * el valor máximo permitido para el transporte y el personal; el segundo, el peso real
 * de la encomienda.
 */
public class PedidoEncomienda extends Pedido {
    private static final double CAPACIDAD_MAXIMA_KG = 40.0;
    private final double pesoEncomienda;

    /**
     * Construye un pedido de encomienda.
     *
     * @param idPedido         Identificador único de la orden.
     * @param direccionEntrega Destino físico del despacho.
     * @param distanciaKm      Trayecto en kilómetros.
     * @param pesoEncomienda   Peso de la encomienda en kilogramos.
     * @throws IllegalArgumentException Si el peso de la encomienda es menor o igual a cero.
     */
    public PedidoEncomienda(String idPedido, String direccionEntrega, double distanciaKm, double pesoEncomienda) {
        super(TipoPedido.ENCOMIENDA, idPedido, direccionEntrega, distanciaKm);

        if (pesoEncomienda <= 0) {
            throw new IllegalArgumentException("El peso de la encomienda debe ser válido.");
        }
        this.pesoEncomienda = pesoEncomienda;
    }

    @Override
    public boolean validarPedido() {
        if (pesoEncomienda > CAPACIDAD_MAXIMA_KG) {
            this.cancelar();
            System.out.println("✘ " + getTipoPedido().getNombrePedido() +
                    " #" + getIdPedido() + " cancelado por el peso de la encomienda.");
            return false;
        }

        return true;
    }

    @Override
    protected int calcularTiempoEntrega() {
        return (int) (20 + 1.5 * getDistanciaKm());
    }
}
