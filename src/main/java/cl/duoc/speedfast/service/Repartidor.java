package cl.duoc.speedfast.service;

import cl.duoc.speedfast.interfaces.Rastreable;
import cl.duoc.speedfast.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable, Rastreable {
    private final String nombreRepartidor;
    private final List<Pedido> pedidosOrdenados;
    private final static List<Pedido> HISTORIAL_PEDIDOS = new CopyOnWriteArrayList<>();

    public Repartidor(String nombreRepartidor, List<Pedido> pedidosIniciales) {
        this.nombreRepartidor = nombreRepartidor;
        List<Pedido> pedidosTemporales = new ArrayList<>(pedidosIniciales);

        pedidosTemporales.sort(null);

        // List.copyOf hace que la lista sea inmutable
        this.pedidosOrdenados = List.copyOf(pedidosTemporales);
    }

    @Override
    public void run() {
        for (Pedido pedido : pedidosOrdenados) {
            try {
                if (pedido.isPedidoActivo()) {
                    System.out.println("[Repartidor: " + nombreRepartidor + "] Entregando " +
                            pedido.getTipoPedido().getNombrePedido() + " #" + pedido.getIdPedido() + "...");

                    int tiempoViaje = 3000 + ThreadLocalRandom.current().nextInt(1500);
                    Thread.sleep(tiempoViaje);

                    System.out.println("[Repartidor: " + nombreRepartidor + "] Pedido #" + pedido.getIdPedido() +
                            " entregado ✔");
                }

                registrarPedido(pedido);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Proceso interrumpido en pedido #" + pedido.getIdPedido());
                return;
            }
        }
    }

    public void registrarPedido(Pedido pedido) {
        if (pedido != null) {
            HISTORIAL_PEDIDOS.add(pedido);
        }
    }

    @Override
    public void verHistorial() {
        if (HISTORIAL_PEDIDOS.isEmpty()) {
            System.out.println("No se han registrado pedidos en esta sesión.");
            return;
        }

        String tituloHistorial = """
                \n======================================
                === HISTORIAL DE TODAS LAS ÓRDENES ===
                ======================================\n""";
        System.out.println(tituloHistorial);

        for (Pedido pedido : HISTORIAL_PEDIDOS) {
            if (pedido.isPedidoActivo()) {
                System.out.println("- " + pedido.getTipoPedido().getNombrePedido() + " #" + pedido.getIdPedido() + " - [Entregado exitósamente]");
            } else {
                System.out.println("- " + pedido.getTipoPedido().getNombrePedido() + " #" + pedido.getIdPedido() + " - [Orden cancelada]");
            }
        }
    }
}
