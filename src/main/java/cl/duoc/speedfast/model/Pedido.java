package cl.duoc.speedfast.model;

public abstract class Pedido implements Despachable, Cancelable {
    private final TipoPedido tipoPedido;
    private final String idPedido;
    private String direccionEntrega;
    private final double distanciaKm;
    private boolean pedidoActivo = true;
    private String nombreRepartidor = "No asignado";

    public Pedido(TipoPedido tipoPedido, String idPedido, String direccionEntrega, double distanciaKm) {
        if (idPedido == null || idPedido.isBlank()) {
            throw new IllegalArgumentException("El ID del pedido no puede estar vacío.");
        }
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser válida.");
        }
        this.tipoPedido = tipoPedido;
        this.idPedido = idPedido;
        setDireccionEntrega(direccionEntrega);
        this.distanciaKm = distanciaKm;
    }

    @Override
    public void despachar() {
        if (!pedidoActivo) {
            System.out.println("No se puede despachar un pedido cancelado.\n");
            return;
        }
        System.out.println("El pedido ha sido despachado.");
    }

    @Override
    public void cancelar() {
        if (!pedidoActivo) {
            System.out.println("- El pedido #" + idPedido + " ya se encuentra cancelado.\n");
            return;
        }
        pedidoActivo = false;
        System.out.println("- El pedido #" + idPedido + " ha sido cancelado.\n");
    }

    public abstract boolean validarPedido();

    protected abstract int calcularTiempoEntrega();

    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
        System.out.println("[" + tipoPedido + " #" + idPedido + "] asignado a " + nombreRepartidor + ".\n");
    }

    public void mostrarResumen() {
        String textoResumen = """
                \n===================
                %s #%s
                ===================
                
                Dirección: %s
                Distancia: %.1f km
                Tiempo estimado de entrega: %d minutos
                """.formatted(
                tipoPedido.getNombrePedido(), getIdPedido(),
                getDireccionEntrega(),
                getDistanciaKm(),
                calcularTiempoEntrega()
        );

        System.out.println(textoResumen);
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException("La dirección de entrega debe ser válida.");
        }
        this.direccionEntrega = direccionEntrega;
    }

    public boolean isPedidoActivo() {
        return pedidoActivo;
    }
}
