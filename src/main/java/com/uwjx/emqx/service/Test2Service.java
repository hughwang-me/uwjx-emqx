package com.uwjx.emqx.service;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/10/26 12:40
 */
@Slf4j
public class Test2Service {

    public static void run(List<String> orders){
        if(orders == null){
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String currentDay = format.format(new Date());
        log.warn("当前日期:{}" , currentDay);
        Iterator<String> iterator = orders.iterator();
        while (iterator.hasNext()){
            String day = iterator.next();
            log.warn("判断的:{}" , day);
            if(!currentDay.equals(day)){
                iterator.remove();
            }
        }
        log.warn("结果:{}" , orders);
    }

    public static void main(String[] args) {
        List<String> orders = new ArrayList<>();
        orders.add("2021-10-01");
        orders.add("2021-10-01");
        orders.add("2021-10-01");
        orders.add("2021-10-01");
        orders.add("2021-10-01");
        orders.add("2021-10-01");
        run(orders);
    }
}
