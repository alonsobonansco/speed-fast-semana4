package cl.duoc.speedfast.model;

public class PedidoExpress extends Pedido {
    private static final double LIMITE_DISTANCIA_KM = 20.0;

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
