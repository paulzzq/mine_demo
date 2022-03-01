package com.zzq.design_model.facade_model;

import java.util.logging.Logger;

/**
 * @author zhuzaiqing
 * @describe  子系统
 * @time 2020/7/6 17:31
 */
public class CPU {
    public static final Logger LOGGER = Logger.getLogger(String.valueOf(CPU.class));

    public void start() {
        LOGGER.info("cpu is start...");
    }

    public void shutDown() {
        LOGGER.info("CPU is shutDown...");
    }
}
