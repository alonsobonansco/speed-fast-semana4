package cl.duoc.speedfast.model;

public class PedidoEncomienda extends Pedido {
    private static final double CAPACIDAD_MAXIMA_KG = 40.0;
    private final double pesoEncomienda;

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
