package tech.henridev.bored

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // initialisation des variables servants à recupérer les element du formulaire.
    lateinit var Atype: Spinner
    lateinit var Anumber: EditText
    lateinit var Abutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Création d'un arrayAdapter pour la liste de selection du formulaire
        val spinner: Spinner = findViewById(R.id.activity_type)
        ArrayAdapter.createFromResource(this,
                R.array.type,
                android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            // affectation de la valeur du bouton submit du formulaire à  la variable Abutton
            Abutton = findViewById(R.id.submit)

            // sequence à exécuter au lorsqu'on clique sur le bouton

            Abutton.setOnClickListener() {

                            // CORPS DE LA SEQUENCE DU TRAITEMENT DU FORMULAIRE\\

                            // recuperation du nombre de participant entrer par l'utilisateur
                            Anumber = findViewById(R.id.editTextNumber)
                            val participant = Anumber.text.toString()

                            // recupération du type d'activité entrer par l'utilisateur
                            Atype = findViewById(R.id.activity_type)
                            val activityType = Atype.selectedItem.toString().toLowerCase()

                            // creation d'un nouvelle object retrofit
                            val retrofit = Retrofit.Builder()
                                    .baseUrl("https://www.boredapi.com") // définition de l'ur principale de notre api

                                    .addConverterFactory(GsonConverterFactory.create()) // spécification du parseur de donnée qu'on
                                    //utilisera lors du parsing de la réponse

                                    .build()
                            val service = retrofit.create(RESTService::class.java)  // création de la requete

                                    // création d'une coroutine
                            CoroutineScope(Dispatchers.IO).launch {

                                //envoie de la requete
                                val response = service.getActivity(participants = participant, type = activityType)

                                withContext(Dispatchers.Main)
                                {
                                    if (response.isSuccessful) {

                                        Log.d("***********************", "*******************************")
                                        Log.d("Response : ", response.body().toString())
                                        Log.d("***********************", "*******************************")


                                        // recupération des objet de la requete suivant le format de données établi
                                        val act = response.body()
                                        val title = act?.title.toString()
                                        val type = act?.type.toString()
                                        val people = act?.people.toString()
                                        val cost = act?.cost.toString()
                                        val level = act?.Alevel.toString()

                                        val intent = Intent(this@MainActivity, result::class.java)
                                        intent.putExtra("activity", title)
                                        intent.putExtra("type", type)
                                        intent.putExtra("participants", people)
                                        intent.putExtra("price", cost)
                                        intent.putExtra("level", level)

                                        startActivity(intent)


                                    } else {

                                        Log.e("Retrofit error : ", response.code().toString())
                                        Log.e("Error message :", response.message().toString())
                                       val intent1 = Intent(this@MainActivity, MainActivity::class.java)
                                        startActivity(intent1)
                                    }
                                }

                }

            }
        }


    }
}