package com.byjusexamprep.hackathon.ui.composables.home_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow



class MainViewModel : ViewModel() {

    private val squadList = listOf(
        "Nucleus", "Sigma", "Qubit", "Electrons", "Momentum", "Photon", "Quantum",
        "Delta"
    )

    private val membersList = listOf(
        "Omar", "Prashant", "Sanchit", "Sanjeev", "Ankit", "Abhishek", "Faheem",
        "Ankit Raj", "Sakshi Pruthi", "Aditya Mathur", "Moghira", "Abhilash",
        "Gauri Advani", "Gunjit", "Nitin Bhatia",
        "Vaibhav", "Vatsal", "Yogesh", "Ricky", "Tushar"
    )

    private val teamMap = HashMap<String, String>()
    fun buildTeamMap(){
        teamMap.apply {
            this["Omar"] = "Sigma"
            this["Prashant"] = "Nucleus"
            this["Sanchit"] = "Qubit"
            this["Sanjeev"] = "Electrons"
            this["Ankit"] = "Sigma"
            this["Abhishek"] = "Photon"
            this["Faheem"] = "Photon"
            this["Ankit Raj"] = "Momentum"
            this["Sakshi Pruthi"] = "Qubit"
            this["Aditya Mathur"] = "Nucleus"
            this["Moghira"] = "Momentum"
            this["Abhilash"] = "Delta"
            this["Gauri Advani"] = "Quantum"
            this["Gunjit"] = "Nucleus"
            this["Nitin Bhatia"] = "Nucleus"
            this["Vaibhav"] = "Sigma"
            this["Yogesh"] = "Qubit"
            this["Ricky"] = "Nucleus"
            this["Tushar"] = "Sigma"
        }
    }

    val randomSquadFlow = MutableStateFlow("Squad")
    val randomMemberFlow = MutableStateFlow("Member")
    val matchFlow = MutableStateFlow(false)
    val newText = MutableStateFlow(false)
    val score = MutableStateFlow(0)
    val remainingAttempts = MutableStateFlow(5)

    fun generateRandom(){
        getRandomSquad()
        getRandomMember()
    }

    private fun getRandomSquad() {
        randomSquadFlow.value = squadList.random()
    }

    private fun getRandomMember() {
        randomMemberFlow.value = membersList.random()
    }

    fun checkIfItsAMatchV1(){
        if(teamMap[randomMemberFlow.value] == randomSquadFlow.value){
            matchFlow.value = true
            score.value = score.value + 1
        } else {
            matchFlow.value = false
        }
        newText.value = true
        remainingAttempts.value = remainingAttempts.value - 1
    }

    fun checkIfItsAMatch() : Boolean{
        getRandomSquad()
        getRandomMember()
        if(teamMap[randomMemberFlow.value] == randomSquadFlow.value){
            matchFlow.value = true
            score.value = score.value + 1
        } else {
            matchFlow.value = false
        }
        matchFlow.value = (teamMap[randomMemberFlow.value] == randomSquadFlow.value)
        newText.value = true
        remainingAttempts.value = remainingAttempts.value - 1
        return teamMap[randomMemberFlow.value] == randomSquadFlow.value
    }

    fun clearAttempts(){
        score.value = 0
        remainingAttempts.value = 5
    }

}