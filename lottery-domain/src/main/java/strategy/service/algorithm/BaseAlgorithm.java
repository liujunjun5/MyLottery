package strategy.service.algorithm;

/**
 * @author lj
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{
    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    // 是一个无理数 均匀覆盖无周期性
    private final int HASH_INCREMENT = 0x61c88647;
}
