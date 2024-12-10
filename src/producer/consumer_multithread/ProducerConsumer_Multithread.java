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
            System.out.println("Productor esperando...");
            cola.put(i); //pone el número
            System.out.println(i + "=>Productor: " + n + ", produce: " + i);
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
            System.out.println("Consumidor esperando...");
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
        while(!disponible){
            try{
                wait();
            }catch(InterruptedException ex){}
        }
        disponible=false;
        notifyAll();
        return numero;
    }
    public synchronized void put(int valor) {
        while(disponible){
            try{
                wait();
            }catch(InterruptedException ex){}
        }
        numero=valor;
        disponible=true;
        notifyAll();
    }
}
