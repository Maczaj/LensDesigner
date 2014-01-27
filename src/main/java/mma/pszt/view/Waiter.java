package mma.pszt.view;

/**
 * @author Arkadiusz Szlachetka
 */
public class Waiter {

    private boolean wait;
    private boolean goo = false;
    private static Waiter instance = null;

    protected Waiter() {
    }

    public static Waiter getInstance() {
        if (instance == null) {
            instance = new Waiter();
        }
        return instance;
    }

    public boolean getWait() {
        return wait;
    }

    public void changeWait() {
        wait = !wait;
    }

    public void stop() {
        while (wait)
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

}
