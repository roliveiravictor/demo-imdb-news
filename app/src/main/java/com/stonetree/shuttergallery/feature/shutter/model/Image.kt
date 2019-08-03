package com.stonetree.shuttergallery.feature.shutter.model

data class Image(
    var id: Int = -1,
    var aspect: Float = -1f,
    var assets: Assets
) {
    constructor() : this(-1, -1f, Assets())
}
