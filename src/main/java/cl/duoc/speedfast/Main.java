package cl.duoc.speedfast;

import cl.duoc.speedfast.model.Pedido;
import cl.duoc.speedfast.model.PedidoComida;
import cl.duoc.speedfast.model.PedidoEncomienda;
import cl.duoc.speedfast.model.PedidoExpress;

public class Main {
    public static void main(String[] args) {

        Pedido pedidoComida = new PedidoComida("001", "calle 1234", 50.0, true);
        Pedido pedidoExpress = new PedidoExpress("002", "calle 1111", 67.9);
        Pedido pedidoEncomienda = new PedidoEncomienda("003", "avenida 789", 23.8, 28.3);


        pedidoComida.mostrarResumen();
        pedidoExpress.mostrarResumen();
        pedidoEncomienda.mostrarResumen();
    }
}
