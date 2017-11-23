package com.sean.www.particles.programs;

import android.content.Context;

/**
 * author: machenshuang
 * <p>
 * Date: 2017-11-23 16:22
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

    }
}
