package com.zzq.paul_tools.bean;

import android.text.TextUtils;


import java.io.Serializable;
import java.util.List;

/**
 * @Description: 首页数据
 * @Author: zzq
 * @Date: 2021-08-13 14:52
 */
public class HomeDataBean extends BaseBean implements Serializable {
        /**
         * souvenir : {"id":29,"operator":176,"status":1,"is_delete":1,"city_code":410100,"red_content":"8.4郑州展示纪念品","red_name":"8.4郑州展示纪念品","red_url":"www.baidu.com","url_type":3,"add_time":1628068076,"red_sku":[{"update_time":1628760050,"mall_type":1,"area_code":410100,"stock_count":9998,"sku_id":231,"delivery_charge":0,"charge_price":0,"name":"T测无分享返金币(测试颜色1029 测试规格2)","create_time":1628062581,"is_show":true,"on_shelf":true,"exchange_point":6,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"优识","sku_type_name":"实物","mall_sku_id":331,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8462,"is_synced":true,"area_name":"郑州市","usys_unique_id":"33783d15abef59e1","usys_price_cost":2,"charge_type":0,"usys_commodity_ids":"77523","id":3753,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/201604251732481450.jpg","usys_score_cost":54},{"update_time":1627378818,"mall_type":1,"area_code":410100,"stock_count":-37,"sku_id":182,"delivery_charge":0,"charge_price":0,"name":"博洋棉花糖经典混色4条毛巾套装","create_time":1627378818,"is_show":true,"on_shelf":true,"exchange_point":1,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"优识","sku_type_name":"实物","mall_sku_id":201,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":9922,"is_synced":false,"area_name":"郑州市","usys_unique_id":"5114489d047595e9","usys_price_cost":0,"charge_type":0,"usys_commodity_ids":"6661","id":3740,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/201906241753292000.jpg","usys_score_cost":1},{"update_time":1628760055,"mall_type":1,"area_code":410100,"stock_count":-21,"sku_id":214,"delivery_charge":0,"charge_price":0,"name":"测试图片编辑1201  XXL","create_time":1611024438,"is_show":true,"on_shelf":true,"exchange_point":1,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"优识","sku_type_name":"实物","mall_sku_id":286,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8102,"is_synced":true,"area_name":"郑州市","usys_unique_id":"7e7c1141ef84a78e","usys_price_cost":0,"charge_type":0,"usys_commodity_ids":"78380","id":1222,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/20201201/cefbc5d2091d489cb01ca525a931c039.png","usys_score_cost":18},{"update_time":1628760057,"mall_type":1,"area_code":410100,"stock_count":0,"sku_id":235,"delivery_charge":0,"charge_price":3000,"name":"资生堂玛馨妮（MA CHERIE）花语蜜润 洗发露（滋养丝滑）450ml（日本进口无硅油洗发水）","create_time":1608689226,"is_show":true,"on_shelf":true,"exchange_point":6,"charge_type_name":"爱心卡","is_marked":false,"supplier_type_name":"京东","sku_type_name":"实物","mall_sku_id":350,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8562,"is_synced":true,"area_name":"郑州市","usys_unique_id":"3f76f5de4cfed9a4","usys_price_cost":55,"charge_type":0,"usys_commodity_ids":"79305","id":1122,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/0c26c72a594d9bb7b62b173a9da50720.jpg","usys_score_cost":1118},{"update_time":1628760057,"mall_type":1,"area_code":410100,"stock_count":0,"sku_id":236,"delivery_charge":0,"charge_price":3500,"name":"欧莱雅（LOREAL）精油润养去屑洗发露700ml（新老包装随机发货）","create_time":1608689228,"is_show":true,"on_shelf":true,"exchange_point":7,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"京东","sku_type_name":"实物","mall_sku_id":353,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8582,"is_synced":true,"area_name":"郑州市","usys_unique_id":"26563a751e3f9327","usys_price_cost":44,"charge_type":0,"usys_commodity_ids":"79306","id":1145,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/5a3bcfdec91bc9cbad1966afc59a4155.jpg","usys_score_cost":884},{"update_time":1607181246,"mall_type":1,"area_code":410100,"stock_count":0,"sku_id":228,"delivery_charge":0,"charge_price":5000,"name":"PINYOSAN品尤尚无线蓝牙小音箱","create_time":1607181246,"is_show":true,"on_shelf":true,"exchange_point":10,"charge_type_name":"爱心卡","is_marked":false,"supplier_type_name":"京东","sku_type_name":"实物","mall_sku_id":323,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":1,"is_synced":true,"area_name":"郑州市","usys_unique_id":"mmmmm","usys_price_cost":15,"charge_type":0,"usys_commodity_ids":"6671","id":877,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/201906251534432976.jpg","usys_score_cost":300}],"banner_list":[{"ranking":1,"banner_url":"www.hao123.com","banner_img":"/public/app1/backstage/20210804/165941/4e73619c267d4283a302d127ef14f932/55.jpg"},{"ranking":2,"banner_url":"www.4399.com","banner_img":"/public/app1/backstage/20210804/165950/01701b7a20d547f4807bd27faf8d838e/222.jpg"}]}
         * banner : [{"id":45,"name":"七夕活动","img":"/static/temporary/计文凯/0017316149128087292.png","ranking":"1","add_time":1614912815,"start_time":1628611200,"end_time":1756569600,"url_type":3,"url":"https://wx.xzytest.cn/activity/bloodMate.html?id_one=1117&id_two=1118","city_code":410100,"channel":3,"status":1,"operator":173}]
         * advance : []
         * activity : []
         * icon : [{"label":"icon_card@2x.png","url":"/love/appletimg/icon_img/410100/icon_card@2x.png"},{"label":"icon_code@2x.png","url":"/love/appletimg/icon_img/410100/icon_code@2x.png"},{"label":"icon_love@2x.png","url":"/love/appletimg/icon_img/410100/icon_love@2x.png"},{"label":"icon_serve@2x.png","url":"/love/appletimg/icon_img/410100/icon_serve@2x.png"},{"label":"img_equity_shouye@2x.png","url":"/love/appletimg/icon_img/410100/img_equity_shouye@2x.png"},{"label":"img_every day.png","url":"/love/appletimg/icon_img/410100/img_every day.png"},{"label":"img_life.png","url":"/love/appletimg/icon_img/410100/img_life.png"}]
         * product : [{"id":11,"operator":176,"status":1,"is_delete":1,"city_code":410100,"name":"测试权益模块（郑州）","add_time":1624938334,"content":"我在郑州的街头走一走，哦~~~"}]
         * popup : []
         */

