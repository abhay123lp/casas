package threading;

import javax.swing.SwingUtilities;

public class ThreadSafeThing {
    
    public void doThreadSafeWork() {
        if (SwingUtilities.isEventDispatchThread()) {
            //
            // do all work here...
            //
        } else {
            Runnable bridgeTask = new Runnable() {
                public void run() {
                    doThreadSafeWork();
                }
            };
            SwingUtilities.invokeLater(bridgeTask);
        }
    }
}
