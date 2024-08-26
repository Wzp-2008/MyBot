package cn.wzpmc.utils;

import lombok.extern.log4j.Log4j2;

import java.io.*;

/**
 * 模板文件工具类
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/07/30 23:28:25
 */
@Log4j2
public class TemplateFileUtils {
    /**
     * 写入默认配置文件
     *
     * @param loader 读取默认配置所使用的类加载器
     * @param path   默认配置的路径
     * @param saved  保存到的文件
     * @return 是否写入
     */
    public static boolean saveDefaultConfig(ClassLoader loader, String path, File saved) {
        log.debug("创建默认配置文件从ClassLoader {} -> {} ==> {}", loader, path, saved);
        try (InputStream sourceInputStream = loader.getResourceAsStream(path)) {
            if (sourceInputStream == null) {
                throw new RuntimeException(new FileNotFoundException("Didn't find " + path + " from class loader: " + loader.getName()));
            }
            if (saved.exists()) {
                return false;
            }
            if (!saved.createNewFile()) {
                throw new IOException("Cannot create file " + saved.getAbsolutePath());
            }
            try (FileOutputStream targetFileStream = new FileOutputStream(saved)) {
                sourceInputStream.transferTo(targetFileStream);
            }
            return true;
        } catch (IOException e) {
            log.throwing(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建默认文件夹，当其不存在时会被创建
     *
     * @param path 文件夹路径
     * @return 是否创建
     * @author wzp
     * @since 2024/8/4 下午2:36 v0.0.4-dev
     */
    public static boolean createDefaultDirectory(File path) {
        log.debug("创建文件夹：{}", path.getAbsolutePath());
        if (path.isDirectory()) {
            return false;
        }
        if (path.mkdir()) {
            return true;
        }
        throw new RuntimeException(new IOException("Cannot create directory " + path.getAbsolutePath()));
    }
}
