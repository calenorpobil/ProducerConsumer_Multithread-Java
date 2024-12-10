package producer.consumer_multithread;

public class ProducerConsumer_Multithread {
    public static void main(String[] args) {
        Cola cola = new Cola();
        Productor p = new Productor(cola, 1);
        Consumidor c = new Consumidor(cola, 1);
        p.start();
        c.start();
    }
}

class Productor extends Thread {

    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); //pone el número
            System.out.println(i + "=>Productor: " + n + ", produce: " + i);
            System.out.println(cola);
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Consumidor extends Thread {
    private Cola cola;
    private int n;
    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }
    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); //recoge el número 
            System.out.println(i + "=>Consumidor: " + n + ", consume: " + valor);
        }
    }
}

class Cola {
    private int numero;
    private boolean disponible
            = false; //inicialmente cola vacía
    public synchronized int get() {
        if (disponible) { //Hay número en la cola
            disponible = false; // se pone cola vacía return numero; 
            return numero;//se devuelve
        }
        return -1; //no hay número disponible, cola vacía
    }
    public synchronized void put(int valor) {
        numero = valor; //coloca valor en la cola
        disponible = true; //disponible para consumir, cola llena
    }
}
