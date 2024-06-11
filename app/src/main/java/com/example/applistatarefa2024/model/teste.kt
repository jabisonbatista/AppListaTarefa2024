package com.example.applistatarefa2024.model

fun main(){
 val creditCardAPI = "master"
 val card = CreditCard.valueOf(creditCardAPI.uppercase())
 if (card == CreditCard.VISA){
     println("Dá um desconto")
 }else{
     println("avisa o usuario que o cartão VISA tem desconto")
 }
}
enum class CreditCard(val label : String) {
    VISA("Visa"),
    MASTER("Mastercad"),
    ELO("Elo"),
    AMEX("American Express")

}


 
