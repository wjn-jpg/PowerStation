package com.ntdq.power_station.remoteControl.wrapper;

import com.ntdq.power_station.remoteControl.ControlCommandCallback;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;

public class PromiseWrapper {

    private Channel channel;

    private ChannelPromise channelPromise;

    private ControlCommandCallback controlCommandCallback;

    public PromiseWrapper(Channel channel, ChannelPromise channelPromise, ControlCommandCallback controlCommandCallback) {
        this.channel = channel;
        this.channelPromise = channelPromise;
        this.controlCommandCallback = controlCommandCallback;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ChannelPromise getChannelPromise() {
        return channelPromise;
    }

    public void setChannelPromise(ChannelPromise channelPromise) {
        this.channelPromise = channelPromise;
    }

    public ControlCommandCallback getControlCommandCallback() {
        return controlCommandCallback;
    }

    public void setControlCommandCallback(ControlCommandCallback controlCommandCallback) {
        this.controlCommandCallback = controlCommandCallback;
    }
}
