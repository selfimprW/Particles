package com.sean.www.particles;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView;

import com.sean.www.particles.objects.ParticleShooter;
import com.sean.www.particles.objects.ParticleSystem;
import com.sean.www.particles.programs.ParticleShaderProgram;
import com.sean.www.particles.util.Geometry;
import com.sean.www.particles.util.MatrixHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
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

    public ParticlesRenderer(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        particleProgram = new ParticleShaderProgram(mContext);
        particleSystem = new ParticleSystem(10000);
        globalStartTime = System.nanoTime();

        final Geometry.Vector particleDirection = new Geometry.Vector(0f, 0.5f, 0f);

        redParticleShoot = new ParticleShooter(
                new Geometry.Point(-1f, 0f, 0f),
                particleDirection,
                Color.rgb(255, 50, 5)
        );

        greenParticleShoot = new ParticleShooter(
                new Geometry.Point(0f, 0f, 0f),
                particleDirection,
                Color.rgb(25, 255, 25)
        );

        blueParticleShoot = new ParticleShooter(
                new Geometry.Point(1f, 0f, 0f),
                particleDirection,
                Color.rgb(5, 50, 255)
        );
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

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

        float currentTime = (System.nanoTime() - globalStartTime)/1000000000f;

        redParticleShoot.addParticles(particleSystem,currentTime,5);
        greenParticleShoot.addParticles(particleSystem,currentTime,5);
        blueParticleShoot.addParticles(particleSystem,currentTime,5);

        particleProgram.useProgram();
    }
}