        private SouvenirBean souvenir;
        private List<BannerBean> banner;
        private List<TrailerBean> advance;
        private List<ActivityBean> activity;
        private List<IconBean> icon;
        private List<ProductBean> product;
        private List<ActivityBean> popup;

        public SouvenirBean getSouvenir() {
            return souvenir;
        }

        public void setSouvenir(SouvenirBean souvenir) {
            this.souvenir = souvenir;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<TrailerBean> getAdvance() {
            return advance;
        }

        public void setAdvance(List<TrailerBean> advance) {
            this.advance = advance;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public List<IconBean> getIcon() {
            return icon;
        }

        public void setIcon(List<IconBean> icon) {
            this.icon = icon;
        }

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public List<ActivityBean> getPopup() {
            return popup;
        }

        public void setPopup(List<ActivityBean> popup) {
            this.popup = popup;
        }

        public static class SouvenirBean {
            /**
             * id : 29
             * operator : 176
             * status : 1
             * is_delete : 1
             * city_code : 410100
             * red_content : 8.4郑州展示纪念品
             * red_name : 8.4郑州展示纪念品
             * red_url : www.baidu.com
             * url_type : 3
             * add_time : 1628068076
             * red_sku : [{"update_time":1628760050,"mall_type":1,"area_code":410100,"stock_count":9998,"sku_id":231,"delivery_charge":0,"charge_price":0,"name":"T测无分享返金币(测试颜色1029 测试规格2)","create_time":1628062581,"is_show":true,"on_shelf":true,"exchange_point":6,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"优识","sku_type_name":"实物","mall_sku_id":331,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8462,"is_synced":true,"area_name":"郑州市","usys_unique_id":"33783d15abef59e1","usys_price_cost":2,"charge_type":0,"usys_commodity_ids":"77523","id":3753,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/201604251732481450.jpg","usys_score_cost":54},{"update_time":1627378818,"mall_type":1,"area_code":410100,"stock_count":-37,"sku_id":182,"delivery_charge":0,"charge_price":0,"name":"博洋棉花糖经典混色4条毛巾套装","create_time":1627378818,"is_show":true,"on_shelf":true,"exchange_point":1,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"优识","sku_type_name":"实物","mall_sku_id":201,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":9922,"is_synced":false,"area_name":"郑州市","usys_unique_id":"5114489d047595e9","usys_price_cost":0,"charge_type":0,"usys_commodity_ids":"6661","id":3740,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/201906241753292000.jpg","usys_score_cost":1},{"update_time":1628760055,"mall_type":1,"area_code":410100,"stock_count":-21,"sku_id":214,"delivery_charge":0,"charge_price":0,"name":"测试图片编辑1201  XXL","create_time":1611024438,"is_show":true,"on_shelf":true,"exchange_point":1,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"优识","sku_type_name":"实物","mall_sku_id":286,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8102,"is_synced":true,"area_name":"郑州市","usys_unique_id":"7e7c1141ef84a78e","usys_price_cost":0,"charge_type":0,"usys_commodity_ids":"78380","id":1222,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/20201201/cefbc5d2091d489cb01ca525a931c039.png","usys_score_cost":18},{"update_time":1628760057,"mall_type":1,"area_code":410100,"stock_count":0,"sku_id":235,"delivery_charge":0,"charge_price":3000,"name":"资生堂玛馨妮（MA CHERIE）花语蜜润 洗发露（滋养丝滑）450ml（日本进口无硅油洗发水）","create_time":1608689226,"is_show":true,"on_shelf":true,"exchange_point":6,"charge_type_name":"爱心卡","is_marked":false,"supplier_type_name":"京东","sku_type_name":"实物","mall_sku_id":350,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8562,"is_synced":true,"area_name":"郑州市","usys_unique_id":"3f76f5de4cfed9a4","usys_price_cost":55,"charge_type":0,"usys_commodity_ids":"79305","id":1122,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/0c26c72a594d9bb7b62b173a9da50720.jpg","usys_score_cost":1118},{"update_time":1628760057,"mall_type":1,"area_code":410100,"stock_count":0,"sku_id":236,"delivery_charge":0,"charge_price":3500,"name":"欧莱雅（LOREAL）精油润养去屑洗发露700ml（新老包装随机发货）","create_time":1608689228,"is_show":true,"on_shelf":true,"exchange_point":7,"charge_type_name":"爱心卡","is_marked":true,"supplier_type_name":"京东","sku_type_name":"实物","mall_sku_id":353,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":8582,"is_synced":true,"area_name":"郑州市","usys_unique_id":"26563a751e3f9327","usys_price_cost":44,"charge_type":0,"usys_commodity_ids":"79306","id":1145,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/5a3bcfdec91bc9cbad1966afc59a4155.jpg","usys_score_cost":884},{"update_time":1607181246,"mall_type":1,"area_code":410100,"stock_count":0,"sku_id":228,"delivery_charge":0,"charge_price":5000,"name":"PINYOSAN品尤尚无线蓝牙小音箱","create_time":1607181246,"is_show":true,"on_shelf":true,"exchange_point":10,"charge_type_name":"爱心卡","is_marked":false,"supplier_type_name":"京东","sku_type_name":"实物","mall_sku_id":323,"sku_type":1,"mall_type_name":"2.0商城","usys_serial_number":1,"is_synced":true,"area_name":"郑州市","usys_unique_id":"mmmmm","usys_price_cost":15,"charge_type":0,"usys_commodity_ids":"6671","id":877,"status":1,"pic_url":"https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/201906251534432976.jpg","usys_score_cost":300}]
             * banner_list : [{"ranking":1,"banner_url":"www.hao123.com","banner_img":"/public/app1/backstage/20210804/165941/4e73619c267d4283a302d127ef14f932/55.jpg"},{"ranking":2,"banner_url":"www.4399.com","banner_img":"/public/app1/backstage/20210804/165950/01701b7a20d547f4807bd27faf8d838e/222.jpg"}]
             */

            private int id;
            private int operator;
            private int status;
            private int is_delete;
            private int city_code;
            private String red_content;
            private String red_name;
            private String red_url;
            private int url_type;
            private int add_time;
            private List<RedSkuBean> red_sku;
            private List<BannerListBean> banner_list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOperator() {
                return operator;
            }

            public void setOperator(int operator) {
                this.operator = operator;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public int getCity_code() {
                return city_code;
            }

            public void setCity_code(int city_code) {
                this.city_code = city_code;
            }

            public String getRed_content() {
                return red_content;
            }

            public void setRed_content(String red_content) {
                this.red_content = red_content;
            }

            public String getRed_name() {
                return red_name;
            }

            public void setRed_name(String red_name) {
                this.red_name = red_name;
            }

            public String getRed_url() {
                return red_url;
            }

            public void setRed_url(String red_url) {
                this.red_url = red_url;
            }

            public int getUrl_type() {
                return url_type;
            }

            public void setUrl_type(int url_type) {
                this.url_type = url_type;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public List<RedSkuBean> getRed_sku() {
                return red_sku;
            }

            public void setRed_sku(List<RedSkuBean> red_sku) {
                this.red_sku = red_sku;
            }

            public List<BannerListBean> getBanner_list() {
                return banner_list;
            }

            public void setBanner_list(List<BannerListBean> banner_list) {
                this.banner_list = banner_list;
            }

            public static class RedSkuBean {
                /**
                 * update_time : 1628760050
                 * mall_type : 1
                 * area_code : 410100
                 * stock_count : 9998
                 * sku_id : 231
                 * delivery_charge : 0
                 * charge_price : 0
                 * name : T测无分享返金币(测试颜色1029 测试规格2)
                 * create_time : 1628062581
                 * is_show : true
                 * on_shelf : true
                 * exchange_point : 6
                 * charge_type_name : 爱心卡
                 * is_marked : true
                 * supplier_type_name : 优识
                 * sku_type_name : 实物
                 * mall_sku_id : 331
                 * sku_type : 1
                 * mall_type_name : 2.0商城
                 * usys_serial_number : 8462
                 * is_synced : true
                 * area_name : 郑州市
                 * usys_unique_id : 33783d15abef59e1
                 * usys_price_cost : 2
                 * charge_type : 0
                 * usys_commodity_ids : 77523
                 * id : 3753
                 * status : 1
                 * pic_url : https://wxtest.youhuiduo.cn/UpLoadFile/gift/images/201604251732481450.jpg
                 * usys_score_cost : 54
                 */

                private int update_time;
                private int mall_type;
                private int area_code;
                private int stock_count;
                private int sku_id;
                private int delivery_charge;
                private int charge_price;
                private String name;
                private int create_time;
                private boolean is_show;
                private boolean on_shelf;
                private int exchange_point;
                private String charge_type_name;
                private boolean is_marked;
                private String supplier_type_name;
                private String sku_type_name;
                private int mall_sku_id;
                private int sku_type;
                private String mall_type_name;
                private int usys_serial_number;
                private boolean is_synced;
                private String area_name;
                private String usys_unique_id;
                private int usys_price_cost;
                private int charge_type;
                private String usys_commodity_ids;
                private int id;
                private int status;
                private String pic_url;
                private int usys_score_cost;

                public int getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(int update_time) {
                    this.update_time = update_time;
                }

                public int getMall_type() {
                    return mall_type;
                }

                public void setMall_type(int mall_type) {
                    this.mall_type = mall_type;
                }

                public int getArea_code() {
                    return area_code;
                }

                public void setArea_code(int area_code) {
                    this.area_code = area_code;
                }

                public int getStock_count() {
                    return stock_count;
                }

                public void setStock_count(int stock_count) {
                    this.stock_count = stock_count;
                }

                public int getSku_id() {
                    return sku_id;
                }

                public void setSku_id(int sku_id) {
                    this.sku_id = sku_id;
                }

                public int getDelivery_charge() {
                    return delivery_charge;
                }

                public void setDelivery_charge(int delivery_charge) {
                    this.delivery_charge = delivery_charge;
                }

                public int getCharge_price() {
                    return charge_price;
                }

                public void setCharge_price(int charge_price) {
                    this.charge_price = charge_price;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public boolean isIs_show() {
                    return is_show;
                }

                public void setIs_show(boolean is_show) {
                    this.is_show = is_show;
                }

                public boolean isOn_shelf() {
                    return on_shelf;
                }

                public void setOn_shelf(boolean on_shelf) {
                    this.on_shelf = on_shelf;
                }

                public int getExchange_point() {
                    return exchange_point;
                }

                public void setExchange_point(int exchange_point) {
                    this.exchange_point = exchange_point;
                }

                public String getCharge_type_name() {
                    return charge_type_name;
                }

                public void setCharge_type_name(String charge_type_name) {
                    this.charge_type_name = charge_type_name;
                }

                public boolean isIs_marked() {
                    return is_marked;
                }

                public void setIs_marked(boolean is_marked) {
                    this.is_marked = is_marked;
                }

                public String getSupplier_type_name() {
                    return supplier_type_name;
                }

                public void setSupplier_type_name(String supplier_type_name) {
                    this.supplier_type_name = supplier_type_name;
                }

                public String getSku_type_name() {
                    return sku_type_name;
                }

                public void setSku_type_name(String sku_type_name) {
                    this.sku_type_name = sku_type_name;
                }

                public int getMall_sku_id() {
                    return mall_sku_id;
                }

                public void setMall_sku_id(int mall_sku_id) {
                    this.mall_sku_id = mall_sku_id;
                }

                public int getSku_type() {
                    return sku_type;
                }

                public void setSku_type(int sku_type) {
                    this.sku_type = sku_type;
                }

                public String getMall_type_name() {
                    return mall_type_name;
                }

                public void setMall_type_name(String mall_type_name) {
                    this.mall_type_name = mall_type_name;
                }

                public int getUsys_serial_number() {
                    return usys_serial_number;
                }

                public void setUsys_serial_number(int usys_serial_number) {
                    this.usys_serial_number = usys_serial_number;
                }

                public boolean isIs_synced() {
                    return is_synced;
                }

                public void setIs_synced(boolean is_synced) {
                    this.is_synced = is_synced;
                }

                public String getArea_name() {
                    return area_name;
                }

                public void setArea_name(String area_name) {
                    this.area_name = area_name;
                }

                public String getUsys_unique_id() {
                    return usys_unique_id;
                }

                public void setUsys_unique_id(String usys_unique_id) {
                    this.usys_unique_id = usys_unique_id;
                }

                public int getUsys_price_cost() {
                    return usys_price_cost;
                }

                public void setUsys_price_cost(int usys_price_cost) {
                    this.usys_price_cost = usys_price_cost;
                }

                public int getCharge_type() {
                    return charge_type;
                }

                public void setCharge_type(int charge_type) {
                    this.charge_type = charge_type;
                }

                public String getUsys_commodity_ids() {
                    return usys_commodity_ids;
                }

                public void setUsys_commodity_ids(String usys_commodity_ids) {
                    this.usys_commodity_ids = usys_commodity_ids;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getPic_url() {
                    return pic_url;
                }

                public void setPic_url(String pic_url) {
                    this.pic_url = pic_url;
                }

                public int getUsys_score_cost() {
                    return usys_score_cost;
                }

                public void setUsys_score_cost(int usys_score_cost) {
                    this.usys_score_cost = usys_score_cost;
                }
            }

            public static class BannerListBean {
                /**
                 * ranking : 1
                 * banner_url : www.hao123.com
                 * banner_img : /public/app1/backstage/20210804/165941/4e73619c267d4283a302d127ef14f932/55.jpg
                 */

                private int ranking;
                private String banner_url;
                private String banner_img;

                public int getRanking() {
                    return ranking;
                }

                public void setRanking(int ranking) {
                    this.ranking = ranking;
                }

                public String getBanner_url() {
                    return banner_url;
                }

                public void setBanner_url(String banner_url) {
                    this.banner_url = banner_url;
                }

                public String getBanner_img() {
                    return banner_img;
                }

                public void setBanner_img(String banner_img) {
                    this.banner_img = banner_img;
                }
            }
        }

        public static class BannerBean {
            /**
             * id : 45
             * name : 七夕活动
             * img : /static/temporary/计文凯/0017316149128087292.png
             * ranking : 1
             * add_time : 1614912815
             * start_time : 1628611200
             * end_time : 1756569600
             * url_type : 3
             * url : https://wx.xzytest.cn/activity/bloodMate.html?id_one=1117&id_two=1118
             * city_code : 410100
             * channel : 3
             * status : 1
             * operator : 173
             */

            private int id;
            private int city_code;
            private String name;
            private String img;
            private String url;
            private String ranking;
            private long add_time;
            private long start_time;
            private long end_time;
            private int url_type;
            private int channel;
            private int status;
            private int operator;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getRanking() {
                return ranking;
            }

            public void setRanking(String ranking) {
                this.ranking = ranking;
            }

            public long getAdd_time() {
                return add_time;
            }

            public void setAdd_time(long add_time) {
                this.add_time = add_time;
            }

            public long getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public long getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getUrl_type() {
                return url_type;
            }

            public void setUrl_type(int url_type) {
                this.url_type = url_type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getCity_code() {
                return city_code;
            }

            public void setCity_code(int city_code) {
                this.city_code = city_code;
            }

            public int getChannel() {
                return channel;
            }

            public void setChannel(int channel) {
                this.channel = channel;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getOperator() {
                return operator;
            }

            public void setOperator(int operator) {
                this.operator = operator;
            }
        }

        public static class IconBean {
            /**
             * label : icon_card@2x.png
             * url : /love/appletimg/icon_img/410100/icon_card@2x.png
             */

            private String label;
            private String url;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }


    public class ActivityBean  {
        private int id;
        private int city_code;
        private String name;
        private String img;
        private String icon_img;
        private String url;
        private String ranking;
        private long add_time;
        private long start_time;
        private long end_time;
        private int url_type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCity_code() {
            return city_code;
        }

        public void setCity_code(int city_code) {
            this.city_code = city_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getUrl_type() {
            return url_type;
        }

        public void setUrl_type(int url_type) {
            this.url_type = url_type;
        }

        public String getIcon_img() {
            return icon_img;
        }

        public void setIcon_img(String icon_img) {
            this.icon_img = icon_img;
        }


    }

    public class TrailerBean implements Serializable {
        private int id;
        private int city_code;
        private String name;
        private String img;
        private String url;
        private String ranking;
        private int add_time;
        private int start_time;
        private int end_time;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCity_code() {
            return city_code;
        }

        public void setCity_code(int city_code) {
            this.city_code = city_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ProductBean {
            /**
             * id : 11
             * operator : 176
             * status : 1
             * is_delete : 1
             * city_code : 410100
             * name : 测试权益模块（郑州）
             * add_time : 1624938334
             * content : 我在郑州的街头走一走，哦~~~
             */

            private int id;
            private int operator;
            private int status;
            private int is_delete;
            private int city_code;
            private String name;
            private long add_time;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOperator() {
                return operator;
            }

            public void setOperator(int operator) {
                this.operator = operator;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public int getCity_code() {
                return city_code;
            }

            public void setCity_code(int city_code) {
                this.city_code = city_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getAdd_time() {
                return add_time;
            }

            public void setAdd_time(long add_time) {
                this.add_time = add_time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
}
