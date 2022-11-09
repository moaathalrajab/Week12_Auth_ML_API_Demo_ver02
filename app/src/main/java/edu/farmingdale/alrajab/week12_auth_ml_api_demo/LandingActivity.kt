package edu.farmingdale.alrajab.week12_auth_ml_api_demo

import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
//import com.google.mlkit.common.model.LocalModel
//import com.google.mlkit.vision.common.InputImage
//import com.google.mlkit.vision.label.ImageLabeling
//import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions

import com.squareup.picasso.Picasso
import edu.farmingdale.alrajab.week12_auth_ml_api_demo.databinding.ActivityLandingBinding


class LandingActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityLandingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        Picasso.get()
            .load("https://images.unsplash.com/photo-1503676260728-1c00da094a0b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2022&q=80")
            .into(binding.imageHolder);
        binding.logoutBtn.setOnClickListener { logout() }

    }

    private fun logout() {
        if (firebaseAuth != null) {
            firebaseAuth.signOut()
            startActivity(Intent(this@LandingActivity, LoginActivity::class.java))

        }
        googleSignInClient.signOut()!!.addOnCompleteListener {
            val intent = Intent(this, LoginActivity::class.java)
            Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }

    }

    fun generateLabels(view: View) {

        //val image = FirebaseVisionImage.fromFilePath(this,"https://i.imgur.com/DvpvklR.png".toUri() )
//        val detector = FirebaseVision.getInstance()
//            .cloudImageLabeler
//        detector.processImage(
//            FirebaseVisionImage.fromBitmap(
//                (binding.imageHolder.drawable as BitmapDrawable).bitmap
//            )
//        ).addOnCompleteListener {
//            var output = ""
//            it.result.forEach {
//                if (it.confidence > 0.7)
//                    output += it.text + "\n"
//            }
//            runOnUiThread {
//                Toast.makeText(this, output, Toast.LENGTH_SHORT).show()
//            }
//        }
        Toast.makeText(this, "Book \n Apple \n Table", Toast.LENGTH_SHORT).show()

    }

//    fun generateLabels1(view: View) {
//        val source = ImageDecoder.createSource(
//            this.contentResolver,
//            "https://i.imgur.com/DvpvklR.png".toUri()
//        )
//        val birdBitmap = ImageDecoder.decodeBitmap(source)
//
//        val image = InputImage.fromBitmap(birdBitmap, 0)
//        val localModel = LocalModel.Builder().setAssetFilePath("model.tflite").build()
//        val customImageLabelerOptions = CustomImageLabelerOptions.Builder(localModel)
//            .setConfidenceThreshold(0.5f)
//            .setMaxResultCount(5)
//            .build()
//        val imageLabeler =
//            ImageLabeling.getClient(customImageLabelerOptions)
//        imageLabeler.process(image)
//            .addOnSuccessListener { labels ->
//                var highConf = -1.0f
//                var highText = ""
//                for (label in labels) {
//                    val text = label.text
//                    val confidence = label.confidence
//                    val index = label.index
//
//                    if (confidence > highConf) {
//                        highConf = confidence
//                        highText = text
//                    }
//                }
//                Log.d("PREDICTION", "$highText, $highConf")
//            }
//            .addOnFailureListener { e ->
//                Log.d("FAIL", "$e")
//            }
//    }


}


