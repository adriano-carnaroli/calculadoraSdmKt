package br.edu.ifsp.scl.calculadosdmkt.controller

import br.edu.ifsp.scl.calculadosdmkt.model.*
import br.edu.ifsp.scl.calculadosdmkt.view.ConfiguracaoActivity

class ConfiguracaoController(val view: ConfiguracaoActivity) {
    //Model
    val model:ConfiguracaoService

    init {
        model = ConfiguracaoService(view.applicationContext)
    }

    fun salvaConfiguracao(configuracao: Configuracao) {
        model.setConfiguracao(configuracao)
        view.atualizaView(configuracao)
        model.setDao()
    }

    fun buscaConfiguracao() {
        val configuracao = model.getConfiguracao()
        view.atualizaView(configuracao)
    }



}