package com.app.iury.appimc

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.app.iury.appimc.databinding.ActivityImcBinding

//tela 2
class ActivitImc : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //evento de cliques
        eventsClicks()

    }// final onCreate


    //evento de cliques
    fun eventsClicks() {
        binding.btnSend.setOnClickListener(this)
    }

    //ações
    override fun onClick(view: View) {
        if (view.id == R.id.btn_send) {
            if (!validateInputs()) {//cenário triste


                //caixa PopUp
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle(R.string.important)
                dialog.setMessage(R.string.fields_message)
                dialog.setPositiveButton(
                    android.R.string.ok,
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                        }
                    })
                dialog.create().show()

            } else {// cenário feliz

                // passando a função calcImc como variável na string que mostra na tela 3
                //val imcResult = calcImc()
                val resultImcFloat = getString(R.string.imc_response, calcImc())

                val imcText = getString(mensageImc(calcImc()))

                val name = binding.editName.text.toString()

                //instânciando a classe NaegationData
                val navegationData = NavegationData(resultImcFloat, imcText, name)

                //chamando outra tela//passado IMC e a Mensagem pra tela ResultImc
                val intent = Intent(this, ResultImc::class.java)
                intent.putExtra("ObjMensagem", navegationData)
                startActivity(intent)

            }
        }
    }

    //função pra verificar os campos nulos e  input 0
    private fun validateInputs(): Boolean {
        val weight = binding.imcWeight.text.toString()
        val height = binding.imcHeight.text.toString()
        val name = binding.editName.text.toString()

        return (weight.isNotEmpty() &&
                height.isNotEmpty() &&
                name.isNotEmpty()   &&
                !weight.startsWith("0") &&
                !height.startsWith("0"))
    }

    // Retorna a situação de acordo com o IMC
    fun mensageImc(imc: Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }

    //calcula o IMC
    fun calcImc(): Double {
        val weight = binding.imcWeight.text.toString().toInt()
        val height = binding.imcHeight.text.toString().toInt()
        // peso / altura²
        return weight / ((height / 100.0) * (height / 100.0))
    }


}