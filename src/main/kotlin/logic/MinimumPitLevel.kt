package logic

import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import kotlin.math.max

private var lastFinalGlyphLevel = 1

fun updateResults() {
    val glyphInput = document.getElementById("glyphLevel") as? HTMLInputElement
    val noDeathBonus = document.getElementById("noDeathBonus") as? HTMLInputElement
    val pitLevelBonusInput = document.getElementById("pitLevelBonus") as? HTMLSelectElement
    val output = document.getElementById("output")
    val result = document.getElementById("resultGlyphLevel")

    val glyphLevel = glyphInput?.value?.toIntOrNull() ?: 1
    val attempts = if (noDeathBonus?.checked == true) 4 else 3
    val pitLevelBonus = pitLevelBonusInput?.value?.toIntOrNull() ?: 10
    val pitLevel = glyphLevel + pitLevelBonus


    if (glyphLevel < 1) {
        output?.textContent = "Please enter a valid glyph level (1 or higher)."
        return
    }

    val finalGlyphLevel = glyphLevel + attempts + (pitLevelBonus / 10 - 1)
    lastFinalGlyphLevel = finalGlyphLevel
    val requiredPit = max(finalGlyphLevel + 10, pitLevel) //finalGlyphLevel + pitLevel

    output?.innerHTML = "<strong>Minimum Pit Level: $requiredPit</strong><br>" +
            "Glyph level after run: <strong>$finalGlyphLevel</strong>"

    result?.innerHTML = "<strong>$finalGlyphLevel</strong>"
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
