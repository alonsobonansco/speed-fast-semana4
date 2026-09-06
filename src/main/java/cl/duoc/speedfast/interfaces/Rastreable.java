package cl.duoc.speedfast.interfaces;

/**
 * Define el contrato de comportamiento para los componentes encargados de auditar,
 * rastrear y consolidar las órdenes de pedidos en el sistema.
 */
public interface Rastreable {
    /**
     * Muestra el historial de todas las acciones registradas.
     * Las clases que implementen este método deben recorrer las
     * colecciones de almacenamiento y formatear la salida visual de las órdenes procesadas.
     */
    void verHistorial();
}
