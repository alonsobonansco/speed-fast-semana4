package cl.duoc.speedfast;

import cl.duoc.speedfast.model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Pedido pedidoComida = new PedidoComida("001", "calle 1234", 50.0, true);
        Pedido pedidoExpress = new PedidoExpress("002", "calle 1111", 67.9);
        Pedido pedidoEncomienda = new PedidoEncomienda("003", "avenida 789", 23.8, 28.3);
        Pedido pedidoComida2 = new PedidoComida("004", "avenida 444", 31.3, true);

        Repartidor repartidor1 = new Repartidor("Carlos", List.of(pedidoComida, pedidoExpress));
        Repartidor repartidor2 = new Repartidor("María", List.of(pedidoEncomienda, pedidoComida2));
        repartidor1.run();
        repartidor2.run();
    }
}
