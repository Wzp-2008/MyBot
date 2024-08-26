package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.message.json.JsonMessagePart;
import cn.wzpmc.api.message.json.parts.contact.Contact;
import cn.wzpmc.api.message.json.parts.music.Music;
import cn.wzpmc.api.message.json.parts.node.Node;
import cn.wzpmc.api.message.json.parts.poke.Poke;

/**
 * 消息段类型
 * @author wzp
 * @since 2024/8/3 下午6:18
 * @version 0.0.3-dev
 */
public enum PartType {
    /**
     * 文本消息
     * @since 2024/8/23 21:37 v0.0.5-dev
     */
    TEXT(StringMessage.class),
    /**
     * 表情消息
     * @since 2024/8/23 21:37 v0.0.5-dev
     */
    FACE(Face.class),
    /**
     * 图片消息
     * @since 2024/8/23 21:37 v0.0.5-dev
     */
    IMAGE(Image.class),
    /**
     * 语音
     * @since 2024/8/23 21:37 v0.0.5-dev
     */
    RECORD(Record.class),
    /**
     * 短视频
     * @since 2024/8/23 21:37 v0.0.5-dev
     */
    VIDEO(Video.class),
    /**
     * "@"某人
     * @since 2024/8/23 21:37 v0.0.5-dev
     */
    AT(At.class),
    /**
     * 拳魔法表情
     * @since 2024/8/23 21:37 v0.0.5-dev
     */
    RPS(cn.wzpmc.api.message.json.parts.RPS.class),
    /**
     * 掷骰子魔法表情
     * @since 2024/8/23 21:38 v0.0.5-dev
     */
    DICE(Dice.class),
    /**
     * 窗口抖动（戳一戳）
     * @since 2024/8/23 21:38 v0.0.5-dev
     */
    SHAKE(Shake.class),
    /**
     * 戳一戳
     * @since 2024/8/23 21:38 v0.0.5-dev
     */
    POKE(Poke.class),
    /**
     * 匿名发消息
     * @since 2024/8/23 21:39 v0.0.5-dev
     */
    ANONYMOUS(Anonymous.class),
    /**
     * 链接分享
     * @since 2024/8/23 21:39 v0.0.5-dev
     */
    SHARE(Share.class),
    /**
     * 推荐好友/群
     * @since 2024/8/23 21:39 v0.0.5-dev
     */
    CONTACT(Contact.class),
    /**
     * 位置
     * @since 2024/8/23 21:40 v0.0.5-dev
     */
    LOCATION(Location.class),
    /**
     * 音乐分享
     * @since 2024/8/23 21:40 v0.0.5-dev
     */
    MUSIC(Music.class),
    /**
     * 回复
     * @since 2024/8/23 21:40 v0.0.5-dev
     */
    REPLY(Reply.class),
    /**
     * 合并转发
     * @since 2024/8/23 21:40 v0.0.5-dev
     */
    FORWARD(Forward.class),
    /**
     * 合并转发节点
     * @since 2024/8/23 21:40 v0.0.5-dev
     */
    NODE(Node.class),
    /**
     * XML消息
     * @since 2024/8/23 21:40 v0.0.5-dev
     */
    XML(XMLMessage.class),
    /**
     * JSON消息
     * @since 2024/8/23 21:40 v0.0.5-dev
     */
    JSON(CustomJSONMessage.class),
    /**
     * Markdown消息
     * @since 2024/8/25 15:20 v1.0.0
     */
    MARKDOWN(MarkdownMessage.class);
    public final Class<? extends JsonMessagePart> clazz;
    PartType(Class<? extends JsonMessagePart> clazz){
        this.clazz = clazz;
    }
}
