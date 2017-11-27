package com.sean.www.particles;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.sean.www.particles.objects.ParticleShooter;
import com.sean.www.particles.objects.ParticleSystem;
import com.sean.www.particles.programs.ParticleShaderProgram;
import com.sean.www.particles.util.Geometry;
import com.sean.www.particles.util.MatrixHelper;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_ONE;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;

/**
 * author: machenshuang
 * <p>
 * Date: 2017-11-27 11:25
 * <p>
 * <p>粒子系统渲染器</p>
 */

public class ParticlesRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "ParticlesRenderer";

    private final Context mContext;

    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private final float[] viewProjectionMatrix = new float[16];

    private ParticleShaderProgram particleProgram;
    private ParticleSystem particleSystem;
    private ParticleShooter redParticleShoot;
    private ParticleShooter greenParticleShoot;
    private ParticleShooter blueParticleShoot;
    private long globalStartTime;

    final float angleVarianceInDegrees = 5f;
    final float speedVariance = 1f;

    public ParticlesRenderer(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        Log.d(TAG,"onSurfaceCreated");

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        particleProgram = new ParticleShaderProgram(mContext);
        particleSystem = new ParticleSystem(1000000);
        globalStartTime = System.nanoTime();

        //使用混合技术
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE,GL_ONE);

        final Geometry.Vector particleDirection = new Geometry.Vector(0f, 0.5f, 0f);

        redParticleShoot = new ParticleShooter(
                new Geometry.Point(-1f, 0f, 0f),
                particleDirection,
                Color.rgb(255, 50, 5),
                angleVarianceInDegrees,
                speedVariance
        );

        greenParticleShoot = new ParticleShooter(
                new Geometry.Point(0f, 0f, 0f),
                particleDirection,
                Color.rgb(25, 255, 25),
                angleVarianceInDegrees,
                speedVariance
        );

        blueParticleShoot = new ParticleShooter(
                new Geometry.Point(1f, 0f, 0f),
                particleDirection,
                Color.rgb(5, 50, 255),
                angleVarianceInDegrees,
                speedVariance
        );
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        Log.d(TAG,"onSurfaceChanged");

        glViewport(0, 0, width, height);

        MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width /
                (float) height, 1f, 10f);

        setIdentityM(viewMatrix, 0);
        translateM(viewMatrix, 0, 0f, -1.5f, -5f);
        multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);

        Log.d(TAG,"onDrawFrame");

        float currentTime = (System.nanoTime() - globalStartTime)/1000000000f;

        redParticleShoot.addParticles(particleSystem,currentTime,5);
        greenParticleShoot.addParticles(particleSystem,currentTime,5);
        blueParticleShoot.addParticles(particleSystem,currentTime,5);

        particleProgram.useProgram();
        particleProgram.setUniforms(viewProjectionMatrix,currentTime);
        particleSystem.bindData(particleProgram);
        particleSystem.draw();
    }
}
