package cl.duoc.speedfast;

import cl.duoc.speedfast.model.*;
import cl.duoc.speedfast.service.Repartidor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Pedido pedidoComida1 = new PedidoComida("001", "calle 1234", 50.0, true);
        Pedido pedidoExpress1 = new PedidoExpress("002", "calle 1111", 67.9);
        Pedido pedidoEncomienda1 = new PedidoEncomienda("003", "avenida 789", 23.8, 28.3);
        Pedido pedidoComida2 = new PedidoComida("004", "avenida 444", 31.3, false);
        Pedido pedidoExpress2 = new PedidoExpress("005", "avenida 232", 13.7);
        Pedido pedidoEncomienda2 = new PedidoEncomienda("006", "pasaje 555", 45.7, 41.9);

        List<Pedido> listaPedidos = List.of(pedidoComida1, pedidoExpress1, pedidoEncomienda1, pedidoComida2, pedidoExpress2, pedidoEncomienda2);

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

        Repartidor repartidor1 = new Repartidor("Carlos", List.of(pedidoComida1, pedidoExpress1));
        Repartidor repartidor2 = new Repartidor("María", List.of(pedidoEncomienda1, pedidoComida2));
        Repartidor repartidor3 = new Repartidor("Alberto", List.of(pedidoExpress2, pedidoEncomienda2));

        List<Repartidor> listaRepartidores = List.of(repartidor1, repartidor2, repartidor3);

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
