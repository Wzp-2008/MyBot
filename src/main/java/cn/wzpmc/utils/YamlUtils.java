package cn.wzpmc.utils;

import cn.wzpmc.configuration.Configuration;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;

/**
 * Yaml工具类
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:46
 */
@Log4j2
public class YamlUtils {
    /**
     * 通过一个流读取yaml文件并将其序列化为一个类
     *
     * @param is    输入流
     * @param clazz 需要序列化的类
     * @param <T>   序列化的类型(需要空参构造方法)
     * @return 一个对象
     * @author wzp
     * @since 2024/9/1 00:40 v1.0.3
     */
    public static <T> T readYamlStream(InputStream is, Class<T> clazz) {
        Yaml yaml = new Yaml();
        JSONObject json = yaml.loadAs(is, JSONObject.class);
        return json.to(clazz);
    }

    /**
     * 读取Yaml文件并将其序列化为一个类
     *
     * @param file  yaml文件
     * @param clazz 需要序列化的类
     * @param <T>   序列化的类型(需要空参构造方法)
     * @return 一个对象
     */
    public static <T> T readYamlFile(File file, Class<T> clazz) {
        log.debug("读取Yaml文件 {}，并写入到类 {}", file, clazz);
        try (FileInputStream fis = new FileInputStream(file)) {
            return readYamlStream(fis, clazz);
        } catch (IOException e) {
            log.throwing(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将一个类序列化为Yaml文件
     *
     * @param file yaml文件
     * @param obj  数据类
     * @param <T>  序列化的对象
     */
    public static <T> void writeYamlFile(File file, T obj) {
        log.debug("将对象：{} 写入Yaml文件：{}中", obj, file);
        try (FileWriter writer = new FileWriter(file)) {
            DumperOptions dumperOptions = new DumperOptions();
            dumperOptions.setIndent(4);
            dumperOptions.setAllowUnicode(true);
            dumperOptions.setPrettyFlow(true);
            dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Representer representer = new Representer(dumperOptions);
            representer.addClassTag(Configuration.class, Tag.MAP);
            Yaml yaml = new Yaml(representer);
            yaml.dump(obj, writer);
        } catch (IOException e) {
            log.throwing(e);
            throw new RuntimeException(e);
        }
    }
}
