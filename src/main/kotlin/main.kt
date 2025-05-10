import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement

fun main() {
    // Initial calculation
    updateResults()
    updateStoneCost()

    (document.getElementById("glyphLevel") as? HTMLInputElement)?.addEventListener("input", { updateResults() })
    (document.getElementById("adjustGlyphLevelAdd") as? HTMLButtonElement)?.addEventListener("click", { adjustGlyphLevel(1) })
    (document.getElementById("adjustGlyphLevelSub") as? HTMLButtonElement)?.addEventListener("click", { adjustGlyphLevel(-1) })
    (document.getElementById("resetGlyphLevel") as? HTMLButtonElement)?.addEventListener("click", { resetGlyphLevel() })
    (document.getElementById("noDeathBonus") as? HTMLInputElement)?.addEventListener("change", { updateResults() })
    (document.getElementById("calcPlus20") as? HTMLInputElement)?.addEventListener("change", { updateResults() })
    (document.getElementById("applyFinalLevel") as? HTMLButtonElement)?.addEventListener("click", { applyFinalLevel() })

    (document.getElementById("currentLevel") as? HTMLInputElement)?.addEventListener("input", { updateStoneCost() })
    (document.getElementById("desiredLevel") as? HTMLInputElement)?.addEventListener("input", { updateStoneCost() })
    (document.getElementById("setRange1To15") as? HTMLButtonElement)?.addEventListener("click", { setRange(1, 15) })
    (document.getElementById("setRange15To46") as? HTMLButtonElement)?.addEventListener("click", { setRange(15, 46) })
    (document.getElementById("setRange46To100") as? HTMLButtonElement)?.addEventListener("click", { setRange(46, 100) })
    (document.getElementById("stoneNoDeath") as? HTMLInputElement)?.addEventListener("change", { updateStoneCost() })
    (document.getElementById("pitPlus20") as? HTMLInputElement)?.addEventListener("change", { updateStoneCost() })
}
