package br.edu.ifsp.scl.calculadosdmkt.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.calculadosdmkt.R
import br.edu.ifsp.scl.calculadosdmkt.controller.ConfiguracaoController
import br.edu.ifsp.scl.calculadosdmkt.model.*
import kotlinx.android.synthetic.main.activity_configuracao.*
import kotlinx.android.synthetic.main.toolbar.*

class ConfiguracaoActivity:AppCompatActivity() {
    object Constantes {
        //chave de retorno para a MainActivity
        val CONFIGURACAO = "CONFIGURACAO"
    }

    lateinit var configSH:ConfigFonteDadosSharedPreferences

    // Referencia para Controller
    lateinit var configuracaoController: ConfiguracaoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracao)

        // Toolbar
        toolbar.title = "Configuração"
        setSupportActionBar(toolbar)

        // Chama controller e atualiza view
        configSH = ConfigFonteDadosSharedPreferences(this)
        configuracaoController = ConfiguracaoController(this)
        configuracaoController.buscaConfiguracao()
    }

    // função chamada pelo controller depois de acessar o model
    fun atualizaView(configuracao: Configuracao) {
        // Ajusta layout conforme configuração

        leiauteSpn.setSelection(if (configuracao.leiauteAvancado) 1 else 0)
        separadorRg.check(if (configuracao.separador == Separador.PONTO) R.id.pontoRb else R.id.virgulaRb)

        fonteDadosRg.check(if (configSH.readConfigFonteDados().fonteDados == FonteDados.SHARED_PREFERENCES) R.id.sharedPrefRb else R.id.sqliteRb)
        // Setar resultado para a main activity
        setResult(AppCompatActivity.RESULT_OK, Intent().putExtra(Constantes.CONFIGURACAO, configuracao))

    }

    fun onClickSalvaConfiguracao(v: View) {
        // Pega os dados da tela
        val leiauteAvancado = leiauteSpn.selectedItemPosition == 1
        val separador = if (pontoRb.isChecked) Separador.PONTO else Separador.VIRGULA
        val fonteDados = if (sharedPrefRb.isChecked) FonteDados.SHARED_PREFERENCES else FonteDados.SQLITE

        // Criar um objeto Configuracao
        val novaConfiguracao = Configuracao(leiauteAvancado, separador)

        //salava a fonte de dados
        configSH.createOrUpdateConfigFonteDados(ConfiguracaoFonteDados(fonteDados))

        // Chamar o Controller para salvar
        configuracaoController.salvaConfiguracao(novaConfiguracao)

        Toast.makeText(this, "Configuração salva!", Toast.LENGTH_SHORT).show()
        finish()
    }
}