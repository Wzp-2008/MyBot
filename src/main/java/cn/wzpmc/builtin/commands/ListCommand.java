package cn.wzpmc.builtin.commands;

import cn.wzpmc.api.ActionResponse;
import cn.wzpmc.api.IMainApi;
import cn.wzpmc.api.actions.message.get.GetFriendListAction;
import cn.wzpmc.api.actions.message.get.GetGroupListAction;
import cn.wzpmc.commands.BrigadierCommand;
import cn.wzpmc.entities.FriendInformation;
import cn.wzpmc.entities.GroupInformation;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.user.CommandSender;
import cn.wzpmc.user.IBot;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import java.util.List;
import java.util.Objects;

/**
 * @author wzp
 * @version 1.0.2
 * @since 2024/8/27 14:59
 */
public class ListCommand implements BrigadierCommand {
    private final IMainApi api;

    public ListCommand() {
        api = IBot.getInstance().getMainApi();
    }

    @Override
    public LiteralArgumentBuilder<CommandSender> getCommandNode() {
        return LiteralArgumentBuilder.<CommandSender>literal("list").
                requires(CommandSender::isFullAdmin).
                executes(e -> {
                    StringBuilder builder = new StringBuilder();
                    buildFriendList(builder);
                    buildGroupList(builder);
                    CommandSender source = e.getSource();
                    source.sendMessage(StringMessage.text(builder.toString()));
                    return 0;
                }).
                then(LiteralArgumentBuilder.<CommandSender>literal("user").
                        executes(e -> {
                            StringBuilder builder = new StringBuilder();
                            buildFriendList(builder);
                            CommandSender source = e.getSource();
                            source.sendMessage(StringMessage.text(builder.toString()));
                            return 0;
                        })
                ).then(LiteralArgumentBuilder.<CommandSender>literal("group").
                        executes(e -> {
                            StringBuilder builder = new StringBuilder();
                            buildGroupList(builder);
                            CommandSender source = e.getSource();
                            source.sendMessage(StringMessage.text(builder.toString()));
                            return 0;
                        })
                );
    }

    private void buildFriendList(StringBuilder builder) {
        ActionResponse<List<FriendInformation>> listActionResponse = api.doApiCallSafe(new GetFriendListAction());
        List<FriendInformation> data = listActionResponse.getData();
        builder.append("好友：").append('\n');
        for (FriendInformation friendInformation : data) {
            String name = friendInformation.getName();
            builder.append(name);
            String nickname = friendInformation.getNickname();
            if (!Objects.equals(nickname, "") && !Objects.equals(nickname, name)) {
                builder.append('[').append(nickname).append(']');
            }
            builder.append('(').append(friendInformation.getId()).append(')').append('\n');
        }
    }

    private void buildGroupList(StringBuilder builder) {
        ActionResponse<List<GroupInformation>> groupListResponse = api.doApiCallSafe(new GetGroupListAction());
        List<GroupInformation> data = groupListResponse.getData();
        builder.append("群聊：").append('\n');
        for (GroupInformation groupInformation : data) {
            builder.append(groupInformation.getGroupName()).append('(').append(groupInformation.getGroupId()).append(')').append('\n');
        }
    }
}
