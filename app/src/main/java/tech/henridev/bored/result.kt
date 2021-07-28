package tech.henridev.bored

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class result : AppCompatActivity() {
    lateinit var rActivity : TextView
    lateinit var rType : TextView
    lateinit var rPeople : TextView
    lateinit var rPrice : TextView
    lateinit var rLevel : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)



        rActivity = findViewById(R.id.acttext)

        rActivity.text = intent.getStringExtra("activity")

        rType = findViewById(R.id.typetext)

        rType.text = intent.getStringExtra("type")


        rPeople = findViewById(R.id.poepletext)

        rPeople.text = intent.getStringExtra("participants")


        rPrice = findViewById(R.id.pricetext)
        rPrice.text = intent.getStringExtra("price")

        rLevel = findViewById(R.id.accesstext)
        rLevel.text = intent.getStringExtra("level")

    }
}