package com.zzq.design_model.facade_model;

import java.util.logging.Logger;

/**
 * @author zhuzaiqing
 * @describe 子系统
 * @time 2020/7/6 17:34
 */
public class Memory {
    public static final Logger LOGGER = Logger.getLogger(String.valueOf(Memory.class));

    public void start() {
        LOGGER.info("Memory is start...");
    }

    public void shutDown() {
        LOGGER.info("Memory is shutDown...");
    }
}
