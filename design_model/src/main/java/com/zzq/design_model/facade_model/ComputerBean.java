package com.zzq.design_model.facade_model;

import java.util.logging.Logger;

/**
 * @author zhuzaiqing
 * @describe  门面类  提供一个给客户点调用子系统的接口
 * @time 2020/7/6 17:35
 */
public class ComputerBean {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(ComputerBean.class));

    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public ComputerBean() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void start() {
        LOGGER.info("ComputerBean start begin");
        cpu.start();
        disk.start();
        memory.start();
        LOGGER.info("ComputerBean start end");
    }

    public void shutDown() {
        LOGGER.info("ComputerBean shutDown begin");
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
        LOGGER.info("ComputerBean shutDown end...");
    }
}
