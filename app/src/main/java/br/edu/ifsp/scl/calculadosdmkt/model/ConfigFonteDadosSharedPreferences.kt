package br.edu.ifsp.scl.calculadosdmkt.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class ConfigFonteDadosSharedPreferences(context: Context) {
    companion object{
        val NOME_ARQUIVO = "configFonteDados"
        val MODO_ARQUIVO = Context.MODE_PRIVATE
        val TAG_CONFIG_FONTE_DADOS = "configFonteDados"
    }
    val sharedPreferences: SharedPreferences
    val gson: Gson
    init{
        sharedPreferences = context.getSharedPreferences(
            ConfiguracaoSharedPreferences.NOME_ARQUIVO,
            ConfiguracaoSharedPreferences.MODO_ARQUIVO
        )
        gson = GsonBuilder().create()
    }

    fun createOrUpdateConfigFonteDados(config: ConfiguracaoFonteDados) {
        val configuracaoJson = JSONObject(gson.toJson(config))

        val spEditor: SharedPreferences.Editor = sharedPreferences.edit()

        spEditor.putString(TAG_CONFIG_FONTE_DADOS, configuracaoJson.toString())
        spEditor.commit()
    }

    fun readConfigFonteDados(): ConfiguracaoFonteDados {
        val configuracaoString = sharedPreferences.getString(TAG_CONFIG_FONTE_DADOS, "")
        return if (configuracaoString != "")
            gson.fromJson(configuracaoString, ConfiguracaoFonteDados::class.java)
        else
            ConfiguracaoFonteDados()
    }
}