package cl.duoc.speedfast.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {
    private final String nombreRepartidor;
    private final List<Pedido> pedidosOrdenados;

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
                System.out.println("[Repartidor: " + nombreRepartidor + "] Entregando " +
                        pedido.getTipoPedido().getNombrePedido() + " #" + pedido.getIdPedido() + "...");

                int tiempoViaje = 1000 + ThreadLocalRandom.current().nextInt(1500);
                Thread.sleep(tiempoViaje);

                System.out.println("[Repartidor: " + nombreRepartidor + "] Pedido #" + pedido.getIdPedido() +
                        " entregado.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
