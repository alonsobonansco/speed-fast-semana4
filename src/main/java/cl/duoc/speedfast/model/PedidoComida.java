package cl.duoc.speedfast.model;

/**
 * Subclase de Pedido. Su atributo propio es mochilaEnBuenEstado para verificar que
 * la comida llegue en óptimas condiciones.
 */
public class PedidoComida extends Pedido {
    private final boolean mochilaEnBuenEstado;

    /**
     * Construye un pedido de comida.
     *
     * @param idPedido            Identificador único de la orden.
     * @param direccionEntrega    Destino físico del despacho.
     * @param distanciaKm         Trayecto en kilómetros.
     * @param mochilaEnBuenEstado true si la mochila térmica está operativa; false si está dañada.
     */
    public PedidoComida(String idPedido, String direccionEntrega, double distanciaKm, boolean mochilaEnBuenEstado) {
        super(TipoPedido.COMIDA, idPedido, direccionEntrega, distanciaKm);
        this.mochilaEnBuenEstado = mochilaEnBuenEstado;
    }

    @Override
    public boolean validarPedido() {
        if (!mochilaEnBuenEstado) {
            this.cancelar();
            System.out.println("✘ " + getTipoPedido().getNombrePedido() +
                    " #" + getIdPedido() + " cancelado por el estado de la mochila térmica.");
            return false;
        }

        return true;
    }

    @Override
    protected int calcularTiempoEntrega() {
        return (int) (15 + 2 * getDistanciaKm());
    }
}
