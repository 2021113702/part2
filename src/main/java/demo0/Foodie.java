package demo0;

public class Foodie extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.limit == 0) {
                    break;
                } else {
                    if (Desk.food == 1) {
                        Desk.food--;
                        Desk.limit--;
                        System.out.println(getName() + "吃饭了" + ",还能再吃" + Desk.limit);
                        Desk.lock.notifyAll();
                    } else {
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
