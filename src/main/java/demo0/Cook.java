package demo0;

public class Cook extends Thread{
    @Override
    public void run() {
        while(true){
            synchronized (Desk.lock){
                if(Desk.limit==0){
                    break;
                }else{
                    if(Desk.food==0){
                        Desk.food=1;
                        System.out.println(getName()+" 做好第"+ (10-Desk.limit+1)+"饭了");
                        Desk.lock.notifyAll();
                    }else{
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
