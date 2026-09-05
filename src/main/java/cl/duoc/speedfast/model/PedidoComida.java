package cl.duoc.speedfast.model;

public class PedidoComida extends Pedido {
    private final boolean mochilaEnBuenEstado;

    public PedidoComida(String idPedido, String direccionEntrega, double distanciaKm, boolean mochilaEnBuenEstado) {
        super(TipoPedido.COMIDA, idPedido, direccionEntrega, distanciaKm);
        this.mochilaEnBuenEstado = mochilaEnBuenEstado;
    }

    @Override
    public boolean validarPedido() {
        if (!mochilaEnBuenEstado) {
            this.cancelar();
            System.out.println("✘ " + getTipoPedido().getNombrePedido() +
                    " #" + getIdPedido() + " cancelado por el estado de la mochile térmica.");
            return false;
        }

        return true;
    }

    @Override
    protected int calcularTiempoEntrega() {
        return (int) (15 + 2 * getDistanciaKm());
    }
}
