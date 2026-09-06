package cl.duoc.speedfast.interfaces;

/**
 * Contrato de comportamiento para las entidades que pueden ser despachadas.
 */
public interface Despachable {
    /**
     * Ejecuta el proceso de inicio de despacho.
     * Las clases que implementen este método deben encargarse de verificar
     * sus controles de seguridad internos antes de delegar el movimiento al vehículo.
     */
    void despachar();
}
