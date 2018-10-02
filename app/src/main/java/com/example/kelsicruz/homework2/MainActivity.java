package com.example.kelsicruz.homework2;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.example.kelsicruz.homework2.Face.hairColor;

/*********************************************************
 * Main Activity class
 * Author @Kelsi Cruz
 *********************************************************/

public class MainActivity extends AppCompatActivity {
    public static Face face;
    int buttonState = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = (Face) findViewById(R.id.faceView);


        /*********************************************************
         * Creates red, blue, and green seekbars and assigns
         * listeners.
         *********************************************************/
        //RED seekBar
        SeekBar redSeekBar = findViewById(R.id.redSeekBar);
        redSeekBar.setOnSeekBarChangeListener(new redSeekBarListener());
        //BLUE seekBar
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);
        blueSeekBar.setOnSeekBarChangeListener(new blueSeekBarListener());
        //GREEN seekBar
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);
        greenSeekBar.setOnSeekBarChangeListener(new greenSeekBarListener());


        /*********************************************************
         * Initializes random button and assigns listener.
         *********************************************************/
        Button randomButton = (Button) findViewById(R.id.randomFaceButton);
        randomButton.setOnClickListener(new ButtonListener());


        /*********************************************************
         * Creates spinner and assigns a spin listener.
         *********************************************************/
        SpinnerListener spinListener = new SpinnerListener();
        List<String> hair = new ArrayList<>();
        hair.add("Hairstyle 1");
        hair.add("Hairstyle 2");
        hair.add("Hairstyle 3");
        //gets info from strings.xml resource file
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hair);
        //connect spinner to xml spinner
        Spinner spinnerHairChoice = findViewById(R.id.hairSpinner);
        //sets data to be seen in a drop down view
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHairChoice.setOnItemSelectedListener(spinListener);
        spinnerHairChoice.setAdapter(dataAdapter);


        /*********************************************************
         * Creates radio group and radio buttons
         * and assigns both a listener.
         *********************************************************/
        RadioListener radioListener = new RadioListener();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.hairStyleButtons);
        radioGroup.setOnCheckedChangeListener(radioListener);



    }


    //Spinner listener
    public class SpinnerListener extends Activity implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,long id){
            if(parent.getItemAtPosition(position).toString().equals("Hair 0")){
                face.setHairStyle(0);
                face.invalidate();
            }
            if(parent.getItemAtPosition(position).toString().equals("Hair 1")){
                face.setHairStyle(1);
                face.invalidate();
            }
            if(parent.getItemAtPosition(position).toString().equals("Hair 2")){
                face.setHairStyle(2);
                face.invalidate();
            }
        }

        public void onNothingSelected(AdapterView<?> arg0){
            //
        }
    }


    //Radio group listener
    public class RadioListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId){
            if(checkedId == R.id.eyeButton){
                buttonState = 1;
            }
            if(checkedId == R.id.skinButton){
                buttonState = 2;
            }
            if(checkedId == R.id.hairButton){
                buttonState = 3;
            }
        }
    }


    //Randomizer button listener
    public class ButtonListener implements View.OnClickListener {
        public void onClick(View Button) {
            Face newRandom = (Face) findViewById(R.id.faceView);
            newRandom.setEyeColor(random());
            newRandom.setHairColor(random());
            newRandom.setSkinColor(random());

            Random rand = new Random();
            int style = rand.nextInt(3);
            newRandom.setHairStyle(style);

        }
        public int random()
        {
            Random rand = new Random();
            int red = rand.nextInt(255);
            int green = rand.nextInt(255);
            int blue = rand.nextInt(255);
            int outColor = Color.argb(255, red, green, blue);
            return outColor;
        }
    }

    //Red seekbar listener
    private class redSeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar redSeekBar, int progress, boolean fromUser) {
            Log.i("progress", "" + progress);
            SeekBarState.getInstance().setRedAdjust(progress);
            Log.i("Delta", "" + SeekBarState.getInstance().getRedAdjust());
            TextView redTextBox = findViewById(R.id.redText);
            redTextBox.setText("Red Adjustment:  " + SeekBarState.getInstance().getRedAdjust());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (buttonState == 1) {
                //changes hair color

                int red = Color.red(face.hairColor) + SeekBarState.getInstance().getRedAdjust();
                int green = Color.green(face.hairColor);
                int blue = Color.blue(face.hairColor);
                int outColor = Color.argb(255, red, green, blue);
                face.setHairColor(outColor);
            } else if (buttonState == 2) {
                //changes eye color
                int red = Color.red(face.eyeColor) + SeekBarState.getInstance().getRedAdjust();
                int green = Color.green(face.eyeColor);
                int blue = Color.blue(face.eyeColor);
                int outColor = Color.argb(255, red, green, blue);
                face.setEyeColor(outColor);
            } else if (buttonState == 3) {
                //changes skin color
                int red = Color.red(face.skinColor) + SeekBarState.getInstance().getRedAdjust();
                int green = Color.green(face.skinColor);
                int blue = Color.blue(face.skinColor);
                int outColor = Color.argb(255, red, green, blue);
                face.setSkinColor(outColor);
            }
        }
    }


    //Blue seekbar listener
    private class blueSeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar redSeekBar, int progress, boolean fromUser) {
            Log.i("progress", "" + progress);
            SeekBarState.getInstance().setBlueAdjust(progress);
            Log.i("Delta", "" + SeekBarState.getInstance().getBlueAdjust());
            TextView blueTextBox = findViewById(R.id.blueText);
            blueTextBox.setText("Blue Adjustment:  " + SeekBarState.getInstance().getBlueAdjust());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (buttonState == 1) {
                //changes hair color

                int red = Color.red(face.hairColor);
                int green = Color.green(face.hairColor);
                int blue = Color.blue(face.hairColor) + SeekBarState.getInstance().getBlueAdjust();
                int outColor = Color.argb(255, red, green, blue);
                face.setHairColor(outColor);
            } else if (buttonState == 2) {
                //changes eye color
                int red = Color.red(face.eyeColor);
                int green = Color.green(face.eyeColor);
                int blue = Color.blue(face.eyeColor) + SeekBarState.getInstance().getBlueAdjust();
                int outColor = Color.argb(255, red, green, blue);
                face.setEyeColor(outColor);
            } else if (buttonState == 3) {
                //changes skin color
                int red = Color.red(face.skinColor);
                int green = Color.green(face.skinColor);
                int blue = Color.blue(face.skinColor) + SeekBarState.getInstance().getBlueAdjust();
                int outColor = Color.argb(255, red, green, blue);
                face.setSkinColor(outColor);
            }
        }
    }


    //Green seekbar listener
    private class greenSeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar redSeekBar, int progress, boolean fromUser) {
            Log.i("progress", "" + progress);
            SeekBarState.getInstance().setGreenAdjust(progress);
            Log.i("Delta", "" + SeekBarState.getInstance().getGreenAdjust());
            TextView greenTextBox = findViewById(R.id.greenText);
            greenTextBox.setText("Green Adjustment:  " + SeekBarState.getInstance().getGreenAdjust());

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (buttonState == 1) {
                //changes hair color

                int red = Color.red(face.hairColor);
                int green = Color.green(face.hairColor) + SeekBarState.getInstance().getGreenAdjust();
                int blue = Color.blue(face.hairColor);
                int outColor = Color.argb(255, red, green, blue);
                face.setHairColor(outColor);
            } else if (buttonState == 2) {
                //changes eye color
                int red = Color.red(face.eyeColor);
                int green = Color.green(face.eyeColor) + SeekBarState.getInstance().getGreenAdjust();
                int blue = Color.blue(face.eyeColor);
                int outColor = Color.argb(255, red, green, blue);
                face.setEyeColor(outColor);
            } else if (buttonState == 3) {
                //changes skin color
                int red = Color.red(face.skinColor);
                int green = Color.green(face.skinColor) + SeekBarState.getInstance().getGreenAdjust();
                int blue = Color.blue(face.skinColor);
                int outColor = Color.argb(255, red, green, blue);
                face.setSkinColor(outColor);
            }
        }
    }



}




