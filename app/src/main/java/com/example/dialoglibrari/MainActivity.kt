package com.example.dialoglibrari

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dialoglibrari.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val routes : List<String> = listOf("Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro",
        "Etul 4", "El Rapido","Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro", "Etul 4",
        "El Rapido","Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro", "Etul 4", "El Rapido",
        "Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro", "Etul 4", "El Rapido")
    private val routesShort : List<String> = listOf("Cruz del Centro", "Etul 4", "El Rapido", "Corredor Morado")

    private var dialogBuilder: DialogBuilder? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSimpleDialog.setOnClickListener {
            dialogBuilder = DialogBuilder(this)
                .setTitleStyle(title = "¿Confirmar?", size = 30F)
                .setDescription("¿Está seguro que desea continuar?", 20F)
                .setPositiveButton("Yes", 10F, Color.GREEN){
                    dialogBuilder?.dismissDialog()
                    Toast.makeText(this, "Continuando....", Toast.LENGTH_LONG).show()
                }
                .setBackgroundColor(borderWidth = 5)
                .isEasilyCloseAble(true)

            dialogBuilder?.build()
        }

        binding.buttonListDialog.setOnClickListener {
            dialogBuilder =  DialogBuilder(this)
                .setTitleStyle("Confirmación", 30F, Color.BLACK)
                .setDescription("¿Está seguro que desea continuar?", 20F)
                .setPositiveButton("Sí", 10F, Color.GREEN){
                    Toast.makeText(this, "Continuando....", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No", 15F, Color.RED) {
                    dialogBuilder?.dismissDialog()
                    println("CERRANDO DIALOOOOOOOGGGGGGG")
                }
                .setListItems(routes)
                .seOnItemClickListener { selectedItem ->
                    Toast.makeText(this, "Seleccionaste $selectedItem", Toast.LENGTH_LONG).show()
                }
                .setBackgroundColor(colorBackground = Color.GRAY, borderWidth = 10)

            dialogBuilder?.build()
        }


//        dialogBuilder = DialogBuilder(this)
//            .setTitleStyle(title = "Confirma la compra?", size = 30F, color = "000000")
//            .setDescription("¿Está seguro que desea continuar?")
//            .setPositiveButton("Sí"){
//                dialogBuilder?.dismissDialog()
//                Toast.makeText(this, "Continuando....", Toast.LENGTH_LONG).show()
//            }
//            .setBackgroundColor(colorBackground = "ffffff", colorBorder = "028000", borderWidth = 5)
//
//        dialogBuilder?.build()

//        dialogBuilder =  DialogBuilder(this)
//            .setTitleStyle("Confirmación", 30F, "000000")
//            .setDescription("¿Está seguro que desea continuar?", 20F)
//            .setPositiveButton("Sí"){
//                Toast.makeText(this, "Continuando....", Toast.LENGTH_LONG).show()
//            }
//            .setNegativeButton("No") {
//                dialogBuilder?.dismissDialog()
//                println("CERRANDO DIALOOOOOOOGGGGGGG")
//            }
//            .setListItems(routes)
//            .setBackgroundColor(colorBackground = "ffffff", colorBorder = "028000", borderWidth = 10)
////            .setBackgroundColor("#ffffff", "#028000", 50)
//
//        dialogBuilder?.build()

    }
}
