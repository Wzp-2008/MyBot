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
 * @version 0.0.3-dev */
public enum PartType {
    TEXT(StringMessage.class),
    FACE(Face.class),
    IMAGE(Image.class),
    RECORD(Record.class),
    VIDEO(Video.class),
    AT(At.class),
    RPS(cn.wzpmc.api.message.json.parts.RPS.class),
    DICE(Dice.class),
    SHAKE(Shake.class),
    POKE(Poke.class),
    ANONYMOUS(Anonymous.class),
    SHARE(Share.class),
    CONTACT(Contact.class),
    LOCATION(Location.class),
    MUSIC(Music.class),
    REPLY(Reply.class),
    FORWARD(Forward.class),
    NODE(Node.class),
    XML(XMLMessage.class),
    JSON(CustomJSONMessage.class);
    public final Class<? extends JsonMessagePart> clazz;
    PartType(Class<? extends JsonMessagePart> clazz){
        this.clazz = clazz;
    }
}
