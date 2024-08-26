package cn.wzpmc.utils.json.user;

import cn.wzpmc.api.user.IBot;
import cn.wzpmc.api.user.group.GroupUser;
import cn.wzpmc.api.user.permission.Permissions;
import cn.wzpmc.entities.user.bot.MyBot;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderCreator;
import com.alibaba.fastjson2.reader.ObjectReaderProvider;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 15:08
 */
public class GroupUserReader implements ObjectReader<GroupUser> {
    @Override
    public GroupUser readObject(JSONReader jsonReader, Type type, Object o, long l) {
        ObjectReaderProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectReaderProvider();
        ObjectReaderCreator creator = defaultObjectReaderProvider.getCreator();
        ObjectReader<GroupUser> objectReader = creator.createObjectReader(GroupUser.class);
        GroupUser user = objectReader.readObject(jsonReader, type, o, l);
        Long id = user.getId();
        IBot instance = MyBot.getInstance();
        user.setPermissions(Permissions.valueOf(user.getRole().name()));
        if (Objects.nonNull(user.getPermissions()) && !user.getPermissions().isAdmin() && instance.isBotOp(id)) {
            user.setPermissions(Permissions.ADMIN);
        }
        return user;
    }
}
