package cl.duoc.speedfast.model;

import cl.duoc.speedfast.interfaces.Cancelable;
import cl.duoc.speedfast.interfaces.Despachable;

/**
 * Clase genérica de un pedido y superclase de subtipos de pedidos.
 */
public abstract class Pedido implements Despachable, Cancelable, Comparable<Pedido> {
    private final TipoPedido tipoPedido;
    private final String idPedido;
    private String direccionEntrega;
    private final double distanciaKm;
    private boolean pedidoActivo = true;

    /**
     *
     * @param tipoPedido       Tipo de pedido.
     * @param idPedido         Identificador único de la orden.
     * @param direccionEntrega Destino físico del despacho.
     * @param distanciaKm      Trayecto en kilómetros.
     * @throws IllegalArgumentException Si el idPedido o la direccionEntrega están vacíos  o si
     *                                  la distancia es menor o igual a cero.
     */
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

    /**
     * Ejecuta un mensaje de despacho si el pedido se encuentra activo y no cancelado.
     * Protege el flujo mediante una cláusula de guarda que impide enviar órdenes canceladas.
     */
    @Override
    public void despachar() {
        if (!pedidoActivo) {
            System.out.println("No se puede despachar un pedido cancelado.\n");
            return;
        }

        System.out.println("→ " + getTipoPedido().getNombrePedido() + " #" + getIdPedido() + " listo para reparto.");
    }

    /**
     * Cancela el pedido actual modificando su estado interno a falso.
     * Cuenta con un escudo defensivo que bloquea solicitudes de anulación duplicadas.
     */
    @Override
    public void cancelar() {
        if (!pedidoActivo) {
            System.out.println("- El pedido #" + idPedido + " ya se encuentra cancelado.\n");
            return;
        }

        pedidoActivo = false;
    }

    @Override
    public int compareTo(Pedido otro) {
        int comparacionPrioridad = Integer.compare(
                this.tipoPedido.getNivelPrioridad(),
                otro.tipoPedido.getNivelPrioridad()
        );

        if (comparacionPrioridad != 0) {
            return comparacionPrioridad;
        }

        // En caso de que tengan la misma prioridad se atiende el pedido con menor número
        return this.idPedido.compareTo(otro.idPedido);
    }

    /**
     * Evalúa si las condiciones operativas de la subclase permiten el envío.
     * Cada tipo de pedido implementa sus propias reglas de negocio.
     *
     * @return true si el pedido pasa los controles; false si es rechazado.
     */
    public abstract boolean validarPedido();

    /**
     * Calcula el tiempo estimado que tardará el reparto en llegar al destino.
     * Cada subclase implementa su propia lógica de estimación.
     *
     * @return Tiempo estimado para la entrega en minutos.
     */
    protected abstract int calcularTiempoEntrega();

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
