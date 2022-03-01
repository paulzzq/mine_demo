package com.zzq.design_model.facade_model;

import java.util.logging.Logger;

/**
 * @author zhuzaiqing
 * @describe 子系统
 * @time 2020/7/6 17:33
 */
public class Disk {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(Disk.class));

    public void start() {
        LOGGER.info("Disk is start...");
    }

    public void shutDown() {
        LOGGER.info("Disk is shutDown...");
    }
}
