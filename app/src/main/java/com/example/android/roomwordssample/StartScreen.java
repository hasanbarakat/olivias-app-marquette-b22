package com.example.android.roomwordssample;

import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class StartScreen extends AppCompatActivity {

    public static final int WORD_1_REQUEST_CODE = 2;
    public static final int WORD_2_REQUEST_CODE = 3;
    public static final int DELETE_WORD_CODE = 4;

    ImageButton option1Button;
    ImageButton option2Button;
    FloatingActionButton Option1Label;
    FloatingActionButton Option2Label;

    Bitmap imageBitmap;
    Bitmap imageBitmap1;
    Bitmap imageBitmap2;

    //Bluetooth Stuff
    private BluetoothAdapter mBTAdapter;
    private Handler mHandler; // Our main handler that will receive callback notifications
    private StartScreen.ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path
    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    private final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status
    //Change Device Address
    //final String address = "A8:2B:B9:D7:AE:2B"; //Hasan's Phone
    final String address = "00:18:E4:36:2C:BB"; //Olivia's Base

    final String name = "Tablet";
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        imageBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.icon1);
        imageBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.icon2);

        //mBTArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio

        //Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_out_right);
       // Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);

        //Labels
        //final Button Option1Label = findViewById(R.id.option1_label);
        //final Button Option2Label = findViewById(R.id.option2_label);
        Option1Label = findViewById(R.id.option1_label);
        final FloatingActionButton Option2Label = findViewById(R.id.option2_label);
        //Option 1 Label
        Option1Label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StartScreen.this, Categories.class);
                intent1.setAction("CHOOSE");
                startActivityForResult(intent1, WORD_1_REQUEST_CODE);
            }
        }); //Option1Label Listener

        //Option 2 Label
        Option2Label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StartScreen.this, Categories.class);
                intent2.setAction("CHOOSE");
                startActivityForResult(intent2, WORD_2_REQUEST_CODE);
            }
        }); //Option1Label Listener

        //Option 1
        option1Button = (ImageButton) findViewById(R.id.option1_button);
        option1Button.setBackground(null);
        //option1Button.setAnimation(animFadeOut);
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option2Button.setVisibility(View.INVISIBLE);
                //Option2Label.setVisibility(View.INVISIBLE);

            }
        }); //option1Button Listener

        //Option 2
        option2Button = findViewById(R.id.option2_button);
        option2Button.setBackground(null);
        //option2Button.setAnimation(animFadeOut);
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option1Button.setVisibility(View.INVISIBLE);
                //Option1Label.setVisibility(View.INVISIBLE);
            }
        }); //option2Button Listener



        mHandler = new Handler(){
            public void handleMessage(android.os.Message msg){
                if(msg.what == MESSAGE_READ){
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                        if(readMessage.contains("A")){
                            option2Button.setVisibility(View.INVISIBLE);
                            //Option2Label.setVisibility(View.INVISIBLE);
                            Toast.makeText(getBaseContext(), "Left Selected", Toast.LENGTH_SHORT).show();
                        }
                        if(readMessage.contains("B")){
                            option1Button.setVisibility(View.INVISIBLE);
                            //Option1Label.setVisibility(View.INVISIBLE);
                            Toast.makeText(getBaseContext(), "Right Selected", Toast.LENGTH_SHORT).show();
                        }
                        if(readMessage.contains("C")){
                            option1Button.setVisibility(View.VISIBLE);
                            //option1Button.setImageResource(R.drawable.icon1);
                            option1Button.setImageBitmap(imageBitmap1);

                            option2Button.setVisibility(View.VISIBLE);
                            //option2Button.setImageResource(R.drawable.icon2);
                            option2Button.setImageBitmap(imageBitmap2);
                            Toast.makeText(getBaseContext(), "Reset By Base", Toast.LENGTH_SHORT).show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //mReadBuffer.setText(readMessage);
                }

                if(msg.what == CONNECTING_STATUS){
                    if(msg.arg1 == 1)
                        //mBluetoothStatus.setText("Connected to Device: " + (String)(msg.obj));
                        Toast.makeText(getBaseContext(), "Connected to Base", Toast.LENGTH_SHORT).show();
                    else {
                        //mBluetoothStatus.setText("Connection Failed");
                        Toast.makeText(getBaseContext(), "Failed to Connect to Base", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WORD_1_REQUEST_CODE && resultCode == RESULT_OK) {

            //Code to Get Picture, convert, and place goes here.
            String imageDIR = data.getStringExtra(Categories.PIC_REPLY);//Get Image DIR From Intent
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            imageBitmap1 = BitmapFactory.decodeFile(imageDIR,bitmapOptions);


            //Set Image
           option1Button.setImageBitmap(imageBitmap1);
        }
        else if(requestCode == WORD_2_REQUEST_CODE && resultCode == RESULT_OK) {
            //Code to Get Picture, convert, and place goes here.
            String imageDIR = data.getStringExtra(Categories.PIC_REPLY);//Get Image DIR From Intent
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            imageBitmap2 = BitmapFactory.decodeFile(imageDIR,bitmapOptions);


            //Set Image
            option2Button.setImageBitmap(imageBitmap2);
        }
        else if(requestCode == DELETE_WORD_CODE && resultCode == RESULT_OK) {
            //Delete Image
            String imageDelete = data.getStringExtra(Categories.DELETE_REPLY);//Get Image name From Intent
            //mWordViewModel.delete(imageDelete);
        }
        else {
            /*Toast.makeText(
                    getApplicationContext(),
                    "Canceled",
                    Toast.LENGTH_LONG).show();*/
        }
    } //onActivityResult

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.start_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_button:
                // User chooses clear button
                //this.recreate(); //Restart current activity
                option1Button.setVisibility(View.VISIBLE);
                //option1Button.setImageResource(R.drawable.icon1);
                option1Button.setImageBitmap(imageBitmap1);
                //Option1Label.setVisibility(View.VISIBLE);

                option2Button.setVisibility(View.VISIBLE);
                //option2Button.setImageResource(R.drawable.icon2);
                option2Button.setImageBitmap(imageBitmap2);
                //Option2Label.setVisibility(View.INVISIBLE);

                Toast.makeText(getBaseContext(), "Reset", Toast.LENGTH_SHORT).show();

                return true;
            /*case R.id.settings:
                Intent settingsIntent = new Intent(StartScreen.this, Settings.class);
                startActivity(settingsIntent);
                return true;*/
            case R.id.connect_to_base:
                if(mBTSocket != null)
                {
                    try {
                        mBTSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
               /* if(mBTSocket != null && mBTSocket.isConnected())
                {
                    Toast.makeText(getBaseContext(), "Already Connected :)", Toast.LENGTH_SHORT).show();
                }*/
                //else {
                    bluetoothConnectBase();
                //}
                return true;
            case R.id.delete_option:
                Intent deleteIntent = new Intent(StartScreen.this, Categories.class);
                deleteIntent.setAction("DELETE");
                startActivityForResult(deleteIntent, DELETE_WORD_CODE);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public boolean bluetoothConnectBase(){
        if(!mBTAdapter.isEnabled()) {
            Toast.makeText(getBaseContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(getBaseContext(), "Connecting...", Toast.LENGTH_SHORT).show();

        // Spawn a new thread to avoid blocking the GUI one
        new Thread()
        {
            public void run() {
                boolean fail = false;

                BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                try {
                    mBTSocket = createBluetoothSocket(device);
                } catch (IOException e) {
                    fail = true;
                    Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                }
                // Establish the Bluetooth socket connection.
                try {
                    mBTSocket.connect();
                } catch (IOException e) {
                    try {
                        fail = true;
                        mBTSocket.close();
                        mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                .sendToTarget();
                    } catch (IOException e2) {
                        //insert code to deal with this
                        Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                    }
                }
                if(fail == false) {
                    mConnectedThread = new StartScreen.ConnectedThread(mBTSocket);
                    mConnectedThread.start();

                    mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                            .sendToTarget();
                }
            }
        }.start();

        return true;
    }
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connection with BT device using UUID
    }

    //RX/TX thread
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.available();
                    if(bytes != 0) {
                        SystemClock.sleep(100); //pause and wait for rest of data. Adjust this depending on your sending speed.
                        bytes = mmInStream.available(); // how many bytes are ready to be read?
                        bytes = mmInStream.read(buffer, 0, bytes); // record how many bytes we actually read
                        mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                                .sendToTarget(); // Send the obtained bytes to the UI activity
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String input) {
            byte[] bytes = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
}
