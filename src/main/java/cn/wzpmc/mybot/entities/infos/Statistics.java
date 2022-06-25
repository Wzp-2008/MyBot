package cn.wzpmc.mybot.entities.infos;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public class Statistics {
    @JSONField(name="PacketReceived")
    private Long packetReceived;
    @JSONField(name="PacketSent")
    private Long packetSent;
    @JSONField(name="PacketLost")
    private Long packetLost;
    @JSONField(name="MessageReceived")
    private Long messageReceived;
    @JSONField(name="MessageSent")
    private Long messageSent;
    @JSONField(name="DisconnectTimes")
    private Integer disconnectTimes;
    @JSONField(name="LostTimes")
    private Integer lostTimes;
    @JSONField(name="LastMessageTime")
    private Long lastMessageTime;
    public Statistics(
        @JSONField(name="PacketReceived") Long packetReceived,
        @JSONField(name="PacketSent") Long packetSent,
        @JSONField(name="PacketLost") Long packetLost,
        @JSONField(name="MessageReceived") Long messageReceived,
        @JSONField(name="MessageSent") Long messageSent,
        @JSONField(name="DisconnectTimes") Integer disconnectTimes,
        @JSONField(name="LostTimes") Integer lostTimes,
        @JSONField(name="LastMessageTime") Long lastMessageTime){
        this.packetReceived = packetReceived;
        this.packetSent = packetSent;
        this.packetLost = packetLost;
        this.messageReceived = messageReceived;
        this.messageSent = messageSent;
        this.disconnectTimes = disconnectTimes;
        this.lostTimes = lostTimes;
        this.lastMessageTime = lastMessageTime;
    }
}
