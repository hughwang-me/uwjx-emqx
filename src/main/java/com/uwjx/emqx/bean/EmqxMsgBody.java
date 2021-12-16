package com.uwjx.emqx.bean;

import lombok.Data;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/16 14:41
 */
@Data
public class EmqxMsgBody {

    private String topic;
    private String content;
}
