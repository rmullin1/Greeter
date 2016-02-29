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

        final EditText nameEditText = (EditText)activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
                //won't work reliably unless we add text here
                nameEditText.setText("Jake");
            }
        });

        getInstrumentation().waitForIdleSync();
        //write "Jake" to the nameEditText control
        //this does not work reliably, add text in runOnMainSync() instead
        //getInstrumentation().sendStringSync("Jake");
        //getInstrumentation().waitForIdleSync();


        //verify "Reverse" button is disabled on entry
        final Button reverseButton = (Button) activity.findViewById(R.id.reverse_button);
        assertFalse(reverseButton.isEnabled());

        //click the "Greet" button
        Button greetButton = (Button) activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);

        //verify greetMessage contents
        TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);

        // PASSED TO HERE

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                reverseButton.requestFocus();
            }
        });

        //verify "Reverse" button is enabled after greetButton click
        getInstrumentation().waitForIdleSync();
        assertTrue(reverseButton.isEnabled());

        //write "ekaJ" to the nameEditText control
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        //click the "Reverse" button
        TouchUtils.clickView(this, reverseButton);

        //verify greetMessage contents
        actualText = greetMessage.getText().toString();
        assertEquals("!ekaJ ,olleH", actualText);

    }//testGreet()

/*
    public void testGreet() throws java.lang.InterruptedException {
        MainActivity activity = getActivity();

        // Type name in text input
        // ----------------------

        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);

        // Send string input value
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
                //won't work reliably unless we add text here
                nameEditText.setText("Jake");
            }
        });

        getInstrumentation().waitForIdleSync();
        //this does not work reliably, add text in runOnMainSync() instead
        //getInstrumentation().sendStringSync("Jake");
        //getInstrumentation().waitForIdleSync();


        // Tap "Greet" button
        // ----------------------
        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);

        // Verify greet message x
        // ----------------------

        final TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();


        assertEquals("Hello, Jake!", actualText);
    }
*/
}
