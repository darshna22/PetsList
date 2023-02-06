package com.my.sapiaassignment.utils

import com.my.sapiaassignment.datalayer.apimodel.PetItem

object FakeData {

    fun getPet(): PetItem =
        PetItem(
            image_url = "https://upload.wikimedia.org/wikipedia/commons/6/6d/Blue-and-Yellow-Macaw.jpg",
            title = "Parrot",
            content_url = "https://en.wikipedia.org/wiki/Parrot",
            date_added = "2018-07-26T04:20:16.027Z"
        )

}