class Chronometr {
    public int time=0;
    public void countTime (Messenger m, Messenger m1, int period) {
        for (int i=0; i<period; i++){
            synchronized(this) {
                time+=1;
                System.out.println(time);
                if (i==period-1) Messenger.finish=true;
                m.flag=false;
                this.notify();
                m1.flag=false;
                this.notify();
            }
            try {   Thread.sleep(1000);}
            catch (InterruptedException e) {};
        }
    }
}
class Messenger implements Runnable{
    private int time;
    public Chronometr ch;
    public static boolean finish=false;
    public boolean flag=true;
    Messenger(int time, Chronometr ch) {
        this.time=time;
        this.ch=ch;
    }
    public void waitForTime() {
        while(true){
            synchronized (ch) {
                try{
                    while (flag)
                        ch.wait();
                    if (finish) return;
                    if(ch.time%this.time==0)
                    {
                        System.out.println("Thread "+this.time);
                        flag=true;
                    }
                    flag=true;
                }
                catch(InterruptedException e) {}
            }}
    }
    public void run()  {
        waitForTime();
        System.out.println("The end");
    }
}
