package com.example.kelsicruz.homework2;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import com.example.kelsicruz.homework2.Face;

/*********************************************************
 * Spinner Listener Class
 * Author @Kelsi Cruz
 *
 * Creates the listener for the spinner in MainActivity.
 *
 *********************************************************/


public class SpinnerListener extends Activity implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //int item = parent.getSelectedItemPosition();
        if (parent.getItemAtPosition(position).toString().equals("Hairstyle 1")) {

            Face.setHairStyle(1);
        } else if (parent.getItemAtPosition(position).toString().equals("Hairstyle 2")) {
            Face.setHairStyle(2);
        } else if (parent.getItemAtPosition(position).toString().equals("Hairstyle 3")) {
            Face.setHairStyle(3);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        //nothing happens
    }
}
