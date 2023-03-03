package com.knqiufan.shop.utils.id;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 雪花算法工厂
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/4 0:07
 */
public class SnowFlakeFactory {

    /**
     * 默认数据中心id
     */
    private static final long DEFAULT_DATACENTER_ID = 1;
    /**
     * 默认的机器id
     */
    private static final long DEFAULT_MACHINE_ID = 1;

    /**
     * 默认的雪花算法句柄
     */
    private static final String DEFAULT_SNOW_FLAKE = "snow_flake";
    /**
     * 缓存SnowFlake对象
     */
    private static ConcurrentMap<String, SnowFlake> snowFlakeCache = new ConcurrentHashMap<>(2);

    /**
     * 获取雪花算法生成分布式序列号
     *
     * @param datacenterId 数据中心
     * @param machineId    机器标识
     * @return 雪花算法生成分布式序列号
     */
    private static SnowFlake getSnowFlake(long datacenterId, long machineId) {
        return new SnowFlake(datacenterId, machineId);
    }

    /**
     * 获取默认的雪花算法生成分布式序列号
     *
     * @return 雪花算法生成分布式序列号
     */
    private static SnowFlake getSnowFlake() {
        return new SnowFlake(DEFAULT_DATACENTER_ID, DEFAULT_MACHINE_ID);
    }

    /**
     * 从缓冲中获取雪花算法生成分布式序列号
     *
     * @return 雪花算法生成分布式序列号
     */
    public static SnowFlake getSnowFlakeFromCache() {
        SnowFlake snowFlake = snowFlakeCache.get(DEFAULT_SNOW_FLAKE);
        if (snowFlake == null) {
            snowFlake = new SnowFlake(DEFAULT_DATACENTER_ID, DEFAULT_MACHINE_ID);
            snowFlakeCache.put(DEFAULT_SNOW_FLAKE, snowFlake);
        }
        return snowFlake;
    }

    /**
     * 根据数据中心id和机器id从缓存中获取全局id
     *
     * @param dataCenterId: 取值为1~31
     * @param machineId:    取值为1~31
     */
    public static SnowFlake getSnowFlakeByDataCenterIdAndMachineIdFromCache(Long dataCenterId, Long machineId) {
        if (dataCenterId > SnowFlake.getMaxDataCeneterNum() || dataCenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > SnowFlake.getMaxMachineNum() || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        String key = DEFAULT_SNOW_FLAKE.concat("_").concat(String.valueOf(dataCenterId)).concat("_").concat(String.valueOf(machineId));
        SnowFlake snowFlake = snowFlakeCache.get(key);
        if (snowFlake == null) {
            snowFlake = new SnowFlake(dataCenterId, machineId);
            snowFlakeCache.put(key, snowFlake);
        }
        return snowFlake;
    }


    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            System.out.println(getSnowFlakeFromCache().nextId());
        }
    }
}
