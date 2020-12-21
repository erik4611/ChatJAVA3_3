package client.Lesson4_HomeWork;

public class Main {

    static Object monitor = new Object();
    static volatile int number = 1;
    static final int countNumbers = 5;

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                for (int i = 0; i < countNumbers; i++) {
                    synchronized (monitor) {
                        while (number != 1) {
                            monitor.wait();
                        }
                        System.out.print(" A ");
                        number = 2;
                        monitor.notifyAll();

                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < countNumbers; i++) {
                    synchronized (monitor) {
                        while (number != 2) {
                            monitor.wait();
                        }
                        System.out.print("B ");
                        number = 3;
                        monitor.notifyAll();

                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < countNumbers; i++) {
                    synchronized (monitor) {
                        while (number != 3) {
                            monitor.wait();
                        }
                        System.out.print("C -");
                        number = 1;
                        monitor.notifyAll();

                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
