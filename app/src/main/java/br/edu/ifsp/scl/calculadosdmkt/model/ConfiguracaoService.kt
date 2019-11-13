package br.edu.ifsp.scl.calculadosdmkt.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class ConfiguracaoService(val context: Context) {
    lateinit var configuracaoDao: ConfiguracaoDao
    init{
        // Inicializando conforme a fonte de dados utilizada
        setDao()
        //configuracaoDao = ConfiguracaoSharedPreferences(context)
        //configuracaoDao = ConfiguracaoSqlite(context)
    }
    fun setConfiguracao(configuracao: Configuracao) {
        /* Qualquer tratamento necessário aos dados antes de salvá-los
        na fonte de dados escolhida deve ser feita no Service.
        As classes que implementam o DAO devem esconder as peculiaridades
        para acesso a cada fonte de dados diferente e executar apenas as funções
        de CRUD.*/
        // Tratamento de dados aqui!
        // Delegando ao modelo
        configuracaoDao.createOrUpdateConfiguracao(configuracao)
    }
    fun getConfiguracao(): Configuracao {
        // Tratamento de dados aqui!
        // Delegando ao modelo
        return configuracaoDao.readConfiguracao()
    }

    fun setDao() {
        val fonteDados = ConfigFonteDadosSharedPreferences(context).readConfigFonteDados().fonteDados
        configuracaoDao = if (fonteDados == FonteDados.SHARED_PREFERENCES) ConfiguracaoSharedPreferences(context) else ConfiguracaoSqlite(context)
    }
}