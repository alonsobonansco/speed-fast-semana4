package cl.duoc.speedfast.model;

/**
 * Subclase de Pedido. Su atributo propio es LIMITE_DISTANCIA_KM: valor límite que un
 * repartidor puede estar del objetivo para realizar una entrega express.
 */
public class PedidoExpress extends Pedido {
    private static final double LIMITE_DISTANCIA_KM = 20.0;

    /**
     * Construye un pedido express.
     *
     * @param idPedido         Identificador único de la orden.
     * @param direccionEntrega Destino físico del despacho.
     * @param distanciaKm      Trayecto en kilómetros.
     */
    public PedidoExpress(String idPedido, String direccionEntrega, double distanciaKm) {
        super(TipoPedido.EXPRESS, idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public boolean validarPedido() {
        if (getDistanciaKm() > LIMITE_DISTANCIA_KM) {
            this.cancelar();
            System.out.println("✘ " + getTipoPedido().getNombrePedido() +
                    " #" + getIdPedido() + " cancelado por la distancia.");
            return false;
        }

        return true;
    }

    @Override
    protected int calcularTiempoEntrega() {
        return (getDistanciaKm() > 5) ? 15 : 10;
    }
}
