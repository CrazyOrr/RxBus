package com.github.crazyorr.rxbus.app;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.finder.Finder;
import com.hwangjr.rxbus.thread.ThreadEnforcer;

/**
 * Instance of {@link Bus}.
 * Simply use {@link #get()} to get the instance of {@link Bus}
 */
public class MyRxBus {

    /**
     * Instance of {@link Bus}
     */
    private static Bus sBus;

    /**
     * Get the instance of {@link Bus}, create it if necessary.
     *
     * @return Return the instance of {@link Bus}.
     */
    public static synchronized Bus get() {
        if (sBus == null) {
            sBus = new Bus.Builder()
                    .threadEnforcer(ThreadEnforcer.ANY)
                    .identifier("MyRxBus")
                    .finder(Finder.ANNOTATED_HIERARCHY)
                    .build();
        }
        return sBus;
    }
}