package cl.duoc.speedfast.model;

public enum TipoPedido {
    COMIDA("PEDIDO COMIDA", 1),
    EXPRESS("PEDIDO EXPRESS", 2),
    ENCOMIENDA("PEDIDO ENCOMIENDA", 3);

    private final String nombrePedido;
    private final int nivelPrioridad;

    // El constructor de un enum siempre es privado internamente
    TipoPedido(String nombrePedido, int nivelPrioridad) {
        this.nombrePedido = nombrePedido;
        this.nivelPrioridad = nivelPrioridad;

    }

    public String getNombrePedido() {
        return nombrePedido;
    }

    public int getNivelPrioridad() {
        return nivelPrioridad;
    }
}
