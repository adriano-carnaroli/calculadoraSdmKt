package br.edu.ifsp.scl.calculadosdmkt.utils

import br.edu.ifsp.scl.calculadosdmkt.model.Separador

/* Classe de enumeração para constantes de operadores */
enum class Operador {
    RESULTADO, ADICAO, SUBTRACAO, MULTIPLICACAO, DIVISAO, PORCENTAGEM, RAIZ_QUADRADA
}

/* Singleton que calcula operações aritméticas básicas */
object Calculadora {
    // primeiro operando
    var operando: Float = 0.0f
    var separador: Separador = Separador.PONTO

    // operador que será aplicado entre primeiro e segundo operando
    var operador: Operador =
        Operador.RESULTADO
    /* calcula um valor de retorno com base no operando e operador já existentes, novo valor
     e atualiza valor de operando e operador */
    fun calcula(v: String, operador: Operador): String {
        var valor = v.replace(",",".").toFloat()
        when (Calculadora.operador) {
            Operador.RESULTADO -> operando = valor
            Operador.ADICAO -> operando += valor
            Operador.SUBTRACAO -> operando -= valor
            Operador.MULTIPLICACAO -> operando *= valor
            Operador.DIVISAO -> operando /= valor
            Operador.PORCENTAGEM -> operando = valor / 100
            Operador.RAIZ_QUADRADA -> operando = Math.sqrt(valor.toDouble()).toFloat()
        }
        Calculadora.operador = operador
        if (operando.toString().indexOf(".") >= 0 && operando.toString().split(".")[1].length == 1) {
            var resultado = operando.toString().replace(".0", "")
            if (separador == Separador.VIRGULA){
                resultado = resultado.replace(".", ",")
            }
            return resultado
        }
        var resultado = operando.toString()
        if (separador == Separador.VIRGULA){
            resultado = resultado.replace(".", ",")
        }
        return resultado
    }
}