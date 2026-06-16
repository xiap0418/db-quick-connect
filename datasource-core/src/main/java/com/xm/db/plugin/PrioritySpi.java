package com.xm.db.plugin;

/**
 * @author xiap
 * @since 2025-11-10-15:59
 */
public interface PrioritySpi extends Comparable<Integer> {
    /**
     * The SPI identify, if the two plugin has the same name, will load the high priority.
     * If the priority and name is all same, will throw <code>IllegalArgumentException</code>
     *
     * @return spi identify
     */
    SpiIdentify getIdentify();

    /**
     * Compare to the priority
     *
     * @param o priority
     * @return compare result
     */
    @Override
    default int compareTo(Integer o) {
        return Integer.compare(getIdentify().getPriority(), o);
    }
}
