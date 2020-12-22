package com.example.demo;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;


/**
 * Description： TODO
 * <p>
 * Author: miangong
 * <p>
 * Date: Created in 2020/12/8 11:06
 */
public class RocketMqCusomer {
    public static void main(String[] args) throws Exception {
        //发送同步消息
//        sendMessageTb();
        //发送异步消息
//        sendMessageYb();
        //单向发送消息
//        sendMessageDx();
        //
    }

    private static void sendMessageDx() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setSendMsgTimeout(6000);
        producer.setNamesrvAddr("123.56.248.199:9876");
        producer.start();
        Message message = new Message("Topic", "Tag", "hello".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.sendOneway(message);
        producer.shutdown();
    }

    private static void sendMessageYb() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("123.56.248.199:9876");
        producer.setSendMsgTimeout(6000);
        producer.start();
        Message message = new Message("Topic", "Tag", "hello".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("成功" + sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("失败" + throwable.getMessage());
            }
        });
        producer.shutdown();

    }

    public static void sendMessageTb() throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // 设置NameServer的地址
        producer.setNamesrvAddr("123.56.248.199:9876");
        producer.setSendMsgTimeout(6000);
//             启动Producer实例
        producer.start();
        for (int i = 0; i < 3; i++) {
//             创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */);
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

}
