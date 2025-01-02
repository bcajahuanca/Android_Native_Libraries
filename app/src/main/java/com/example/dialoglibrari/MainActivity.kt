package com.example.dialoglibrari

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dialoglibrary.DialogManager
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val routes : List<String> = listOf("Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro",
        "Etul 4", "El Rapido","Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro", "Etul 4",
        "El Rapido","Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro", "Etul 4", "El Rapido",
        "Cruz del Centro", "Etul 4", "El Rapido","Cruz del Centro", "Etul 4", "El Rapido")
//    private val routes : List<String> = listOf("Cruz del Centro", "Etul 4", "El Rapido")

    private var dialogBuilder: DialogBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialogBuilder = DialogBuilder(this)
            .setTitleStyle(title = "Confirma la compra?", size = 30F)
            .setDescription("¿Está seguro que desea continuar?")
            .setPositiveButton("Sí"){
                dialogBuilder?.dismissDialog()
                Toast.makeText(this, "Continuando....", Toast.LENGTH_LONG).show()
            }
            .setBackgroundColor(colorBackground = "#ffffff", colorBorder = "#028000", borderWidth = 10)

        dialogBuilder?.build()

//        dialogBuilder =  DialogBuilder(this)
//            .setTitle("Confirmación")
//            .setDescription("¿Está seguro que desea continuar?")
//            .setPositiveButton("Sí"){
//                Toast.makeText(this, "Continuando....", Toast.LENGTH_LONG).show()
//            }
//            .setNegativeButton("No") {
//                dialogBuilder?.dismissDialog()
//                println("CERRANDO DIALOOOOOOOGGGGGGG")
//            }
//            .setListItems(routes)
//            .setBackgroundColor(colorBackground = "#ffffff", colorBorder = "#028000", borderWidth = 10)
////            .setBackgroundColor("#ffffff", "#028000", 50)
//
//        dialogBuilder?.build()

    }
}
