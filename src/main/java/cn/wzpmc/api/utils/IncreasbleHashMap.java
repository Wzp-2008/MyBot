package cn.wzpmc.api.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 一个单Key对应多Value的HashMap
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/16 00:02
 */
public class IncreasbleHashMap<K, V> extends HashMap<K, List<V>> implements IncreasbleMap<K, V>{
    @Override
    public void add(K key, V value) {
        List<V> newArrayList = super.getOrDefault(key, new ArrayList<>());
        newArrayList.add(value);
        super.put(key, newArrayList);
    }

    @Override
    public boolean delete(K key, V value) {
        if (!super.containsKey(key)) {
            return false;
        }
        List<V> vs = super.get(key);
        return vs.remove(value);
    }

    @Override
    public boolean delete(V value) {
        boolean has = false;
        for (List<V> vs : super.values()) {
            if (vs.contains(value)){
                has = true;
                vs.remove(value);
            }
        }
        return has;
    }

    @Override
    public void addAll(IncreasbleMap<K, V> increasbleMap) {
        for (Entry<K, List<V>> entry : increasbleMap.entrySet()) {
            this.addAll(entry.getKey(), entry.getValue());
        }
    }
    @Override
    public void addAll(K key, Collection<V> values) {
        List<V> list = this.getOrDefault(key, new ArrayList<>());
        list.addAll(values);
        this.put(key, list);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> vs : super.values()) {
            if (vs.contains(value)){
                return true;
            }
        }
        return false;
    }
}
