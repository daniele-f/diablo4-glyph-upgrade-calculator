package logic

import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement

fun updateStoneCost() {
    val currentLevel = document.getElementById("currentLevel") as? HTMLInputElement
    val desiredLevel = document.getElementById("desiredLevel") as? HTMLInputElement
    val stoneNoDeath = document.getElementById("stoneNoDeath") as? HTMLInputElement
    val stoneOutput = document.getElementById("stoneOutput")

    val from = currentLevel?.value?.toIntOrNull() ?: 1
    val to = desiredLevel?.value?.toIntOrNull() ?: 1
    var stones = 0
    var runs = 0
    val upgradesPerRun = if (stoneNoDeath?.checked == true) 4 else 3

    if (from < 1 || to <= from) {
        stoneOutput?.textContent = "Please enter valid levels (desired must be higher than current)."
        return
    }

    var level = from
    while (level < to) {
        val gain = upgradesPerRun
        level += gain
        stones += 3
        runs += 1
    }

    stoneOutput?.innerHTML = "<strong>Minimum Runs Needed: $runs</strong><br><strong>Total Artificer's Stones Needed: $stones</strong>"
}

fun setRange(start: Int, end: Int) {
    val currentLevel = document.getElementById("currentLevel") as? HTMLInputElement
    val desiredLevel = document.getElementById("desiredLevel") as? HTMLInputElement
    currentLevel?.value = start.toString()
    desiredLevel?.value = end.toString()
    updateStoneCost()
}
