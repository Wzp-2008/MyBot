package cn.wzpmc.mybot.api;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.enums.GroupHonorType;
import cn.wzpmc.mybot.pojo.*;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/2
 */
@Slf4j
public class MyBotApi {
    private final String host;
    private final String port;
    private final String protocol;
    private final Bot bot;
    public MyBotApi(Bot bot){
        this.bot = bot;
        URL http = bot.getHttpUrl();
        this.host = http.getHost();
        this.port = String.valueOf(http.getPort());
        this.protocol = http.getProtocol();
    }
    public Bot getBot(){
        return this.bot;
    }
    private String getUrl(String function) {
        char slash = '/';
        if (function.charAt(0) != slash) {
            return protocol + "://" + host + ":" + port + "/" + function;
        }
        return protocol + "://" + host + ":" + port + function;
    }

    private JSONObject doGet(String function) {
        String url = this.getUrl(function);
        HttpRequest httpRequest = HttpRequest.get(url);
        String body;
        try(HttpResponse execute = httpRequest.execute()){
            body = execute.body();
        }
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject.getJSONObject("data");
    }
    private JSONArray doGetWithArray(String function) {
        String url = this.getUrl(function);
        HttpRequest httpRequest = HttpRequest.get(url);
        String body;
        try(HttpResponse execute = httpRequest.execute()){
            body = execute.body();
        }
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject.getJSONArray("data");
    }
    private JSONObject post(String function,JSONObject args){
        String url = this.getUrl(function);
        String s = JSON.toJSONString(args);
        HttpRequest request = HttpRequest.post(url).body(s);
        String body;
        try(HttpResponse response = request.execute()){
            body = response.body();
        }
        return JSON.parseObject(body);
    }
    private JSONArray doPostWithArray(String function, JSONObject args) {
        JSONObject post = post(function, args);
        return post.getJSONArray("data");
    }
    private JSONObject doPost(String function, JSONObject args) {
        JSONObject jsonObject = post(function,args);
        JSONObject data = jsonObject.getJSONObject("data");
        if (data == null) {
            return jsonObject;
        }
        return data;
    }

