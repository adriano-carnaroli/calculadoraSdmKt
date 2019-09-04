package br.edu.ifsp.scl.calculadosdmkt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import br.edu.ifsp.scl.calculadorasdmkt.Calculadora
import br.edu.ifsp.scl.calculadorasdmkt.Operador
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    var concatenaLcd: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Primeira linha
        seteBt.setOnClickListener(this)
        oitoBt.setOnClickListener(this)
        noveBt.setOnClickListener(this)
        maisBt.setOnClickListener(this)

        umBt.setOnClickListener {
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((it as Button).text.toString())
            concatenaLcd = true
        }

        doisBt.setOnClickListener { it: View ->
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((it as Button).text.toString())
            concatenaLcd = true
        }

        tresBt.setOnClickListener { botao: View ->
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((botao as Button).text.toString())
            concatenaLcd = true
        }

        multiplicacaoBt.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                if (!lcdTv.text.toString().isNullOrEmpty()){
                    lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.MULTIPLICACAO)
                    concatenaLcd = false
                }
            }
        })

        zeroBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
        pontoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
        divisaoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
        val f: (View) -> Unit = ::onClickZeroPontoResultadoDivisao
        igualBt.setOnClickListener(f)

        //Acões extras
        limparBt.setOnClickListener {
            lcdTv.text = ""
            Calculadora.operador = Operador.RESULTADO
            Calculadora.operando = 0.0f;
            concatenaLcd = true
        }

        desfazerBt.setOnClickListener {
            lcdTv.text = ""
            concatenaLcd = true
        }

        raizBt.setOnClickListener {
            if (!lcdTv.text.toString().isNullOrEmpty()) {
                Calculadora.operador = Operador.RAIZ_QUADRADA
                lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.RESULTADO)
                concatenaLcd = false
            }
        }

        porcentagemBt.setOnClickListener {
            if (!lcdTv.text.toString().isNullOrEmpty()) {
                Calculadora.operador = Operador.PORCENTAGEM
                lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.RESULTADO)
                concatenaLcd = false
            }
        }

    }

    override fun onClick(p0: View?) {
        if (p0 == seteBt || p0 == oitoBt || p0 == noveBt) {
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((p0 as Button).text.toString())
            concatenaLcd = true
        } else {
            if (p0 == maisBt) {//Adicao
                if (!lcdTv.text.toString().isNullOrEmpty()) {
                    lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.ADICAO)
                    concatenaLcd = false
                }
            }
        }
    }

    fun onClickBtNum(p0: View?) {
        if (!concatenaLcd) {
            lcdTv.text = ""
        }
        lcdTv.append((p0 as Button).text.toString())
        concatenaLcd = true
    }

    fun onClickBtSub(p0: View) {
        if (p0 == menosBt) {//Subtracao
            if (!lcdTv.text.toString().isNullOrEmpty()) {
                lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.SUBTRACAO)
                concatenaLcd = false
            }
        }
    }

    fun onClickZeroPontoResultadoDivisao(view: View?) {
        when (view){
            zeroBt -> {
                // Limpa LCD se último clicado foi um operador
                if (!concatenaLcd) {
                    lcdTv.text = ""
                }
                lcdTv.append((view as Button).text.toString())
                concatenaLcd = true
            }
            pontoBt -> {
                if (!lcdTv.text.toString().contains(".")){
                    if (!concatenaLcd) {
                        lcdTv.text = "0"
                    }
                    lcdTv.append(".")
                    concatenaLcd = true
                }
            }
            igualBt -> {
                if (!lcdTv.text.toString().isNullOrEmpty()) {
                    lcdTv.text = Calculadora.calcula(
                        lcdTv.text.toString().toFloat(),
                        Operador.RESULTADO
                    )
                    concatenaLcd = false
                }
            }
            divisaoBt -> {
                if (!lcdTv.text.toString().isNullOrEmpty()) {
                    lcdTv.text = Calculadora.calcula(
                        lcdTv.text.toString().toFloat(),
                        Operador.DIVISAO
                    )
                    concatenaLcd = false
                }
            }
        }
    }

}
