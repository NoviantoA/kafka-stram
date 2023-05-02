package com.noviantoanggoro.kafkastream.sample.broker.serde;

import com.noviantoanggoro.kafkastream.sample.broker.message.PromotionMessage;

public class PromotionSerde extends CustomJsonSerde<PromotionMessage> {

    public PromotionSerde() {
        super(new CustomJsonSerializer<PromotionMessage>(), new CustomJsonDeserialize<PromotionMessage>(PromotionMessage.class));
    }
}