    /**
     * 发送私聊消息
     *
     * @param userId     对方 QQ 号
     * @param groupId    主动发起临时会话群号(机器人本身必须是管理员/群主)
     * @param message    要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) , 只在 message 字段是字符串时有效
     * @return 消息ID
     */
    public Integer sendPrivateMessage(Long userId, Long groupId, String message, boolean autoEscape) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.
                fluentPut("user_id",userId).
                fluentPut("group_id",groupId).
                fluentPut("message",message).
                fluentPut("auto_escape",autoEscape);
        JSONObject r = doPost("/send_private_msg", jsonObject);
        return r.getInteger("message_id");
    }

    /**
     * 发送私聊消息
     *
     * @param userId  对方 QQ 号
     * @param groupId 主动发起临时会话群号(机器人本身必须是管理员/群主)
     * @param message 要发送的内容
     * @return 消息ID
     */
    public Integer sendPrivateMessage(Long userId, Long groupId, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("user_id", userId);
        jsonObject.fluentPut("group_id", groupId);
        jsonObject.fluentPut("message", message);
        JSONObject r = doPost("/send_private_msg", jsonObject);
        return r.getInteger("message_id");
    }

    /**
     * 获取登录号信息
     *
     * @return 机器人用户
     */
    public GroupUser getGroupBotInfo() {
        JSONObject data = doGet("/get_login_info");
        return new GroupUser(data.getInteger("user_id"), data.getString("nickname"));
    }

    /**
     * 获取频道系统内BOT的资料
     *
     * @return 频道系统内BOT的资料
     */
    public ChannelUser getChannelBotInfo() {
        JSONObject data = doGet("/get_guild_service_profile");
        return new ChannelUser(data.getLong("tiny_id"), data.getString("nickname"));
    }
    /**
     * 发送群消息
     *
     * @param groupId    群号
     * @param message    要发送的内容
     * @return 消息 ID
     */
    public Integer sendGroupMessage(Long groupId, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id", groupId);
        jsonObject.fluentPut("message", message);
        JSONObject r = doPost("/send_group_msg", jsonObject);
        return r.getInteger("message_id");
    }
    /**
     * 发送群消息
     * @param groupId    群号
     * @param message    要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送 ( 即不解析 CQ 码) , 只在 message 字段是字符串时有效
     * @return 消息 ID
     */
    public Integer sendGroupMessage(Long groupId, String message, boolean autoEscape) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id", groupId);
        jsonObject.fluentPut("message", message);
        jsonObject.fluentPut("auto_escape", autoEscape);
        JSONObject r = doPost("/send_group_msg", jsonObject);
        return r.getInteger("message_id");
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息 ID
     */
    public void deleteMessage(Integer messageId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("message_id", messageId);
        doPost("/delete_msg", jsonObject);
    }

    /**
     * 获取消息
     *
     * @param messageId 消息ID
     * @return 消息
     */
    public GroupMessage getMsg(Integer messageId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("message_id", messageId);
        JSONObject r = doPost("/get_msg", jsonObject);
        return new GroupMessage(r,this.bot);
    }

    /**
     * 获取合并转发内容
     * @param messageId 消息ID
     * @return 响应数据
     */
    public JSONObject getForwardMsg(Integer messageId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("message_id", messageId);
        return doPost("/get_forward_msg", jsonObject);
    }

    /**
     * 获取图片信息
     *
     * @param file 图片缓存文件名
     * @return 图片缓存信息
     */
    public ImageInfo getImage(String file) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("file", file);
        JSONObject r = doPost("/get_image", jsonObject);
        return r.toJavaObject(ImageInfo.class);
    }

    /**
     * 群组踢人
     * @param groupId 群号
     * @param userId 要踢的QQ号
     * @param rejectAddRequest 拒绝此人的加群请求
     */
    public void groupKick(Long groupId, Long userId,boolean rejectAddRequest) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id", groupId);
        jsonObject.fluentPut("user_id", userId);
        jsonObject.fluentPut("reject_add_request",rejectAddRequest);
        doPost("/set_group_kick", jsonObject);
    }

    /**
     * 群组单人禁言
     * @param groupId 群号
     * @param userId 要踢的QQ号
     */
    public void groupKick(Long groupId, Long userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id", groupId);
        jsonObject.fluentPut("user_id", userId);
        doPost("/set_group_kick",jsonObject);
    }

    /**
     * 群组单人禁言
     * @param groupId 群号
     * @param userId 要禁言的QQ号
     */
    public void groupBan(Long groupId, Long userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        doPost("/set_group_ban",jsonObject);
    }
    /**
     * 群组单人禁言
     * @param groupId 群号
     * @param userId 要禁言的QQ号
     * @param duration 禁言时长, 单位秒, 0 表示取消禁言
     */
    public void groupBan(Long groupId, Long userId, int duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        jsonObject.fluentPut("duration",duration);
        doPost("/set_group_ban",jsonObject);
    }

    /**
     * 群组匿名用户禁言
     * @param groupId 群号
     * @param anonymous 可选, 要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     * @param flag 可选, 要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     */
    public void groupAnonymousBan(Long groupId, Anonymous anonymous, String flag){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("anonymous",anonymous);
        jsonObject.fluentPut("flag",flag);
        doPost("/set_group_anonymous_ban",jsonObject);
    }

    /**
     * 群组匿名用户禁言
     * @param groupId 群号
     * @param anonymous 可选, 要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     */
    public void groupAnonymousBan(Long groupId, Anonymous anonymous){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("anonymous",anonymous);
        doPost("/set_group_anonymous_ban",jsonObject);
    }

    /**
     * 群组匿名用户禁言
     * @param groupId 群号
     * @param flag 可选, 要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     */
    public void groupAnonymousBan(Long groupId, String flag){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("flag",flag);
        doPost("/set_group_anonymous_ban",jsonObject);
    }

    /**
     * 群组匿名用户禁言
     * @param groupId 群号
     * @param anonymous 可选, 要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     * @param flag 可选, 要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     * @param duration 禁言时长, 单位秒, 无法取消匿名用户禁言
     */
    public void groupAnonymousBan(Long groupId, Anonymous anonymous, String flag, int duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("anonymous",anonymous);
        jsonObject.fluentPut("flag",flag);
        jsonObject.fluentPut("duration",duration);
        doPost("/set_group_anonymous_ban",jsonObject);
    }

    /**
     * 群组匿名用户禁言
     * @param groupId 群号
     * @param anonymous 可选, 要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     * @param duration 禁言时长, 单位秒, 无法取消匿名用户禁言
     */
    public void groupAnonymousBan(Long groupId, Anonymous anonymous, int duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("anonymous",anonymous);
        jsonObject.fluentPut("duration",duration);
        doPost("/set_group_anonymous_ban",jsonObject);
    }

    /**
     * 群组匿名用户禁言
     * @param groupId 群号
     * @param flag 可选, 要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     * @param duration 禁言时长, 单位秒, 无法取消匿名用户禁言
     */
    public void groupAnonymousBan(Long groupId, String flag, int duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("flag",flag);
        jsonObject.fluentPut("duration",duration);
        doPost("/set_group_anonymous_ban",jsonObject);
    }

    /**
     * 群组匿名用户禁言
     * @param groupId 群号
     * @param duration 禁言时长, 单位秒, 无法取消匿名用户禁言
     */
    public void groupAnonymousBan(Long groupId, int duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("duration",duration);
        doPost("/set_group_anonymous_ban",jsonObject);
            }

    /**
     * 群组全员禁言
     * @param groupId 群号
     */
    public void groupWholeBan(Long groupId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        doPost("set_group_whole_ban",jsonObject);
    }

    /**
     * 群组全员禁言
     * @param groupId 群号
     * @param enable 是否禁言
     */
    public void groupWholeBan(Long groupId, boolean enable){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("enable",enable);
        doPost("/set_group_whole_ban",jsonObject);
    }

    /**
     * 群组设置管理员
     * @param groupId 群号
     * @param userId 要设置管理员的 QQ 号
     */
    public void setGroupAdmin(Long groupId, Long userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        doPost("/set_group_admin",jsonObject);
    }

    /**
     * 群组设置管理员
     * @param groupId 群号
     * @param userId 要设置管理员的 QQ 号
     * @param enable true 为设置, false 为取消
     */
    public void setGroupAdmin(Long groupId, Long userId, boolean enable){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        jsonObject.fluentPut("enable",enable);
        doPost("/set_group_admin",jsonObject);
    }

    /**
     * 设置群名片 ( 群备注 )
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     */
    public void setGroupCard(Long groupId, Long userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        doPost("/set_group_card",jsonObject);
    }

    /**
     * 设置群名片 ( 群备注 )
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     * @param card 群名片内容, 不填或空字符串表示删除群名片
     */
    public void setGroupCard(Long groupId, Long userId, String card){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        jsonObject.fluentPut("card",card);
        doPost("/set_group_card",jsonObject);
    }

    /**
     * 设置群名
     * @param groupId 群号
     * @param groupName 新群名
     */
    public void setGroupName(Long groupId, String groupName){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("group_name",groupId);
        doPost("/set_group_name",jsonObject);
    }

    /**
     * 退出群组
     * @param groupId 群号
     */
    public void setGroupLeave(Long groupId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        doPost("/set_group_leave",jsonObject);
    }

    /**
     * 退出群组
     * @param groupId 群号
     * @param isDismiss 是否解散, 如果登录号是群主, 则仅在此项为 true 时能够解散
     */
    public void setGroupLeave(Long groupId, boolean isDismiss){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("is_dismiss",isDismiss);
        doPost("/set_group_leave",jsonObject);
    }

    /**
     * 设置群组专属头衔
     * @param groupId 群号
     * @param userId 要设置的QQ号
     */
    public void setGroupSpecialTitle(Long groupId, Long userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        doPost("/set_group_special_title",jsonObject);
    }

    /**
     * 设置群组专属头衔
     * @param groupId 群号
     * @param userId 要设置的QQ号
     * @param specialTitle 专属头衔, 不填或空字符串表示删除专属头衔
     * @param duration 专属头衔有效期, 单位秒, -1 表示永久, 不过此项似乎没有效果, 可能是只有某些特殊的时间长度有效, 有待测试
     */
    public void setGroupSpecialTitle(Long groupId, Long userId, String specialTitle, int duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        jsonObject.fluentPut("special_title",specialTitle);
        jsonObject.fluentPut("duration",duration);
        doPost("/set_group_special_title",jsonObject);
    }

    /**
     * 设置群组专属头衔
     * @param groupId 群号
     * @param userId 要设置的QQ号
     * @param specialTitle 专属头衔, 不填或空字符串表示删除专属头衔
     */
    public void setGroupSpecialTitle(Long groupId, Long userId, String specialTitle){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        jsonObject.fluentPut("special_title",specialTitle);
        doPost("/set_group_special_title",jsonObject);
    }

    /**
     * 设置群组专属头衔
     * @param groupId 群号
     * @param userId 要设置的QQ号
     * @param duration 专属头衔有效期, 单位秒, -1 表示永久, 不过此项似乎没有效果, 可能是只有某些特殊的时间长度有效, 有待测试
     */
    public void setGroupSpecialTitle(Long groupId, Long userId, int duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        jsonObject.fluentPut("user_id",userId);
        jsonObject.fluentPut("duration",duration);
        doPost("/set_group_special_title",jsonObject);
    }

    /**
     * 处理加好友请求
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     */
    public void setFriendAddRequest(String flag){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("flag",flag);
        doPost("/set_friend_add_request",jsonObject);
    }

    /**
     * 处理加好友请求
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     * @param approve 是否同意请求
     * @param remark 添加后的好友备注（仅在同意时有效）
     */
    public void setFriendAddRequest(String flag, boolean approve, String remark){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("flag",flag);
        jsonObject.fluentPut("approve",approve);
        jsonObject.fluentPut("remark",remark);
        doPost("/set_friend_add_request",jsonObject);
    }

    /**
     * 处理加好友请求
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     * @param approve 是否同意请求
     */
    public void setFriendAddRequest(String flag, boolean approve){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("flag",flag);
        jsonObject.fluentPut("approve",approve);
        doPost("/set_friend_add_request",jsonObject);
    }

    /**
     * 处理加群请求
     * @param flag 加群请求的 flag（需从上报的数据中获得）
     * @param type add 或 invite, 请求类型（需要和上报消息中的 sub_type 字段相符）
     */
    public void setGroupAddRequest(String flag,String type){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("flag",flag);
        jsonObject.fluentPut("type",type);
        doPost("set_group_add_request",jsonObject);
    }

    /**
     * 处理加群请求/邀请
     * @param flag 加群请求的 flag（需从上报的数据中获得）
     * @param type add 或 invite, 请求类型（需要和上报消息中的 sub_type 字段相符）
     * @param approve 是否同意请求／邀请
     * @param reason 拒绝理由（仅在拒绝时有效）
     */
    public void setGroupAddRequest(String flag,String type, boolean approve, String reason){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("flag",flag);
        jsonObject.fluentPut("type",type);
        jsonObject.fluentPut("approve",approve);
        jsonObject.fluentPut("reason",reason);
        doPost("/set_group_add_request",jsonObject);
    }

    /**
     * 处理加群请求／邀请
     * @param flag 加群请求的 flag（需从上报的数据中获得）
     * @param type add 或 invite, 请求类型（需要和上报消息中的 sub_type 字段相符
     * @param approve 是否同意请求／邀请
     */
    public void setGroupAddRequest(String flag, String type, boolean approve){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("flag",flag);
        jsonObject.fluentPut("type",type);
        jsonObject.fluentPut("approve",approve);
        doPost("/set_group_add_request",jsonObject);
    }

    /**
     * 获取企点账号信息
     * 该API只有企点协议可用
     * @return 企点账号信息
     */
    public QiDianAccount qiDianGetAccountInfo(){
        JSONObject jsonObject = doGet("/qidian_get_account_info");
        return QiDianAccount.getQiDianAccount(jsonObject);
    }

    /**
     * 获取陌生人信息
     * @param userId QQ 号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时, 但响应更快）
     * @return 陌生人信息
     */
    public GroupUser getStrangerInfo(Long userId,Boolean noCache){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("user_id",userId);
        jsonObject.fluentPut("no_cache",noCache);
        JSONObject result = doPost("/get_stranger_info",jsonObject);
        return GroupUser.getGroupUser(result);
    }

    /**
     * 获取好友列表
     * @return 所有的好友
     */
    public ArrayList<FriendUser> getFriendList(){
        ArrayList<FriendUser> users = new ArrayList<>();
        JSONArray objects = doGetWithArray("/get_friend_list");
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject1 = objects.getJSONObject(i);
            users.add(FriendUser.getFriendUser(jsonObject1));
        }
        return users;
    }

    /**
     * 删除好友
     * @param friendId 好友 QQ 号
     */
    public void deleteFriend(Long friendId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("friend_id",friendId);
        doPost("/delete_friend",jsonObject);
    }

    /**
     * 获取群信息
     * @param groupId 群号
     * @return 群信息
     */
    public GroupInfo getGroupInfo(Long groupId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        JSONObject jsonObject1 = doPost("/get_group_info", jsonObject);
        return jsonObject1.toJavaObject(GroupInfo.class);
    }

    /**
     * 获取群信息
     * @param groupId 群号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时, 但响应更快）
     * @return 群信息
     */
    public GroupInfo getGroupInfo(Long groupId,Boolean noCache){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId)
                .fluentPut("no_cache",noCache);
        JSONObject jsonObject1 = doPost("/get_group_info", jsonObject);
        return jsonObject1.toJavaObject(GroupInfo.class);
    }

    /**
     * 获取群成员信息
     * @param groupId 群号
     * @param userId QQ 号
     * @return 群成员信息
     */
    public GroupMemberInfo getGroupMemberInfo(Long groupId,Long userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId).fluentPut("user_id",userId);
        JSONObject jsonObject1 = doPost("/get_group_member_info", jsonObject);
        return jsonObject1.toJavaObject(GroupMemberInfo.class);
    }

    /**
     * 获取群成员信息
     * @param groupId 群号
     * @param userId QQ 号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时, 但响应更快）
     * @return 群成员信息
     */
    public GroupMemberInfo getGroupMemberInfo(Long groupId,Long userId,Boolean noCache){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId).fluentPut("user_id",userId).fluentPut("no_cache",noCache);
        JSONObject jsonObject1 = doPost("/get_group_member_info", jsonObject);
        return jsonObject1.toJavaObject(GroupMemberInfo.class);
    }

    /**
     * 获取群成员列表
     * @param groupId 群号
     * @return 群成员列表
     */
    public ArrayList<GroupMemberInfo> getGroupMemberList(Long groupId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        JSONArray objects = doPostWithArray("/get_group_member_list", jsonObject);
        ArrayList<GroupMemberInfo> R = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            R.add(objects.getJSONObject(i).toJavaObject(GroupMemberInfo.class));
        }
        return R;
    }

    /**
     * 获取群荣誉信息
     * @param groupId 群号
     * @param type 要获取的群荣誉类型
     * @return 群荣誉信息
     */
    public JSONObject getGroupHonorInfo(Long groupId, GroupHonorType type){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId).fluentPut("type",type.name());
        return doPost("/get_group_honor_info", jsonObject);
    }

    /**
     * 获取 Cookies
     * @deprecated 该 API 暂未被 go-cqhttp 支持
     * @param domain 需要获取 cookies 的域名
     * @return Cookies
     */
    @Deprecated
    public String getCookies(String domain){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("domain",domain);
        return doPost("/get_cookies",jsonObject).getString("cookies");
    }

    /**
     * 获取 CSRF Token
     * @deprecated 该 API 暂未被 go-cqhttp 支持
     * @return CSRF Token
     */
    @Deprecated
    public Integer getCsrfToken(){
        return doGet("/get_csrf_token").getInteger("token");
    }

    /**
     * 获取 QQ 相关接口凭证
     * @deprecated 该 API 暂未被 go-cqhttp 支持
     * @param domain 需要获取 cookies 的域名
     * @return QQ 相关接口凭证
     */
    @Deprecated
    public Credentials getCredentials(String domain){
        String cookies = getCookies(domain);
        Integer csrfToken = getCsrfToken();
        return new Credentials(cookies,csrfToken);
    }

    /**
     * 获取语音
     * @deprecated 该 API 暂未被 go-cqhttp 支持
     * @param file 收到的语音文件名（消息段的 file 参数）
     * @param outFormant 要转换到的格式, 目前支持 mp3、amr、wma、m4a、spx、ogg、wav、flac
     * @return 语音
     */
    @Deprecated
    public String getRecord(String file,String outFormant){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("file",file).fluentPut("out_format",outFormant);
        return doPost("/get_record", jsonObject).getString("file");
    }

    /**
     * 检查是否可以发送图片
     * @return 是否可以发送图片
     */
    public Boolean canSendImage(){
        return doGet("/can_send_image").getBoolean("yes");
    }

    /**
     * 检查是否可以发送语音
     * @return 是否可以发送语音
     */
    public Boolean canSendRecord(){
        return doGet("/can_send_record").getBoolean("yes");
    }

    /**
     * 获取版本信息
     * @return 版本信息
     */
    public VersionInfo getVersionInfo(){
        return doGet("/get_version_info").toJavaObject(VersionInfo.class);
    }

    /**
     * 重启go-cqhttp
     * @param delay 要延迟的毫秒数, 如果默认情况下无法重启, 可以尝试设置延迟为 2000 左右
     */
    public void setRestart(Integer delay){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("delay",delay);
        doPost("/set_restart",jsonObject);
    }

    /**
     * 重启go-cqhttp
     */
    public void setRestart(){
        doPost("/set_restart",new JSONObject());
    }

    /**
     * 清理缓存
     * @deprecated 该 API 暂未被 go-cqhttp 支持
     */
    @Deprecated
    public void cleanCache(){
        doGet("/clean_cache");
    }

    /**
     * 设置群头像
     * 目前这个API在登录一段时间后因cookie失效而失效, 请考虑后使用
     * @param groupId 群号
     * @param file 图片文件名
     * file 参数支持以下几种格式：
     * 绝对路径,格式使用 <a href="https://datatracker.ietf.org/doc/html/rfc8089">file URI</a>
     * 网络 URL
     * Base64 编码
     * @param cache 表示是否使用已缓存的文件
     * cache参数: 通过网络 URL 发送时有效, 1表示使用缓存, 0关闭关闭缓存, 默认 为1
     */
    public void setGroupPortrait(Long groupId,String file,Integer cache){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId).fluentPut("file",file).fluentPut("cache",cache);
        doPost("/set_group_portrait",jsonObject);
    }

    /**
     * 设置群头像
     * 目前这个API在登录一段时间后因cookie失效而失效, 请考虑后使用
     * @param groupId 群号
     * @param file 图片文件名
     * file 参数支持以下几种格式：
     * 绝对路径,格式使用 <a href="https://datatracker.ietf.org/doc/html/rfc8089">file URI</a>
     * 网络 URL
     * Base64 编码
     */
    public void setGroupPortrait(Long groupId,String file){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId).fluentPut("file",file);
        doPost("/set_group_portrait",jsonObject);
    }

    /**
     * 图片 OCR
     * 目前图片OCR接口仅支持接受的图片
     * @param image 图片ID
     * @return OCR结果
     */
    public OcrImageResult ocrImage(String image){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("image",image);
        JSONObject result = doPost("/ocr_image", jsonObject);
        JSONArray texts = result.getJSONArray("texts");
        String language = result.getString("language");
        ArrayList<TextDetection> textDetections = new ArrayList<>();
        for (int i = 0; i < texts.size(); i++) {
            JSONObject textDetectionObject = texts.getJSONObject(i);
            String text = textDetectionObject.getString("text");
            Integer confidence = textDetectionObject.getInteger("confidence");
            JSONArray coordinatesObject= textDetectionObject.getJSONArray("coordinates");
            Pos pos1 = coordinatesObject.getJSONObject(0).toJavaObject(Pos.class);
            Pos pos2 = coordinatesObject.getJSONObject(1).toJavaObject(Pos.class);
            Vec2 coordinates = new Vec2(pos1, pos2);
            TextDetection textDetection = new TextDetection(text, confidence, coordinates);
            textDetections.add(textDetection);
        }
        return new OcrImageResult(textDetections, language);
    }

    /**
     * 获取群系统消息
     * @return 所有群系统消息
     * 如果列表没有消息, 将返回 null
     */
    public GroupSystemMsg getGroupSystemMsg(){
        JSONObject jsonObject = doGet("/get_group_system_msg");
        JSONArray invitedRequestsObject = jsonObject.getJSONArray("invited_requests");
        JSONArray joinRequestsObject = jsonObject.getJSONArray("join_requests");
        ArrayList<InvitedRequest> invitedRequests = new ArrayList<>();
        ArrayList<JoinRequest> joinRequests = new ArrayList<>();
        for (int i = 0; i < invitedRequestsObject.size(); i++) {
            invitedRequests.add(invitedRequestsObject.getJSONObject(i).toJavaObject(InvitedRequest.class));
        }
        for (int i = 0; i < joinRequestsObject.size(); i++) {
            joinRequests.add(joinRequestsObject.getJSONObject(i).toJavaObject(JoinRequest.class));
        }
        return new GroupSystemMsg(invitedRequests,joinRequests);
    }

    /**
     * 上传群文件
     * @param groupId 群号
     * @param file 本地文件路径
     * @param name 储存名称
     * @param folder 父目录ID
     */
    public void uploadGroupFile(Long groupId,String file,String name,String folder){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId).fluentPut("file",file).fluentPut("name",name).fluentPut("folder",folder);
        doPost("/upload_group_file",jsonObject);
    }

    /**
     * 获取群文件系统信息
     * @param groupId 群号
     * @return 群文件系统信息
     */
    public GroupFileSystemInfo getGroupFileSystemInfo(Long groupId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        return doPost("/get_group_file_system_info", jsonObject).toJavaObject(GroupFileSystemInfo.class);
    }

    /**
     * 获取群根目录文件列表
     * @param groupId 群号
     * @return 文件列表
     */
    public GroupFileList getGroupRootFiles(Long groupId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        JSONObject result = doPost("/get_group_file_system_info", jsonObject);
        JSONArray filesObject = result.getJSONArray("files");
        JSONArray foldersObject = result.getJSONArray("folders");
        ArrayList<GroupFileObject> files = new ArrayList<>();
        ArrayList<GroupFolderObject> folder = new ArrayList<>();
        for (int i = 0; i < filesObject.size(); i++) {
            files.add(filesObject.getJSONObject(i).toJavaObject(GroupFileObject.class));
        }
        for (int i = 0; i < foldersObject.size(); i++) {
            folder.add(foldersObject.getJSONObject(i).toJavaObject(GroupFolderObject.class));
        }
        return new GroupFileList(files,folder);
    }

    /**
     * 获取状态
     * 所有统计信息都将在重启后重置
     * @return 机器人状态
     */
    public BotStatus getStatus(){
        JSONObject result = doGet("/get_status");
        return result.toJavaObject(BotStatus.class);
    }

    /**
     * 获取群 @全体成员 剩余次数
     * @param groupId 群号
     * @return @全体成员 剩余次数
     */
    public GroupAtAllRemain getGroupAtAllRemain(Long groupId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("group_id",groupId);
        JSONObject result = doPost("/get_group_at_all_remain", jsonObject);
        return result.toJavaObject(GroupAtAllRemain.class);
    }
}
