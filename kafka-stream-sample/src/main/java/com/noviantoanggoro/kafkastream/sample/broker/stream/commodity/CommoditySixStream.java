package com.noviantoanggoro.kafkastream.sample.broker.stream.commodity;

import com.noviantoanggoro.kafkastream.sample.broker.message.OrderMessage;
import com.noviantoanggoro.kafkastream.sample.broker.message.OrderPatternMessage;
import com.noviantoanggoro.kafkastream.sample.broker.message.OrderRewardMessage;
import com.noviantoanggoro.kafkastream.sample.util.CommodityStreamUtil;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaStreamBrancher;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class CommoditySixStream {

    @Bean
    public KStream<String, OrderMessage> kStreamCommodityTrading(StreamsBuilder builder){
        var stringSerde = Serdes.String();
        var orderSerde = new JsonSerde<>(OrderMessage.class);
        var orderPatternSerde = new JsonSerde<>(OrderPatternMessage.class);
        var orderRewardSerde = new JsonSerde<>(OrderRewardMessage.class);

        var maskedCreditCardStream = builder.stream("t-commodity-order", Consumed.with(stringSerde, orderSerde))
                .mapValues(CommodityStreamUtil::maskedCreditCard);

        final var branchProducer = Produced.with(stringSerde, orderPatternSerde);

        new KafkaStreamBrancher<String, OrderPatternMessage>().branch(CommodityStreamUtil.isPlastic(),
                kStream -> kStream.to("t-commodity-pattern-six-plastic", branchProducer))
                .defaultBranch(kStream -> kStream.to("t-commodity-pattern-six-notplastic", branchProducer))
                .onTopOf(maskedCreditCardStream.mapValues(CommodityStreamUtil::mapToOrderPattern));

//        var plasticIndex = 0;
//        var notPlasticIndex = 1;

        // plastic
//        patternStreams[plasticIndex].to("t-commodity-pattern-two-plastic", Produced.with(stringSerde, orderPatternSerde));

        // not plastic
//        patternStreams[notPlasticIndex].to("t-commodity-pattern-two-notplastic", Produced.with(stringSerde, orderPatternSerde));

        var rewardStream = maskedCreditCardStream.filter(CommodityStreamUtil.isLargeQuantity())
                .filterNot(CommodityStreamUtil.isCheap())
                .map(CommodityStreamUtil.mapToOrderRewardChangedKey());
        rewardStream.to("t-commodity-reward-six", Produced.with(stringSerde, orderRewardSerde));

        // generate base64 key and replace key
        var storageStream = maskedCreditCardStream.selectKey(CommodityStreamUtil.generateStorageKey());
        storageStream.to("t-commodity-storage-six", Produced.with(stringSerde, orderSerde));

        // 4th sink using lambda
        maskedCreditCardStream.filter((k, v) -> v.getOrderLocation().toUpperCase().startsWith("C"))
                .foreach((k, v) -> this.reportFraud(v));

        return maskedCreditCardStream;
    }

    private static final Logger LOG = LoggerFactory.getLogger(CommoditySixStream.class);

    private void reportFraud(OrderMessage v) {
        LOG.info("Reporting fraud : {}", v);
    }
}
