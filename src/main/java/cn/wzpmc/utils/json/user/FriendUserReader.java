package cn.wzpmc.utils.json.user;

import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.user.Friend;
import cn.wzpmc.user.IBot;
import cn.wzpmc.user.permission.Permissions;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderCreator;
import com.alibaba.fastjson2.reader.ObjectReaderProvider;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 15:08
 */
public class FriendUserReader implements ObjectReader<Friend> {
    @Override
    public Friend readObject(JSONReader jsonReader, Type type, Object o, long l) {
        ObjectReaderProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectReaderProvider();
        ObjectReaderCreator creator = defaultObjectReaderProvider.getCreator();
        ObjectReader<Friend> objectReader = creator.createObjectReader(Friend.class);
        Friend friend = objectReader.readObject(jsonReader, type, o, l);
        Long id = friend.getId();
        IBot instance = MyBot.getInstance();
        friend.setPermissions(Permissions.MEMBER);
        if (instance.isBotOp(id)) {
            friend.setPermissions(Permissions.ADMIN);
        }
        return friend;
    }
}
