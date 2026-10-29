package com.desh2403.bento_ds_compose.uikit.icon

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

/** Weight of a Phosphor icon. [suffix] is appended to the icon name in a key: `drop` / `drop-fill`. */
enum class PhosphorWeight(val suffix: String) {
    REGULAR(""),
    FILL("-fill"),
}

/** Categories of the Phosphor set, as in the `figma_category` of the package. Brand icons are not included. */
enum class PhosphorCategory {
    HEALTH,
    PEOPLE,
    NATURE,
    GAMES,
    MEDIA,
    EDUCATION,
    COMMERCE,
    TRAVEL,
    TIME,
    FINANCE,
    COMMUNICATION,
    OFFICE,
    DESIGN,
    SYSTEM,
    DEVELOPMENT,
    SECURITY,
    ARROWS,
}

/**
 * Icon of the [Phosphor](https://phosphoricons.com) set (MIT license), available in regular and fill weights.
 *
 * @param name icon name in kebab-case, e.g. `person-simple-run`.
 * @param category category of the icon.
 * @param tags space-separated English tags from the Phosphor package, for search.
 * @param regularRes drawable of the regular weight.
 * @param fillRes drawable of the fill weight.
 */
@Immutable
class PhosphorIcon(
    val name: String,
    val category: PhosphorCategory,
    val tags: String,
    @DrawableRes val regularRes: Int,
    @DrawableRes val fillRes: Int,
) {
    @DrawableRes
    fun res(weight: PhosphorWeight): Int = when (weight) {
        PhosphorWeight.REGULAR -> regularRes
        PhosphorWeight.FILL -> fillRes
    }

    fun key(weight: PhosphorWeight): String = name + weight.suffix
}

/** Icon in a specific weight. [key] is a stable string for persistence, parsed back by [PhosphorIcons.parse]. */
@Immutable
data class PhosphorIconRef(
    val icon: PhosphorIcon,
    val weight: PhosphorWeight,
) {
    val key: String get() = icon.key(weight)

    @get:DrawableRes
    val res: Int get() = icon.res(weight)
}

/**
 * Catalog of about 1,400 Phosphor icons in regular and fill weights.
 * Drawables are `R.drawable.ph_<name>` and `R.drawable.ph_<name>_fill`.
 *
 * Bento DS: [Icons](https://developer.bento-ds.com/Guides/icons). Bento DS ships its own icon set;
 * the Phosphor catalog is an extension of this library.
 */
object PhosphorIcons {

    /** All icons of the catalog. */
    val all: List<PhosphorIcon> by lazy { phosphorCatalog() }

    private val byName: Map<String, PhosphorIcon> by lazy { all.associateBy { it.name } }

    private val byCategory: Map<PhosphorCategory, List<PhosphorIcon>> by lazy {
        all.groupBy { it.category }
    }

    /** Icon by its kebab-case [name], or `null`. */
    operator fun get(name: String): PhosphorIcon? = byName[name]

    /** Icons of the [category]. */
    fun inCategory(category: PhosphorCategory): List<PhosphorIcon> =
        byCategory[category].orEmpty()

    /** Parses a key like `drop` or `drop-fill`. Returns `null` if the set has no such icon. */
    fun parse(key: String?): PhosphorIconRef? {
        if (key.isNullOrBlank()) return null
        val fillName = key.removeSuffix(PhosphorWeight.FILL.suffix)
        if (fillName != key) {
            byName[fillName]?.let { return PhosphorIconRef(it, PhosphorWeight.FILL) }
        }
        return byName[key]?.let { PhosphorIconRef(it, PhosphorWeight.REGULAR) }
    }
}
