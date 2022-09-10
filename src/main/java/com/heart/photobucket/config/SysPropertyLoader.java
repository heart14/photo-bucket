package com.heart.photobucket.config;

import com.heart.photobucket.exceptions.SysException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

/**
 * About:
 * Other:
 * Created: wfli on 2022/9/9 15:50.
 * Editored:
 */
public class SysPropertyLoader {

    private static final Logger log = LoggerFactory.getLogger(SysPropertyLoader.class);

    private static SysPropertyLoader instance;

    private Properties properties;

    private static final String PROPERTIES_FILE_NAME = "system.properties";

    private static File persistence;

    private static final String PERSISTENCE_PATH = "cache/persistence/";

    /**
     * 构造方法私有化，防止私自构造对象
     */
    private SysPropertyLoader() {
        log.info("SysPropertyLoader constructor execute...");
        // 自定义构造对象的方法
        init();
    }

    /**
     * 获取SysPropertyLoader实例静态方法
     *
     * @return
     */
    public static SysPropertyLoader getInstance() {
        log.info("SysPropertyLoader getInstance execute...");
        // 懒汉模式构造单例实例
        if (instance == null) {
            instance = new SysPropertyLoader();
        }
        return instance;
    }

    /**
     * 自定义实例初始化方法，加载配置文件到Properties实例
     */
    private void init() {
        log.info("SysPropertyLoader init execute...");
        // 判断是否已加载配置文件
        if (this.properties == null) {
            URL resource = getClassLoader().getResource(PROPERTIES_FILE_NAME);
            if (resource == null) {
                log.error("load system properties fail");
                // 配置文件加载失败直接返回
                return;
            }
            try {
                InputStream inputStream = resource.openStream();
                this.properties = new Properties();
                // 将配置文件加载到Properties实例
                this.properties.load(inputStream);
            } catch (IOException e) {
                log.error("load system properties fail!");
            }
        }
    }

    /**
     * 获取类加载器
     *
     * @return
     */
    private ClassLoader getClassLoader() {
        log.info("SysPropertyLoader getClassLoader execute...");
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 根据配置文件中的key获取value
     *
     * @param pKey
     * @return
     */
    public String getSysProperty(String pKey) {
        if (this.properties == null) {
            return getInnerSysProperty(pKey);
        }
        // 获取value值
        String pValue = this.properties.getProperty(pKey);
        if (pValue == null) {
            return getInnerSysProperty(pKey);
        }
        return pValue;
    }


    /**
     * 获取jvm中的系统属性
     *
     * @param pKey
     * @return java默认的系统变量有下面这些：
     * java.version:java运行时版本
     * java.vendor:java运行时环境供应商
     * java.vendor.url:java供应商url
     * java.home;java安装目录
     * java.vm.specification.version:java虚拟机规范版本
     * java.vm.specification.vendor:java虚拟机规范供应商
     * java.vm.specification.name:java虚拟机规范名称
     * java.vm.version:java虚拟机实现版本
     * java.vm.vendor:java虚拟机实现供应商
     * java.vm.name:java虚拟机实现名称
     * java.specification.version:java运行时环境规范版本
     * java.specification.vendor:java运行时环境规范运营商
     * java.specification.name:java运行时环境规范名称
     * java.class.version:java类格式版本
     * java.class.path:java类路径
     * java.library.path:加载库是搜索的路径列表
     * java.io.tmpdir:默认的临时文件路径
     * java.compiler:要使用的JIT编译器的路径
     * java.ext.dirs:一个或者多个扩展目录的路径
     * os.name:操作系统的名称
     * os.arch:操作系统的架构
     * os.version:操作系统的版本
     * file.separator:文件分隔符（在unix系统中是“/”）
     * path.separator:路径分隔符（在unix系统中是“:”）
     * line.separator:行分隔符（在unix系统中是“/n”）
     * user.name:用户的账户名称
     * user.home:用户的主目录
     * user.dir:用户的当前工作目录
     */
    private String getInnerSysProperty(String pKey) {
        return System.getProperty(pKey);
    }


    /**
     * 将加载完成的Properties实例临时数据持久化存储
     *
     * @throws SysException
     */
    public void storeProperties() throws SysException {
        FileOutputStream os = null;
        if (persistence == null) {
            persistence = new File(PERSISTENCE_PATH);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("system.properties Properties Persistence.");
        try {
            os = new FileOutputStream(persistence);
            this.properties.store(os, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 加载Properties
     *
     * @param forceReload 是否强制重新加载
     * @throws SysException
     */
    public void loadProperties(boolean forceReload) throws SysException {
        // Properties实例Null或者强制重新加载
        if (this.properties == null || forceReload) {
            InputStream is = null;
            try {
                // 持久化文件不存在则从配置文件加载
                if (persistence == null) {
                    URL resource = getClassLoader().getResource(PROPERTIES_FILE_NAME);
                    if (resource == null) {
                        log.error("load system properties fail");
                        // 配置文件加载失败直接返回
                        return;
                    }
                    is = resource.openStream();
                } else {
                    // 持久化文件存在则从持久化文件加载
                    is = new FileInputStream(persistence);
                }
                this.properties = new Properties();
                if (is != null) {
                    // 加载Properties实例
                    this.properties.load(is);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 重新加载Properties
     */
    public void reloadProperties() {
        // 强制重新加载Properties
        loadProperties(Boolean.TRUE);
    }

    /**
     * 获取配置文件keySet
     *
     * @return
     */
    public Set<Object> sysPropertyKeySet() {
        return this.properties.keySet();
    }

    /**
     * 获取jvm中的系统属性keySet
     *
     * @return
     */
    public Set<Object> innerSysPropertyKeySet() {
        return System.getProperties().keySet();
    }
}

