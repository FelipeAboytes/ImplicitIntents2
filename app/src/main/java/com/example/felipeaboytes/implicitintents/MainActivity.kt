package com.example.felipeaboytes.implicitintents

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    // EditText view for the website URI
    private var mWebsiteEditText: EditText? = null
    // EditText view for the location URI
    private var mLocationEditText: EditText? = null
    // EditText view for the share text
    private var mShareTextEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebsiteEditText = findViewById(R.id.website_edittext) as EditText
        mLocationEditText = findViewById(R.id.location_editext) as EditText
        mShareTextEditText = findViewById(R.id.share_edittext) as EditText
    }

    fun openWebsite(v: View) {
        // Get the URL text.
        val url = mWebsiteEditText!!.getText().toString()

        // Parse the URI and create the intent.
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    fun openLocation(v: View) {
        // Get the string indicating a location.  Input is not validated; it is
        // passed to the location handler intact.
        val loc = mLocationEditText!!.getText().toString()

        // Parse the location and create the intent.
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    fun shareText(v: View) {
        // Get the shared text.
        val txt = mShareTextEditText!!.getText().toString()

        // Build the share intent with the mimetype text/plain and launch
        // a chooser for the user to pick an app.
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser()
    }


}
