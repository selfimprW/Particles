package com.sean.www.particles.objects;

import com.sean.www.particles.util.Geometry;

import java.util.Random;

import static android.opengl.Matrix.multiplyMV;
import static android.opengl.Matrix.setRotateEulerM;

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

    private final float angleVariance;
    private final float speedVariance;

    private final Random random = new Random();

    private float[] rotationMatrix = new float[16];
    private float[] directionVector = new float[4];
    private float[] resultVector = new float[4];

    public ParticleShooter(Geometry.Point position,
                           Geometry.Vector direction,
                           int color,
                           float angleVariance,
                           float speedVariance) {
        this.position = position;
        this.direction = direction;
        this.color = color;

        this.angleVariance = angleVariance;
        this.speedVariance = speedVariance;

        directionVector[0] = direction.x;
        directionVector[1] = direction.y;
        directionVector[2] = direction.z;
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

        setRotateEulerM(rotationMatrix, 0,
                (random.nextFloat() - 0.5f) * angleVariance,
                (random.nextFloat() - 0.5f) * angleVariance,
                (random.nextFloat() - 0.5f) * angleVariance);

        multiplyMV(resultVector, 0,
                rotationMatrix, 0,
                directionVector, 0);

        float speedAdjustment = 1f + random.nextFloat() * speedVariance;

        Geometry.Vector thisDirection = new Geometry.Vector(
                resultVector[0] * speedAdjustment,
                resultVector[1] * speedAdjustment,
                resultVector[2] * speedAdjustment
        );

        for (int i = 0; i < count; i++) {
            particleSystem.addParticle(position, color, thisDirection, currentTime);
        }
    }
}
