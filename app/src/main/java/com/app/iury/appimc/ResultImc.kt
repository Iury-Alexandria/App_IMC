package com.app.iury.appimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.iury.appimc.databinding.ActivityResultImcBinding
//tela 3
class ResultImc : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityResultImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //evento de cliques
        eventsClicks()
        //recupera os dados da tela 2 e mostra na tela 3
        recuperateData()
    }//final onCreate

    override fun onClick(view: View) {
        // botão leva pra tela 2 pra colocar os dados novamente
        if (view.id == R.id.btn_recalculate) { startActivity(Intent(this, ActivitImc::class.java)) }
    }

    //recupera os dados da tela 2 e mostra na tela 3
    private fun recuperateData() {
        //recuperando os dados da tela 2 por um Objeto
        val dadosTela1 = intent.getSerializableExtra("ObjMensagem") as NavegationData
        binding.textUser.text = dadosTela1.name
        binding.textResultImc.setText(dadosTela1.imcResult)
        binding.textMensageImc.text = dadosTela1.mensageImc

        // recuperando os dados da tela 2 por variáveis
      /*  binding.textResultImc.text = intent.getStringExtra("imcText").toString()
        binding.textMensageImc.text = intent.getStringExtra("mensageImc").toString()*/
    }

    //evento de cliques
    private fun eventsClicks() {
        binding.btnRecalculate.setOnClickListener(this)
    }

}