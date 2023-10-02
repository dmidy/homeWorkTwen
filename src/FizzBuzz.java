public class FizzBuzz {
    private static int n = 15;
    private static int currentNumber = 1;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread fizzThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n) {
                        break;
                    }
                    if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                        System.out.println("fizz");
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread buzzThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n) {
                        break;
                    }
                    if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                        System.out.println("buzz");
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread fizzBuzzThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n) {
                        break;
                    }
                    if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                        System.out.println("fizzbuzz");
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread numberThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n) {
                        break;
                    }
                    if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                        System.out.println(currentNumber);
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();
    }
}