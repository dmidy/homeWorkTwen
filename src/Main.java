public class Main {
    public static void main(String[] args) {

        Chronometr my=new Chronometr();
        Runnable t1=new Messenger(5, my);
        Runnable t2=new Messenger(1, my);
        new Thread(t1, "t1").start();
        new Thread(t2, "t2").start();
        my.countTime((Messenger)t1, (Messenger)t2, 10);

    }
}