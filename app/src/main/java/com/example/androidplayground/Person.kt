package com.example.androidplayground

import com.cemore.autoadapter_annotations.AdapterModel
import com.cemore.autoadapter_annotations.ViewHolderBinding

@AdapterModel(R.layout.person_item)
data class Person(
    @ViewHolderBinding(R.id.name) val name: String,
    @ViewHolderBinding(R.id.address) val address: String
)