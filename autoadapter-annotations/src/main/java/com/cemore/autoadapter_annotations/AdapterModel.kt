package com.cemore.autoadapter_annotations

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class AdapterModel(val layoutId: Int) {
}