package com.sean.www.particles.objects;

import com.sean.www.particles.data.VertexArray;
import com.sean.www.particles.programs.ParticleShaderProgram;
import com.sean.www.particles.util.Geometry;

import static com.sean.www.particles.Constants.BYTES_PER_FLOAT;

/**
 * author: machenshuang
 * <p>
 * Date: 2017-11-23 17:10
 * <p>粒子系统</p>
 */

public class ParticleSystem {
    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int VECTOR_COMPONENT_COUNT = 3;
    private static final int PARTICLE_START_TIME_COMPONENT_COUNT = 1;

    private static final int TOTAL_COMPONENT_COUNT =
            POSITION_COMPONENT_COUNT
            + COLOR_COMPONENT_COUNT
            + VECTOR_COMPONENT_COUNT
            + PARTICLE_START_TIME_COMPONENT_COUNT;

    private static final int STRIDE = TOTAL_COMPONENT_COUNT * BYTES_PER_FLOAT;

    private final float[] particles;
    private final VertexArray vertexArray;
    private final int maxParticleCount;

    private int currentParticleCount;
    private int nextParticle;

    public ParticleSystem(int maxParticleCount){
        particles = new float[maxParticleCount * TOTAL_COMPONENT_COUNT];
        vertexArray = new VertexArray(particles);
        this.maxParticleCount = maxParticleCount;
    }

    /**
     * 添加粒子
     * @param position 三维中的位置
     * @param color 颜色
     * @param direction 向量
     */
    public void addParticle(Geometry.Point position,int color,Geometry.Vector direction){

    }
}
