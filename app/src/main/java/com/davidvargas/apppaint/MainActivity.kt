package com.davidvargas.apppaint

import android.app.Dialog
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tamano.*
import kotlinx.android.synthetic.main.tamano_goma.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val pincelGrande = 100.0f
    val pincelMediano = 30.0f
    val pincelPequeño = 5.0f

    val gomaGrande = 100.0f
    val gomaMediano = 30.0f
    val gomaPequeño = 5.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ibtColorBlack.setOnClickListener {
            cuadro.ponerColor(resources.getColor(R.color.black))
        }
        ibtColorBlue.setOnClickListener {
            cuadro.ponerColor(resources.getColor(R.color.Blue))
        }
        ibtColorYellow.setOnClickListener {
            cuadro.ponerColor(resources.getColor(R.color.Yellow))
        }
        ibtColorRed.setOnClickListener {
            cuadro.ponerColor(resources.getColor(R.color.Red))
        }
        ibtColorGreen.setOnClickListener {
            cuadro.ponerColor(resources.getColor(R.color.Green))
        }

        ibtTamanoPincel.setOnClickListener {

            val dialog = Dialog(this)
            dialog.window?.setBackgroundDrawableResource(R.color.transparent)
            dialog.setContentView(R.layout.tamano)

            dialog.btPincelGrande.setOnClickListener {

                cuadro.ponerTamanoPincel(pincelGrande)
                dialog.dismiss()
            }
            dialog.btPincelMediano.setOnClickListener {

                cuadro.ponerTamanoPincel(pincelMediano)
                dialog.dismiss()
            }

            dialog.btPincelPequeno.setOnClickListener {

                cuadro.ponerTamanoPincel(pincelPequeño)
                dialog.dismiss()
            }
            dialog.show()
        }
        ibtSave.setOnClickListener {

            cuadro.isDrawingCacheEnabled = true

            MediaStore.Images.Media.insertImage(
                contentResolver,
                cuadro.getDrawingCache(),
                UUID.randomUUID().toString() + ".png",
                "drawing"
            )

            cuadro.destroyDrawingCache()

        }
        ibtDelete.setOnClickListener {

            val dialog = Dialog(this)
            dialog.window?.setBackgroundDrawableResource(R.color.transparent)
            dialog.setContentView(R.layout.tamano_goma)

            dialog.btGomaGrande.setOnClickListener{

                cuadro.ponerTamanoGomaBorrar(gomaGrande)
                dialog.dismiss()
            }

            dialog.btGomaMediana.setOnClickListener {

                cuadro.ponerTamanoGomaBorrar(gomaMediano)
                dialog.dismiss()
            }

            dialog.btGomaPequena.setOnClickListener {

                cuadro.ponerTamanoGomaBorrar(gomaPequeño)
                dialog.dismiss()
            }
            dialog.show()
            cuadro.ponerGomaBorrar(true)
            
        }

        ibtDeleteAll.setOnClickListener{
            cuadro.nuevoCuadro()
        }
    }

}