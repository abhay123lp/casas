package threading;

import javax.swing.SwingUtilities;

/**
 * This Class show how to implement a time-intensive job and how interrupt it.
 */
public class StoptableThread extends Thread {
    boolean jobFinished = false;

    public void cancel(){
        this.interrupt();
    }
    
    public void run() {
        doToughWork();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // updateComponents();
            }
        });
    }

    private void doToughWork() {
        try {
            while (!jobFinished) {
                // We must do at least one of the following:
                // 1. Periodically check Thread.interrupted()
                // 2. Periodically sleep or wait
                if (interrupted()) {
                    throw new InterruptedException();
                }
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            // Notify the application that the thread has
            // has been interrupted
        }
        // No matter what happens, disable the
        // stop button when finished
        finally {
            // stopButton.setEnabled(false);
        }

    }

    public static void main(String[] args) {
        Thread workHard = new StoptableThread();
        workHard.start();
    }

}
