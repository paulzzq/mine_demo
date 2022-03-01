package com.zzq.design_model.build_model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author zhuzaiqing
 * @describe  实体 对象
 * @time 2020/7/3 17:20
 */
public class Computer {
    private String cpu;
    private String board;
    private String hd;

    public Computer(Computer.CreateBuild build) {
        this.cpu = build.getCpu();
        this.board = build.getBoard();
        this.hd = build.getHd();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;

    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;

    }


    public void Show(){
        System.out.println("电脑组装完成，请验收:"+toString());
    }

    @Override
    public String toString() {
        return "ComputerBean{" +
                "cpu='" + cpu + '\'' +
                ", board='" + board + '\'' +
                ", hd='" + hd + '\'' +
                '}';
    }

    public static class CreateBuild{
        private String cpu;
        private String board;
        private String hd;
        public String getCpu() {
            return cpu;
        }

        public CreateBuild setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public String getBoard() {
            return board;
        }

        public CreateBuild setBoard(String board) {
            this.board = board;
            return this;

        }

        public String getHd() {
            return hd;
        }

        public CreateBuild setHd(String hd) {
            this.hd = hd;
            return this;

        }
        //返回组装成功的电脑
        public  Computer build(){
            return new Computer(this);
        }
    }
}
