package cn.wzpmc.utils;

import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Yaml工具类
 * @author wzp
 * @since 2024/7/30 下午11:46
 * @version 0.0.1-dev
 */
@Log4j2
public class YamlUtils {
    /**
     * 读取Yaml文件并将其序列化为一个类
     * @param file yaml文件
     * @param clazz 需要序列化的类
     * @return 一个对象
     * @param <T> 序列化的类型(需要空参构造方法)
     */
    public static <T> T readYamlFile(File file, Class<T> clazz){
        log.debug("读取Yaml文件 {}，并写入到类 {}", file, clazz);
        try(FileInputStream fis = new FileInputStream(file)){
            Yaml yaml = new Yaml();
            return yaml.loadAs(fis, clazz);
        } catch (IOException e) {
            log.throwing(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将一个类序列化为Yaml文件
     * @param file yaml文件
     * @param obj 数据类
     * @param <T> 序列化的对象
     */
    public static <T> void writeYamlFile(File file, T obj){
        log.debug("将对象：{} 写入Yaml文件：{}中", obj, file);
        try(FileWriter writer = new FileWriter(file)){
            Yaml yaml = new Yaml();
            yaml.dump(obj, writer);
        } catch (IOException e) {
            log.throwing(e);
            throw new RuntimeException(e);
        }
    }
}
