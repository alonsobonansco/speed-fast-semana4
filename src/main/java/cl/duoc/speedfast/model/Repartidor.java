package cl.duoc.speedfast.model;

import java.util.ArrayList;
import java.util.List;

public class Repartidor implements Runnable {
    private String nombreRepartidor;
    private List<Pedido> listaPedidos = new ArrayList<>();


    @Override
    public void run() {
        System.out.println("Ejecutando la entrega secuencial de los pedidos...");
    }
}
