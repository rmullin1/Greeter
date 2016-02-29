package com.ebookfrenzy.waynemullinsgreeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testGreet() {
        MainActivity activity = getActivity();

        final EditText nameEditText =
                (EditText)activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        //write "Jake" to the nameEditText control
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        //click the "Greet" button
        Button greetButton = (Button) activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);

        //verify greetMessage contents
        TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);

        //click the "Reverse" button

    }//testGreet()


}
