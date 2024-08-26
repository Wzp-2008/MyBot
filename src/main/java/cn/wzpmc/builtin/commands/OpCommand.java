package cn.wzpmc.builtin.commands;

import cn.wzpmc.api.commands.BrigadierCommand;
import cn.wzpmc.api.commands.arguments.UserIdArguments;
import cn.wzpmc.api.entities.Ops;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.user.CommandSender;
import cn.wzpmc.api.user.IBot;
import cn.wzpmc.api.user.group.GroupCommandSender;
import cn.wzpmc.entities.user.bot.MyBot;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import java.util.*;

/**
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 15:35
 */
public class OpCommand implements BrigadierCommand {
    private final IBot instance;
    public OpCommand(){
        this.instance = MyBot.getInstance();
    }
    @Override
    public LiteralArgumentBuilder<CommandSender> getCommandNode() {
        return LiteralArgumentBuilder.
                <CommandSender>literal("op").
                then(LiteralArgumentBuilder.<CommandSender>literal("list").executes(e -> {
                    Ops ops = instance.getOps();
                    Map<String, List<Long>> groupAdmins = ops.getGroupAdmins();
                    Set<Long> fullAdmins = ops.getAdmins();
                    CommandSender source = e.getSource();
                    Long id = source.getId();
                    boolean groupAdmin = false;
                    boolean fullAdmin = fullAdmins.contains(id);
                    Long sendGroupId = -1L;
                    List<Long> sendGroupAdmins = new ArrayList<>();
                    if (source instanceof GroupCommandSender) {
                        sendGroupId = ((GroupCommandSender) source).getGroupId();
                        List<Long> orDefault = groupAdmins.getOrDefault(sendGroupId.toString(), new ArrayList<>());
                        sendGroupAdmins.addAll(orDefault);
                        groupAdmin = orDefault.contains(id);
                    }
                    if (source instanceof IBot) {
                        groupAdmin = true;
                        fullAdmin = true;
                    }
                    StringBuilder builder = new StringBuilder();
                    if (fullAdmin){
                        builder.append(getFullOpListString(fullAdmins)).append('\n');
                        for (Map.Entry<String, List<Long>> stringListEntry : groupAdmins.entrySet()) {
                            builder.append(getGroupOpListString(stringListEntry.getValue(), Long.valueOf(stringListEntry.getKey()))).append('\n');
                        }
                        builder.deleteCharAt(builder.lastIndexOf("\n"));

                    }else if (groupAdmin) {
                        builder.append(getGroupOpListString(sendGroupAdmins, sendGroupId));
                    }else {
                        builder.append("权限不足！");
                    }
                    source.sendMessage(StringMessage.text(builder.toString()));
                    return 0;
                }).then(LiteralArgumentBuilder.<CommandSender>literal("group").
                        then(RequiredArgumentBuilder.<CommandSender, Long>argument("groupId", LongArgumentType.longArg()).
                                executes(e -> {
                                    Long groupId = e.getArgument("groupId", Long.class);
                                    Ops ops = instance.getOps();
                                    Map<String, List<Long>> groupsAdmins = ops.getGroupAdmins();
                                    Set<Long> admins = ops.getAdmins();
                                    List<Long> groupAdmins = groupsAdmins.getOrDefault(groupId.toString(), new ArrayList<>());
                                    CommandSender source = e.getSource();
                                    Long id = source.getId();
                                    String result = "权限不足";
                                    if (source instanceof IBot || admins.contains(id) || groupAdmins.contains(id)) {
                                        result = getGroupOpListString(groupAdmins, groupId);
                                    }
                                    source.sendMessage(StringMessage.text(result));
                                    return 0;
                                })).
                        executes(e -> {
                            CommandSender commandSender = e.getSource();
                            if (!(commandSender instanceof GroupCommandSender)) {
                                commandSender.sendMessage(StringMessage.text("仅支持群内使用此指令！"));
                                return 0;
                            }
                            GroupCommandSender source = (GroupCommandSender) commandSender;
                            Long groupId = source.getGroupId();
                            Ops ops = instance.getOps();
                            Map<String, List<Long>> groupsAdmins = ops.getGroupAdmins();
                            Set<Long> admins = ops.getAdmins();
                            List<Long> groupAdmins = groupsAdmins.getOrDefault(groupId.toString(), new ArrayList<>());
                            Long id = source.getId();
                            String result = "权限不足";
                            if (admins.contains(id) || groupAdmins.contains(id)) {
                                result = getGroupOpListString(groupAdmins, groupId);
                            }
                            source.sendMessage(StringMessage.text(result));
                            return 0;
                        })
                ).then(LiteralArgumentBuilder.<CommandSender>literal("full").executes(e -> {
                    Ops ops = instance.getOps();
                    Set<Long> admins = ops.getAdmins();
                    CommandSender source = e.getSource();
                    Long id = source.getId();
                    String result = "权限不足";
                    if (source instanceof IBot || admins.contains(id)) {
                        result = getFullOpListString(admins);
                    }
                    source.sendMessage(StringMessage.text(result));
                    return 0;
                }))).
                requires(CommandSender::isAdmin).
                then(RequiredArgumentBuilder.<CommandSender, Long>argument("user", new UserIdArguments()).
                        executes(e -> {
                            Ops ops = instance.getOps();
                            CommandSender source = e.getSource();
                            Long targetId = e.getArgument("user", Long.class);
                            if (source instanceof GroupCommandSender){
                                if (ops.isAdmin(source.getId())){
                                    instance.addOp(targetId);
                                    source.sendMessage(StringMessage.text("已为用户：" + targetId + "添加总OP权限"));
                                }else{
                                    Long groupId = ((GroupCommandSender) source).getGroupId();
                                    instance.addOp(groupId, targetId);
                                    source.sendMessage(StringMessage.text("已为用户：" + targetId + "添加群：" + groupId + "的OP权限"));
                                }
                                return 0;
                            }
                            instance.addOp(targetId);
                            source.sendMessage(StringMessage.text("已为用户：" + targetId + "添加总OP权限"));
                            return 0;
                        }).
                        then(LiteralArgumentBuilder.<CommandSender>literal("group")
                                .then(RequiredArgumentBuilder.<CommandSender, Long>argument("group", LongArgumentType.longArg()).
                                        executes(e -> {
                                            Long targetGroupId = e.getArgument("group", Long.class);
                                            Long targetId = e.getArgument("user", Long.class);
                                            CommandSender source = e.getSource();
                                            if (source instanceof GroupCommandSender){
                                                if (!instance.isBotOp(targetGroupId, source.getId())) {
                                                    source.sendMessage(StringMessage.text("权限不足！"));
                                                    return 0;
                                                }
                                            }
                                            instance.addOp(targetGroupId, targetId);
                                            source.sendMessage(StringMessage.text("已为用户：" + targetId + "添加群：" + targetGroupId + "的OP权限"));
                                            return 0;
                                        })
                                ).requires((e) -> e instanceof GroupCommandSender).executes(e -> {
                                    GroupCommandSender source = (GroupCommandSender) e.getSource();
                                    Long groupId = source.getGroupId();
                                    Long targetId = e.getArgument("user", Long.class);
                                    instance.addOp(groupId, targetId);
                                    source.sendMessage(StringMessage.text("已为用户：" + targetId + "添加群：" + groupId + "的OP权限"));
                                    return 0;
                                })
                        )
                );
    }
    private static String getFullOpListString(Collection<Long> admins){
        return "总管理：" + '\n' +
                getOpListString(admins);
    }
    private static String getGroupOpListString(Collection<Long> admins, Long groupId){
        return "群" + groupId + "的管理员：" + '\n' +
                getOpListString(admins);
    }
    private static StringBuilder getOpListString(Collection<Long> admins) {
        StringBuilder builder = new StringBuilder();
        if (admins.isEmpty()){
            builder.append("无").append('\n');
        }
        for (Long admin : admins) {
            builder.append('\t').append(admin).append('\n');
        }
        int lastLine = builder.lastIndexOf("\n");
        builder.deleteCharAt(lastLine);
        return builder;
    }
}
