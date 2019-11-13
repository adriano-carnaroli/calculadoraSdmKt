package br.edu.ifsp.scl.calculadosdmkt.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class Separador { VIRGULA, PONTO }
enum class FonteDados { SHARED_PREFERENCES, SQLITE }

@Parcelize
data class Configuracao(var leiauteAvancado:Boolean = false,
                        var separador: Separador = Separador.PONTO): Parcelable

@Parcelize
data class ConfiguracaoFonteDados(var fonteDados: FonteDados = FonteDados.SHARED_PREFERENCES): Parcelable