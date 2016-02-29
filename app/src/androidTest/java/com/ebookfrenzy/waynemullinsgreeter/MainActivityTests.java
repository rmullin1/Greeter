package com.ebookfrenzy.waynemullinsgreeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }//constructor

    private MainActivity testSetup() {
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
        return activity;
    }//testSetup()

    //TEST 1, verify that the MainActivity exists
    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull("Test Activity Exists", activity);
    }//testActivityExists()


    //TEST 2, verify that the reverseButton is initially disabled
    public void testReverseButtonIsDisabledWhenNoMessageText() throws Exception {
        MainActivity activity = testSetup();
        //verify "Reverse" button is disabled on entry
        Button reverseButton = (Button) activity.findViewById(R.id.reverse_button);
        assertFalse(reverseButton.isEnabled());
    }//testReverseButtonIsDisabledWhenNoMessageText()

    //TEST 3, verify that the Hello message is properly displayed after Greet button clicked
    public void testHelloMessageIsDisplayedAfterGreetButtonClicked() {
        MainActivity activity = testSetup();
        //click the "Greet" button
        Button greetButton = (Button) activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);

        //verify greetMessage contents
        TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }//testHelloMessageIsDisplayedAfterGreetButtonClicked

    //TEST 4, verify that the Reverse button is enabled after the Greet button is clicked
    public void testReverseButtonIsEnabledAfterGreetButtonClicked() {
        MainActivity activity = testSetup();
        //click the "Greet" button
        Button greetButton = (Button) activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);
        getInstrumentation().waitForIdleSync();

        Button reverseButton = (Button) activity.findViewById(R.id.reverse_button);
        assertTrue(reverseButton.isEnabled());
    }//testReverseButtonIsEnabledAfterGreetButtonClicked()

    //TEST 5, verify that the text is reversed after the Reverse button is clicked
    public void testHelloMessageIsReversedAfterReverseButtonClicked() {
        MainActivity activity = testSetup();
        //click the "Greet" button
        Button greetButton = (Button) activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);
        //click the "Reverse" button
        Button reverseButton = (Button) activity.findViewById(R.id.reverse_button);
        TouchUtils.clickView(this, reverseButton);

        //verify greetMessage contents
        TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals(new StringBuilder("Hello, Jake!").reverse().toString(), actualText);

    }//testHelloMessageIsReversedAfterReverseButtonClicked()

    /*
    //SINGLE TEST CASE VERSION FOLLOWING EXAMPLE TEST
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

        //verify "Reverse" button is enabled after greetButton click
        assertTrue(reverseButton.isEnabled());

        //click the "Reverse" button
        TouchUtils.clickView(this, reverseButton);

        //verify greetMessage contents
        actualText = greetMessage.getText().toString();
        assertEquals("!ekaJ ,olleH", actualText);

    }//testGreet()
*/

}
