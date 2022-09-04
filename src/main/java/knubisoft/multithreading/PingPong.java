package knubisoft.multithreading;

public class PingPong {
    public static void main(String[] args) {
    Msg msg = new Msg();
    new Ping(msg);
    new Pong(msg);
    }
}

class Msg {
    boolean valueSet;

    synchronized String pong() {
        while (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("Pong");
        valueSet = false;
        notify();
        return "Pong";
    }

    synchronized void ping() {
        while (valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        valueSet = true;
        System.out.print("Ping ");
        notify();
    }
}

class Ping implements Runnable {
    Msg msg;

    Ping(Msg msg) {
        this.msg = msg;
        new Thread(this, "Ping").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            msg.ping();
        }
    }
}

class Pong implements Runnable{
    Msg msg;

    Pong(Msg msg){
        this.msg = msg;
        new Thread(this, "Pong").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            msg.pong();
        }
    }
}
