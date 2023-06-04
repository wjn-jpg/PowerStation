package com.ntdq.power_station.nettyHandler.parseCore.parse;

import com.ntdq.power_station.remoteControl.ControlCommandCallback;
import com.ntdq.power_station.remoteControl.wrapper.PromiseWrapper;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.YcAndYxProcess;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;

public interface IParse {

    void parseByteToMessage(byte[] data, YcAndYxProcess callBack);

//    String generateTransId(PromiseWrapper promiseWrapper);

    default boolean isNeedSendKafka(int count, int divided, int totalLength) {
        //return (count * divided) > totalLength;
        return count >= 76;
    }

    default PromiseWrapper setControlPromiseWrapper(Channel channel, ChannelPromise channelPromise, ControlCommandCallback controlCommandCallback) {
        return new PromiseWrapper(channel, channelPromise, controlCommandCallback);
    }


}
