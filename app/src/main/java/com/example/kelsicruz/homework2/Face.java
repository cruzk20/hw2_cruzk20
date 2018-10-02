package com.example.kelsicruz.homework2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.content.Context;
import java.util.Random;

/*********************************************************
 * Face Class
 * Author @Kelsi Cruz
 *
 * Creates and draws the face, contains color variables
 * and randomizers.
 *
 *********************************************************/

public class Face extends SurfaceView {

    public static int skinColor, eyeColor, hairColor, hairStyle;
    private int red, blue, green;
    private Random rand = new Random();
    private Face face;


    //Constructor
    public Face(Context context, AttributeSet set){
        super(context, set);
        setWillNotDraw(false);
        skinColor = randomize();
        eyeColor = randomize();
        hairColor = randomize();
        hairStyle = randomizeHair();

    }

    //Color randomizer.
    public int randomize(){
        red = rand.nextInt(256);
        blue = rand.nextInt(256);
        green = rand.nextInt(256);
        int outColor = Color.argb(255,red,green,blue);

        return outColor;


    }


    //Hairstyle randomizer.
    public int randomizeHair(){
        int hairNum = rand.nextInt(3);
        return hairNum;
    }


    //Draws face.
    @Override
    public void onDraw(Canvas canvas){
        drawFaceShape(skinColor, canvas);
        drawEyes(eyeColor, canvas);
        drawHair(hairColor, hairStyle, canvas);

        invalidate();


    }



    //Set methods.
    public static void setEyeColor(int color) {
        eyeColor = color;
    }

    public static void setHairStyle(int style) {
        hairStyle = style;
    }

    public static void setSkinColor(int color) {
        skinColor = color;
    }

    public static void setHairColor(int color) {
        hairColor = color;
    }

    public void drawFaceShape(int skinColor, Canvas canvas)
    {
        //Draws main face
        Paint mainFace = new Paint();
        mainFace.setColor(skinColor);
        canvas.drawOval(590,101,1211,907,mainFace);

        //Draws the mouth.
        Paint smile = new Paint();
        smile.setColor(Color.BLACK);
        canvas.drawArc(765,600,1050,780,0,180,true,smile);
        invalidate();
    }

    public void drawEyes(int eyeColor, Canvas canvas)
    {
        //Draws the left eye.
        Paint leftEye = new Paint();
        leftEye.setColor(eyeColor);
        canvas.drawCircle(775,402,30,leftEye);

        //Draws the right eye.
        Paint rightEye = new Paint();
        rightEye.setColor(eyeColor);
        canvas.drawCircle(1026,402,30,rightEye);

        //Draws a glimmer of light in the left eye.
        Paint leftEyeGlimmer = new Paint();
        leftEyeGlimmer.setColor(Color.WHITE);
        canvas.drawCircle(787,385,10,leftEyeGlimmer);

        //Draws a glimmer of light in the right eye.
        Paint rightEyeGlimmer= new Paint();
        rightEyeGlimmer.setColor(Color.WHITE);
        canvas.drawCircle(1038,385,10,rightEyeGlimmer);

        invalidate();

    }


    public  void drawHair(int hairColor, int hairStyle, Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(hairColor);

        if (hairStyle == 0)
        {
            //Bald hairstyle.
            invalidate();
        }
        if(hairStyle == 1)
        {
            //Short hairstyle.
            canvas.drawArc(615, 80, 1185, 580, 180, 180, true, paint);
            invalidate();
        }
        else if(hairStyle == 2)
        {
            //Bun hairstyle.
            canvas.drawArc(615, 80, 1185, 580, 180, 180, true, paint);
            canvas.drawCircle(650, 155, 90, paint);
            canvas.drawCircle(1150, 155, 90, paint);
            invalidate();

        }
    }



}
