package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.FilePathInformation;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取图片
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:44
 */
public class GetImageAction extends Action<GetImageAction.Params, FilePathInformation> {
    /**
     * 获取图片
     *
     * @param file 收到的图片文件名（消息段的 file 参数），如 6B4DE3DFD1BD271E3297859D41C530F5.jpg
     * @author wzp
     * @since 2024/8/24 23:55 v0.0.6-dev
     */
    public GetImageAction(String file) {
        super.setAction(Actions.GET_IMAGE);
        super.setParams(new Params(file));
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 收到的图片文件名（消息段的 file 参数），如 6B4DE3DFD1BD271E3297859D41C530F5.jpg
         *
         * @since 2024/8/24 23:41 v0.0.6-dev
         */
        private String file;
    }
}
