package com.example.finalapplication

data class ItemRetrofitModel(
    var gugun: String? = null,
    var facility_type: String? = null,
    var facility_name: String? = null,
    var road_address: String? = null,
    var tel: String? = null,
)

data class MyItem(val item:MutableList<ItemRetrofitModel>)
data class MyItems(val items:MyItem)
data class MyModel(val body:MyItems)
data class MyBody(val getTblDisabledFac:MyModel)