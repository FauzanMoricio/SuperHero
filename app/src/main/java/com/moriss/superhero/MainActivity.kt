package com.moriss.superhero

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.moriss.superhero.model.Superhero
import com.moriss.superhero.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var superheroImage: ImageView
    private lateinit var superheroName: TextView
    private lateinit var superheropower: TextView
    private lateinit var superheroBiography: TextView
    private lateinit var superheroApperance: TextView
    private lateinit var superheroWork: TextView
    private lateinit var superheroConnect: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        superheroImage = findViewById(R.id.superheroImage)
        superheroName = findViewById(R.id.superheroName)
        superheropower = findViewById(R.id.Powerstats)
        superheroBiography = findViewById(R.id.Biography)
        superheroApperance = findViewById(R.id.Apperance)
        superheroWork = findViewById(R.id.Work)
        superheroConnect = findViewById(R.id.Connection)

        fetchSuperheroData()
    }

    private fun fetchSuperheroData() {
        RetrofitInstance.api.getSuperhero().enqueue(object : Callback<Superhero> {
            override fun onFailure(call: Call<Superhero>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Superhero>, response: Response<Superhero>) {
                if (response.isSuccessful) {
                    response.body()?.let { superhero ->
                        val imageUrl = superhero.image.url
                        val name = superhero.name

                        val powerstats = superhero.powerstats
                        val intelligence = powerstats.intelligence
                        val strength = powerstats.strength
                        val speed = powerstats.speed
                        val durability = powerstats.durability
                        val power = powerstats.power
                        val combat = powerstats.combat

                        val biography = superhero.biography
                        val fullname = biography.fullName
                        val aliases = biography.aliases?.joinToString(", ")
                        val birth = biography.placeOfBirth
                        val firtAppearance = biography.firstAppearance
                        val publisher = biography.publisher
                        val aligment = biography.alignment

                        val appearance = superhero.appearance
                        val gender = appearance.gender
                        val race = appearance.race
                        val height = appearance.height?.joinToString(", ")
                        val weight = appearance.weight?.joinToString(", ")
                        val eyecolor = appearance.eyeColor
                        val haircolor = appearance.hairColor

                        val work = superhero.work
                        val occupation = work.occupation
                        val base = work.base

                        val connections = superhero.connections
                        val grupafiliasi = connections.groupAffiliation
                        val relatives = connections.relatives

                        val dbiography = """
                            Full Nama : $fullname
                            Alias : $aliases
                            Birth : $birth
                            First Apperance : $firtAppearance
                            Publisher : $publisher
                            Aligment : $aligment
                        """.trimIndent()

                        val dpower = """
                            Intelligence : $intelligence
                            Strength : $strength
                            Speed : $speed
                            Durability : $durability
                            Power : $power
                            Combat : $combat
                            
                        """.trimIndent()

                        val daperance = """
                            Gender : $gender
                            Race : $race
                            height : $height
                            weight : $weight 
                            Eye Color : $eyecolor
                            Hair Color : $haircolor 
                        """.trimIndent()

                        val dwork = """
                            Occupation : $occupation 
                            Base : $base 
                        """.trimIndent()

                        val dconnect = """
                            Grup Afiliasi : $grupafiliasi 
                            Relative : $relatives 
                        """.trimIndent()



                        Glide.with(this@MainActivity).load(imageUrl).into(superheroImage)
                        superheroName.text = name
                        superheroBiography.text = dbiography
                        superheropower.text = dpower
                        superheroApperance.text = daperance
                        superheroWork.text = dwork
                        superheroConnect.text = dconnect
                    }
                }
            }
        })
    }
}





