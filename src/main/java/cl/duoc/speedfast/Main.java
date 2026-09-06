package cl.duoc.speedfast;

import cl.duoc.speedfast.model.*;
import cl.duoc.speedfast.service.Repartidor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Director de orquesta principal del sistema de despachos SpeedFast.
 * Coordina las fases de recepción secuencial, validación de reglas de negocio,
 * distribución balanceada de carga y activación del motor de hilos concurrente.
 */
public class Main {
    /**
     * Punto de entrada de la aplicación. Orquesta la simulación por etapas
     * utilizando pausas artificiales temporales para el control de flujo visual.
     */
    public static void main(String[] args) throws InterruptedException {
        List<Pedido> listaPedidos = List.of(
                new PedidoComida("001", "calle 1234", 50.0, true),
                new PedidoExpress("002", "calle 1111", 67.9),
                new PedidoEncomienda("003", "avenida 789", 23.8, 28.3),
                new PedidoComida("004", "avenida 444", 31.3, true),
                new PedidoExpress("005", "avenida 232", 13.7),
                new PedidoEncomienda("006", "pasaje 555", 45.7, 41.9)
        );

        System.out.println("""
                \n=========================================
                === RECEPCIÓN Y VALIDACIÓN DE PEDIDOS ===
                =========================================\n""");

        for (Pedido p : listaPedidos) {
            Thread.sleep(800);

            if (p.validarPedido()) {
                p.despachar();
            }
        }

        List<Repartidor> listaRepartidores = List.of(
                new Repartidor("Carlos", listaPedidos.subList(0, 2)),
                new Repartidor("María", listaPedidos.subList(2, 4)),
                new Repartidor("Alberto", listaPedidos.subList(4, 6))
        );

        Thread.sleep(1500);

        System.out.println("""
                \n======================================
                === REPARTO CONCURRENTE DE PEDIDOS ===
                ======================================\n""");

        try (ExecutorService executorService = Executors.newFixedThreadPool(listaRepartidores.size())) {
            listaRepartidores.forEach(executorService::execute);
            executorService.shutdown();
        }

        Thread.sleep(1000);

        listaRepartidores.getFirst().verHistorial();

        // Forma alternativa desde Java 21
        /*try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            listaRepartidores.forEach(executorService::execute);
        }*/
    }
}
