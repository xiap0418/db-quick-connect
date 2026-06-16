package com.xm.db.plugin;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author xiap
 * @since 2025-11-10-16:04
 */
@Slf4j
public class PrioritySpiFactory<T extends PrioritySpi> {
    private final Map<String, T> map = new HashMap<>();

    public PrioritySpiFactory(Class<T> spiClass) {
        for (T t : ServiceLoader.load(spiClass)) {
            if (map.containsKey(t.getIdentify().getName())) {
                resolveConflict(t);
            } else {
                map.put(t.getIdentify().getName(), t);
            }
        }
    }

    public Map<String, T> getSpiMap() {
        return Collections.unmodifiableMap(map);
    }

    private void resolveConflict(T nweSpi) {
        SpiIdentify identify = nweSpi.getIdentify();
        T oldSpi = map.get(identify.getName());

        if (nweSpi.compareTo(oldSpi.getIdentify().getPriority()) == 0) {
            throw new IllegalArgumentException(
                    String.format("These two spi plugins has conflict identify name with the same priority: %s, %s",
                            oldSpi.getIdentify(), nweSpi.getIdentify()));
        } else if (nweSpi.compareTo(oldSpi.getIdentify().getPriority()) > 0) {
            log.info("The {} plugin has high priority, will override {}", nweSpi.getIdentify(), oldSpi);
            map.put(identify.getName(), nweSpi);
        } else {
            log.info("The low plugin {} will be skipped", nweSpi);
        }
    }
}
