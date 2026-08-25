package com.example.limitlesstech.limitlessnews.core.util
fun getSourceLogoUrl(
    source: String
): String {

    return when (
        source
            .trim()
            .lowercase()
    ) {

        "cnbc" ->
            "https://www.google.com/s2/favicons?domain=cnbc.com&sz=128"

        "vice",
        "vice news" ->
            "https://www.google.com/s2/favicons?domain=vice.com&sz=128"

        "vox" ->
            "https://www.google.com/s2/favicons?domain=vox.com&sz=128"

        "bbc",
        "bbc news" ->
            "https://www.google.com/s2/favicons?domain=bbc.com&sz=128"

        "cnn" ->
            "https://www.google.com/s2/favicons?domain=cnn.com&sz=128"

        "usa today" ->
            "https://www.google.com/s2/favicons?domain=usatoday.com&sz=128"

        "time" ->
            "https://www.google.com/s2/favicons?domain=time.com&sz=128"

        "cnet" ->
            "https://www.google.com/s2/favicons?domain=cnet.com&sz=128"

        "msnbc" ->
            "https://www.google.com/s2/favicons?domain=msnbc.com&sz=128"

        "techcrunch" ->
            "https://www.google.com/s2/favicons?domain=techcrunch.com&sz=128"

        "the verge" ->
            "https://www.google.com/s2/favicons?domain=theverge.com&sz=128"

        "the next web" ->
            "https://www.google.com/s2/favicons?domain=thenextweb.com&sz=128"

        else -> ""
    }
}