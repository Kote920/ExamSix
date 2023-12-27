package com.example.examsix.di

import com.example.examsix.domain.Item
import com.example.examsix.domain.Type
import javax.inject.Inject

class StaticDataProvider @Inject constructor() {

    fun getStaticData(): MutableList<Item> {
        return mutableListOf(Item(1, 1, Type.NUMBER),
            Item(2, 2, Type.NUMBER),
            Item(3, 3, Type.NUMBER),
            Item(4, 4, Type.NUMBER),
            Item(5, 5, Type.NUMBER),
            Item(6, 6, Type.NUMBER),
            Item(7, 7, Type.NUMBER),
            Item(8, 8, Type.NUMBER),
            Item(9, 9, Type.NUMBER),
            Item(10, null, Type.FINGERPRINT),
            Item(11, 0, Type.NUMBER),
            Item(12, null, Type.REMOVE),)
    }

}