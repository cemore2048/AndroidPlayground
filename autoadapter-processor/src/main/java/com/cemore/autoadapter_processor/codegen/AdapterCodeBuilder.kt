package com.cemore.autoadapter_processor.codegen

import com.cemore.autoadapter_processor.models.ModelData
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class AdapterCodeBuilder(
    private val adapterName: String,
    private val data: ModelData
) {

    private val viewHolderName = "ViewHolder"
    private val viewHolderClassName = ClassName(data.packageName, viewHolderName)
    private val viewHolderQualifiedClassName = ClassName(
        data.packageName,
        "$adapterName.$viewHolderName"
    )
    private val modelClassName = ClassName(data.packageName, data.modelName) // 4
    private val itemsListClassName = ClassName("kotlin.collections", "List") // 5
        .parameterizedBy(modelClassName)
    private val textViewClassName = ClassName("android.widget", "TextView")

    fun build(): TypeSpec = TypeSpec.classBuilder(adapterName)
        .primaryConstructor(
            FunSpec.constructorBuilder().addParameter("items", itemsListClassName).build()
        )
        .superclass(ClassName("androidx.recyclerView.widget.RecyclerView", "Adapter")
            .parameterizedBy(viewHolderQualifiedClassName))
        .addProperty(PropertySpec.builder("items", itemsListClassName)
            .addModifiers(KModifier.PRIVATE)
            .initializer("items")
            .build()
        )
        .build()

}