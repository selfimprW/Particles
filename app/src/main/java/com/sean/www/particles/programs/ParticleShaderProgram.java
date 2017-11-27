package com.sean.www.particles.programs;

import android.content.Context;

import com.sean.www.particles.R;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1f;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * author: machenshuang
 * <p>
 * Date: 2017-11-23 16:22
 *
 * <p>粒子着色器程序</p>
 */

public class ParticleShaderProgram extends ShaderProgram {

    //Uniform location
    private final int uMatrixLocation;
    private final int uTimeLocation;

    //Attribute location
    private final int aPositionLocation;
    private final int aColorLocation;
    private final int aDirectionVectorLocation;
    private final int aParticleStartTimeLocation;

    public ParticleShaderProgram(Context context){
        super(context, R.raw.particle_vertex_shader,
                R.raw.particle_fragment_shader);

        //获取uniform位置
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
        uTimeLocation = glGetUniformLocation(program, U_TIME);

        //获取属性位置
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aColorLocation = glGetAttribLocation(program, A_COLOR);
        aDirectionVectorLocation = glGetAttribLocation(program, A_DIRECTION_VECTOR);
        aParticleStartTimeLocation =
                glGetAttribLocation(program, A_PARTICLE_START_TIME);
    }

    /**
     * 设置Uniform单元的数据
     * @param matrix 数组矩阵
     * @param elapsedTime 消逝的时间
     */
    public void setUniforms(float[] matrix,float elapsedTime){

        //上传矩阵数据
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        glUniform1f(uTimeLocation, elapsedTime);
    }

    public int getPositionLocation(){
        return aPositionLocation;
    }

    public int getColorLocation(){
        return aColorLocation;
    }

    public int getDirectionVectorLocation(){
        return aDirectionVectorLocation;
    }

    public int getParticleStartTimeLocation(){
        return aParticleStartTimeLocation;
    }


}
