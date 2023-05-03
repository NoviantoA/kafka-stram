package com.noviantoanggoro.kafkastream.sample.util;

import com.noviantoanggoro.kafkastream.sample.broker.message.OrderMessage;
import com.noviantoanggoro.kafkastream.sample.broker.message.OrderPatternMessage;
import com.noviantoanggoro.kafkastream.sample.broker.message.OrderRewardMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;

import java.util.Base64;

public class CommodityStreamUtil {

    public static OrderMessage maskedCreditCard(OrderMessage original){
        var converted = original.copy();
        var maskedCreditCardNumber = original.getCreditCardNumber().replaceFirst("\\d(12)", StringUtils.repeat('*', 12));

        converted.setCreditCardNumber(maskedCreditCardNumber);

        return converted;
    }

    public static OrderPatternMessage mapToOrderPattern(OrderMessage original){
        var result = new OrderPatternMessage();

        result.setItemName(original.getItemName());
        result.setOrderDateTime(original.getOrderDateTime());
        result.setOrderLocation(original.getOrderLocation());
        result.setOrderNumber(original.getOrderNumber());
        result.setTotalItemAmount(original.getPrice() * original.getQuantity());

        return result;
    }

    // map order to reward message
    public static OrderRewardMessage mapToOrderReward(OrderMessage original){
        var result = new OrderRewardMessage();

        result.setItemName(original.getItemName());
        result.setOrderDateTime(original.getOrderDateTime());
        result.setOrderLocation(original.getOrderLocation());
        result.setOrderNumber(original.getOrderNumber());
        result.setPrice(original.getPrice());
        result.setQuantity(original.getQuantity());

        return result;
    }

    // filter "large quantity"
    public static Predicate<String, OrderMessage> isLargeQuantity(){
        return (key, value) -> value.getQuantity() > 200;
    }

    public static Predicate<? super String, ? super OrderPatternMessage> isPlastic() {
        return (key, value) -> StringUtils.startsWithIgnoreCase(value.getItemName(), "plastic");
    }

    public static Predicate<? super String,? super OrderMessage> isCheap() {
        return (key, value) -> value.getPrice() < 100;
    }

    public static KeyValueMapper<String, OrderMessage, String> generateStorageKey() {
        return (key, value) -> Base64.getEncoder().encodeToString(value.getOrderNumber().getBytes());
    }

    // menerima 3 generic
    // 1. original key
    // 2. original value
    // 3. kafka key value
    public static KeyValueMapper<String, OrderMessage, KeyValue<String, OrderRewardMessage>> mapToOrderRewardChangedKey() {
        return (key, value) -> KeyValue.pair(value.getOrderLocation(), mapToOrderReward(value));
    }
}
