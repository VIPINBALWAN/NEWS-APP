package com.example.reycleviewitemclick

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var dataset: ArrayList<data>
    private lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()
        dataset = ArrayList()
        val recycleview = findViewById<RecyclerView>(R.id.recycleview)


        val title = arrayOf(
            "Railway staffer detained for stealing detonators, setting them off in Madhya Pradesh", "Centre's notice to a ghee supplier for Tirupati temple amid laddu row: Sources",
            "India reports first case of mpox from new, fast-spreading clade 1b variety", "Pune airport to be renamed 'Tukaram Maharaj Airport'",
            "India's one-horned Asian rhino population triples in four decades"
        )
        val image = arrayOf(
            "https://akm-img-a-in.tosshub.com/indiatoday/images/story/202409/railway-track-213607324-16x9.jpg?VersionId=_6B8Eg7_CnFhKw4MMk2D16eWtVES0_55&size=690:388",
            "https://akm-img-a-in.tosshub.com/indiatoday/images/story/202409/tirupati-laddoos-232457405-16x9_0.jpeg?VersionId=ixik08VdBInmPM.ZbjkCD8HkGsJT_M7M&size=690:388",
            "https://akm-img-a-in.tosshub.com/indiatoday/images/story/202409/health-ministry-to-begin-screening-for-mpox-093747334-16x9_3.png?VersionId=pmrgnIiO_biKVLeAIoBlqw2TPq5bHnuW&size=690:388",
            "https://akm-img-a-in.tosshub.com/indiatoday/images/story/202409/pune-airport-230634458-16x9_0.jpeg?VersionId=ttMKdTUOBRDwizhxQecICtqYnLUk8HOe&size=690:388",
            "https://akm-img-a-in.tosshub.com/indiatoday/images/story/202409/rhino-232802891-16x9_0.jpg?VersionId=bzd6fXEh7ft0SxjtLYK0JtVbMYO8ZTUC&size=690:388"
        )
        val desc=arrayOf("A railway staffer has been taken into custody for stealing 10 harmless detonators that went off on a track during the passing of a military special train in Madhya Pradesh, an official said on Monday.\n"+
                "Ten detonators, termed harmless by the Railways, went off on a track near Sagphata between Nepanagar and Khandwa stations of the Bhusawal division on September 18.\n"+
                "The explosion prompted the authorities to halt a military special train for two minutes.",
                "The Union Health Ministry has issued a show cause notice to AR Dairy, one of the companies which supplied ghee to the Tirupati temple, sources told India Today TV.\n" +
                "\n" +"The notice has been sent by the Food Safety and Standards Authority of India (FSSAI), the government's food regulatory authority.\n" +
                "\n" +"AR Dairy came under scrutiny after a report by NDDB CALF, a private laboratory specialising in testing animal feed and dairy products, found that ghee samples used in the famed Tirupati laddus contained foreign fats, including palm oil, fish oil, beef tallow, and lard (pig fat).",
                "India said on Monday that an mpox case involving a traveller in the southern state of Kerala was from the fast-spreading clade 1b variety, marking the country's first recorded case from the new strain\n"+
                " Manisha Verma, a spokesperson for the health ministry, confirmed the strain after news agency ANI cited official sources as saying that the mpox case reported in Kerala's Malappuram district last week belonged to clade 1.",
                "The Maharashtra government on Monday officially approved the renaming of Pune airport to 'Tukaram Maharaj Airport' in honour of the revered 17th-century saint. The decision was made during a cabinet meeting held today.\n"+
                "This move aims to pay tribute to Sant Tukaram Maharaj, a prominent spiritual figure in Maharashtra's history, known for his devotional poetry and influence on the Bhakti movement.",
                "India's one-horned Asian rhino population has almost tripled in the past four decades thanks to conservation and anti-poaching efforts, according to government figures.\n"+
                "Data released on Sunday -- World Rhino Day -- said the number of the animals, known for their single horn and thick, armour-like skin, had surged from 1,500 four decades ago to more than 4,000 now.\n"+
                "There were just 600 left in India in the 1960s.")

        for (i in title.indices) {
            dataset.add(data(title[i], image[i],desc[i]))
        }
        fetchNewsData()
        adapter = MyAdapter(this, dataset)
        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = adapter
        adapter.onclicklistner(object : MyAdapter.onitemclicklistner {
            override fun onclick(position: Int) {
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                intent.putExtra("image", dataset[position].imageUrl)
                intent.putExtra("title", dataset[position].title)
                intent.putExtra("desc", dataset[position].description)
                startActivity(intent)
            }

        })
    }
        private fun fetchNewsData() {
            db.collection("news").get().addOnSuccessListener { result ->
                    for (document in result) {
                        val data = document.toObject(data::class.java)
                        if (data != null) {
                            dataset.add(data)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.w("MainActivity", "Error fetching data: ", exception)
                }
        }
}
