package cn.wzpmc.message.json.parts.music;

/**
 * 音乐类型
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:25
 */
public enum MusicType {
    /**
     * qq音乐
     *
     * @since 2024/8/2 下午11:25 v0.0.3-dev
     */
    QQ("qq", MusicAppShare.class),
    /**
     * 网易云音乐
     *
     * @since 2024/8/2 下午11:26 v0.0.3-dev
     */
    NETEASE("163", MusicAppShare.class),
    /**
     * 虾米音乐
     *
     * @since 2024/8/2 下午11:27 v0.0.3-dev
     */
    XM("xm", MusicAppShare.class),
    /**
     * 自定义
     *
     * @since 2024/8/2 下午11:30 v0.0.3-dev
     */
    CUSTOM("custom", MusicCustomShare.class);
    public final String name;
    public final Class<? extends Music> clazz;

    MusicType(String name, Class<? extends Music> clazz) {
        this.name = name;
        this.clazz = clazz;
    }
}
