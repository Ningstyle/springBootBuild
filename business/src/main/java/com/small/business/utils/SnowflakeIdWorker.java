package com.small.business.utils;

import java.util.Random;

public class SnowflakeIdWorker {
    private static final long twepoch = 1420041600000L;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = 31L;
    private static final long maxDatacenterId = 31L;
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = 12L;
    private static final long datacenterIdShift = 17L;
    private static final long timestampLeftShift = 22L;
    private static final long sequenceMask = 4095L;
    public static long workerId;
    public static long datacenterId;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;
    private static Random random = new Random();

    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId <= 31L && workerId >= 0L) {
            if (datacenterId <= 31L && datacenterId >= 0L) {
                SnowflakeIdWorker.workerId = workerId;
                SnowflakeIdWorker.datacenterId = datacenterId;
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }

    public static synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        } else {
            if (lastTimestamp == timestamp) {
                sequence = sequence + 1L & 4095L;
                if (sequence == 0L) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = (long)random.nextInt(10);
            }

            lastTimestamp = timestamp;
            return timestamp - 1420041600000L << 22 | datacenterId << 17 | workerId << 12 | sequence;
        }
    }

    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = timeGen(); timestamp <= lastTimestamp; timestamp = timeGen()) {
        }

        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0L, 15L);

        for(int i = 0; i < 1000; ++i) {
            long id = nextId();
            System.out.println(id);
        }

    }
}
