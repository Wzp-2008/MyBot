package cn.wzpmc.api.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 一个单Key对应多Value的Map
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/15 23:57
 */
public interface IncreasbleMap<K, V> extends Map<K, List<V>> {
    /**
     * 向一个Key中添加元素
     * @author wzp
     * @since 2024/8/16 00:04 v0.0.4-dev
     * @param key 键
     * @param value 值
     */
    void add(K key, V value);

    /**
     * 删除某个key中的对应元素
     * @author wzp
     * @since 2024/8/16 00:05 v0.0.4-dev
     * @param key 键
     * @param value 值
     * @return 是否删除成功
     */
    boolean delete(K key, V value);

    /**
     * 删除所有的对应value的值
     * @author wzp
     * @since 2024/8/16 00:05 v0.0.4-dev
     * @param value 值
     * @return 是否删除成功
     */
    boolean delete(V value);

    /**
     * 将两个表融合
     * @author wzp
     * @since 2024/8/16 00:35 v0.0.4-dev
     * @param increasbleMap 另一个表
     */
    void addAll(IncreasbleMap<K, V> increasbleMap);

    /**
     * 将所有value添加到此key中
     * @author wzp
     * @since 2024/8/16 00:43 v0.0.4-dev
     * @param key 键
     * @param values 所有要添加的值的集合
     */
    void addAll(K key, Collection<V> values);
}
