🚚 Speed Fast App
---
Actividad formativa 3 (Semana 4)

## 📖 Descripción

**SpeedFast** es un simulador de gestión de rutas y despachos de pedidos en tiempo real desarrollado en **Java** y
**Maven**. El sistema aplica conceptos avanzados de Programación Orientada a Objetos (POO), patrones de concurrencia
multihilo seguros en memoria, programación defensiva y optimización arquitectónica.

---

## 🚀 Características Principales y Decisiones de Diseño

### 1. Concurrencia e Hilos Seguros (Thread-Safety)

* **Piscina de Hilos Dinámica:** El ciclo de vida de los despachos se orquesta mediante un `ExecutorService` con un pool
  de hilos fijo que se adapta automáticamente al tamaño de la flota de reparto (
  `Executors.newFixedThreadPool(listaRepartidores.size())`).
* **Gestión Eficiente de Recursos:** Se implementa la estructura moderna de **`try-with-resources`** para garantizar el
  apagado automático (`.shutdown()`) y la liberación de canales en la memoria RAM de forma implícita.
* **Mochilas Inmutables:** Los pedidos asignados a cada repartidor se congelan al nacer mediante **`List.copyOf()`** (
  *Defensive Copying*), blindando al sistema contra alteraciones concurrentes externas.

### 2. Arquitectura de Negocio Orientada a Objetos (POO)

* **Estandarización por Enums Avanzados:** Se sustituyeron los strings libres por el Enum estricto `TipoPedido` para
  amarrar los nombres fijos y niveles de urgencia (`nivelPrioridad`), aislando las reglas del negocio de errores de
  digitación.
* **Polimorfismo Dinámico:** La clase abstracta `Pedido` delega de forma polimórfica las lógicas particulares a las
  subclases especializadas: `PedidoComida`, `PedidoExpress` y `PedidoEncomienda`.

### 3. Gestión Unificada de Prioridades y Criterio FIFO

* **Ordenamiento Estricto por Contrato:** La clase `Pedido` implementa el tipado fuerte de la interfaz *
  *`Comparable<Pedido>`**. El método `compareTo` prioriza de forma nativa la urgencia de la entrega (**Comida >
  Express > Encomienda**).
* **Desempate de Seguridad:** En caso de que dos paquetes pertenezcan a la misma categoría, el sistema aplica un
  criterio **FIFO (First In, First Out)** automático evaluando el `idPedido` cronológico.
* **Auto-corrección de Rutas:** El constructor de la clase `Repartidor` utiliza `ArrayList.sort(null)` para ordenar
  automáticamente la carga antes de iniciar la marcha, descargando al método `run()` de lógicas pesadas de procesamiento
  de datos.
* **Libro Contable Concurrente:** La clase `Repartidor` implementa el contrato `Rastreable`. Utiliza una estructura
  unificada en la memoria compartida mediante un atributo estático respaldado por un **`CopyOnWriteArrayList<Pedido>`**,
  permitiendo que todos los hilos registren sus estados de entrega simultáneamente sin generar condiciones de carrera (
  *Race Conditions*).

---

## 📁 Estructura del Proyecto

```text
speed-fast-semana4/                  
└── src/
    └── main/
        └── java/
            └── cl/
                └── duoc/
                    └── speedfast/
                        ├── Main.java           <-- Director de orquesta general de la simulación
                        │
                        ├── interfaces/         <-- Contratos de comportamiento unificados
                        │   ├── Cancelable.java
                        │   ├── Despachable.java
                        │   └── Rastreable.java
                        │
                        ├── model/              <-- Entidades base, subclases y catálogos estáticos
                        │   ├── Pedido.java
                        │   ├── PedidoComida.java
                        │   ├── PedidoEncomienda.java
                        │   ├── PedidoExpress.java
                        │   └── TipoPedido.java
                        │
                        └── service/            <-- Motores de ejecución activa y simulación de hilos
                            └── Repartidor.java
```

---

## 🛠️ Instrucciones para clonar y ejecutar

Requisitos del sistema:

* **JDK:** Java 25 (LTS) o superior

1. Clonar el repositorio desde la terminal de la computadora o IDE:  
   git clone https://github.com/alonsobonansco/speed-fast-semana4.git
2. Ir a File →️ Open y seleccionar la carpeta raíz del proyecto (la carpeta que contiene el archivo pom.xml).
3. Ejecutar el `Main` desde su clase en el paquete raíz `cl.duoc.speedfast`

---

## 👤 Autor

Alonso Bonansco Vergara  
Desarrollo Orientado a Objetos II - 004A  
Analista Programador Computacional
