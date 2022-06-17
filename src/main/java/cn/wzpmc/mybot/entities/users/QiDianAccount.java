package cn.wzpmc.mybot.entities.users;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 */
@ToString
@Getter
@Setter
public class QiDianAccount extends User {
    private Long createTime;

    public QiDianAccount(long masterId, String extName, Long createTime) {
        super(masterId, extName);
        this.createTime = createTime;
    }

    public static QiDianAccount getQiDianAccount(JSONObject jsonObject) {
        Long masterId = jsonObject.getLong("master_id");
        Long createTime = jsonObject.getLong("create_time");
        String extName = jsonObject.getString("ext_name");
        return new QiDianAccount(masterId, extName, createTime);
    }
}
