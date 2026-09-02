package cl.duoc.speedfast.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {
    private final String nombreRepartidor;
    private final List<Pedido> listaPedidos;

    public Repartidor(String nombreRepartidor, List<Pedido> listaPedidos) {
        this.nombreRepartidor = nombreRepartidor;
        // List.copyOf hace que la lista sea inmutable
        this.listaPedidos = List.copyOf(listaPedidos);
    }

    @Override
    public void run() {
        for (Pedido pedido : listaPedidos) {
            try {
                System.out.println("[Repartidor: " + nombreRepartidor + "] Entregando " +
                        pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + "...");

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
