import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement

private var lastFinalGlyphLevel = 1

fun updateResults() {
    val glyphInput = document.getElementById("glyphLevel") as? HTMLInputElement
    val noDeathBonus = document.getElementById("noDeathBonus") as? HTMLInputElement
    val calcPlus20 = document.getElementById("calcPlus20") as? HTMLInputElement
    val output = document.getElementById("output")

    val glyphLevel = glyphInput?.value?.toIntOrNull() ?: 1
    val attempts = if (noDeathBonus?.checked == true) 4 else 3
    val extraUpgrade = if (calcPlus20?.checked == true) 1 else 0

    if (glyphLevel < 1) {
        output?.textContent = "Please enter a valid glyph level (1 or higher)."
        return
    }

    val finalGlyphLevel = glyphLevel + attempts - 1 + extraUpgrade
    lastFinalGlyphLevel = finalGlyphLevel
    val requiredPit = if (calcPlus20?.checked == true) glyphLevel + 20 else finalGlyphLevel + 10

    output?.innerHTML = "<strong>Minimum Pit Level: $requiredPit</strong><br>" +
            "Glyph level after run: <strong>$finalGlyphLevel</strong>"
}

fun adjustGlyphLevel(delta: Int) {
    val glyphInput = document.getElementById("glyphLevel") as? HTMLInputElement
    val current = glyphInput?.value?.toIntOrNull() ?: 1
    val newValue = maxOf(1, current + delta)
    glyphInput?.value = newValue.toString()
    updateResults()
}

fun resetGlyphLevel() {
    val glyphInput = document.getElementById("glyphLevel") as? HTMLInputElement
    glyphInput?.value = "1"
    updateResults()
}

fun applyFinalLevel() {
    val glyphInput = document.getElementById("glyphLevel") as? HTMLInputElement
    glyphInput?.value = lastFinalGlyphLevel.toString()
    updateResults()
}

fun updateStoneCost() {
    val currentLevel = document.getElementById("currentLevel") as? HTMLInputElement
    val desiredLevel = document.getElementById("desiredLevel") as? HTMLInputElement
    val stoneNoDeath = document.getElementById("stoneNoDeath") as? HTMLInputElement
    val pitPlus20 = document.getElementById("pitPlus20") as? HTMLInputElement
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
        val gain = if (pitPlus20?.checked == true) upgradesPerRun + 1 else upgradesPerRun
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
