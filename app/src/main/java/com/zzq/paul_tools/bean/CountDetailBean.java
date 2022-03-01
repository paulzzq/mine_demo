package com.zzq.paul_tools.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2018/11/1 18:20
 */
public class CountDetailBean implements Serializable {
        private String type;
        private int typeId;
        private int count;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
}
