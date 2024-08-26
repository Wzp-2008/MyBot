package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.FilePathInformation;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取语音
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:44
 */
public class GetRecordAction extends Action<GetRecordAction.Params, FilePathInformation> {
    /**
     * 获取语音
     *
     * @param file      收到的语音文件名（消息段的 file 参数），如 0B38145AA44505000B38145AA4450500.silk
     * @param outFormat 要转换到的格式，目前支持 mp3、amr、wma、m4a、spx、ogg、wav、flac
     * @author wzp
     * @since 2024/8/24 23:55 v0.0.6-dev
     */
    public GetRecordAction(String file, String outFormat) {
        super.setAction(Actions.GET_RECORD);
        super.setParams(new Params(file, outFormat));
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 收到的语音文件名（消息段的 file 参数），如 0B38145AA44505000B38145AA4450500.silk
         *
         * @since 2024/8/24 23:35 v0.0.6-dev
         */
        private String file;
        /**
         * 要转换到的格式，目前支持 mp3、amr、wma、m4a、spx、ogg、wav、flac
         *
         * @since 2024/8/24 23:35 v0.0.6-dev
         */
        @JSONField(name = "out_format")
        private String outFormat;
    }
}
