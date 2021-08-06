package com.example.androidplayground.ignore

import com.cemore.autoadapter_annotations.AdapterModel
import com.cemore.autoadapter_annotations.ViewHolderBinding
import com.example.androidplayground.R

@AdapterModel(R.layout.person_item)
data class Person(
    @ViewHolderBinding(R.id.name) val name: String,
    @ViewHolderBinding(R.id.address) val address: String
)