package com.sean.www.particles.objects;

import com.sean.www.particles.util.Geometry;

/**
 * author: machenshuang
 * <p>
 * Date: 2017-11-27 11:09
 * <p>
 * <p>粒子喷泉：产生粒子数据</p>
 */

public class ParticleShooter {
    private final Geometry.Point position;
    private final Geometry.Vector direction;
    private final int color;

    public ParticleShooter(Geometry.Point position,
                           Geometry.Vector direction,
                           int color) {
        this.position = position;
        this.direction = direction;
        this.color = color;
    }

    /**
     * 添加粒子给粒子系统
     *
     * @param particleSystem 粒子系统
     * @param currentTime    当前时间
     * @param count          数量
     */
    public void addParticles(ParticleSystem particleSystem, float currentTime,
                             int count) {
        for (int i = 0; i < count; i++) {
            particleSystem.addParticle(position, color, direction, currentTime);
        }
    }
}
