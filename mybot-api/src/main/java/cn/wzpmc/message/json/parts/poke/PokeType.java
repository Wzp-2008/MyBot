package cn.wzpmc.message.json.parts.poke;

/**
 * 戳一戳类型
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:51
 */
public enum PokeType {
    CHUO_YI_CHUO("戳一戳", 1, -1),
    SHOW_LOVE("比心", 2, -1),
    LIKE("点赞", 2, -1),
    HEART_BROKEN("心碎", 4, -1),
    SIX_SIX_SIX("666", 5, -1),
    FANG_DA_ZHAO("放大招", 6, -1),
    BAO_BEI_QOI("宝贝球", 126, 2011),
    MEI_GUI_HUA("玫瑰花", 126, 2007),
    ZHAO_HUAN_SHU("召唤术", 126, 2006),
    RANG_NI_PI("让你皮", 126, 2009),
    JIE_YIN("结印", 126, 2005),
    SHOU_LEI("手雷", 126, 2004),
    GOU_YIN("勾引", 126, 2003),
    ZHUA_YI_XIA("抓一下", 126, 2001),
    SUI_PIN("碎屏", 126, 2002),
    QIAO_MEN("敲门", 126, 2002);
    /**
     * 戳一戳名称
     *
     * @since 2024/8/3 下午6:18 v0.0.3-dev
     */
    public final String name;
    /**
     * 戳一戳类型
     *
     * @since 2024/8/3 下午6:18 v0.0.3-dev
     */
    public final Integer type;
    /**
     * 戳一戳ID
     *
     * @since 2024/8/3 下午6:18 v0.0.3-dev
     */
    public final Integer id;

    PokeType(String name, Integer type, Integer id) {
        this.name = name;
        this.type = type;
        this.id = id;
    }
}
