package com.example.my18application

// Json: JavaScript에서 데이터를 표현하는 방법, 웹에서 데이터를 주고 받을 때 주로 사용
// Gson: json을 JVA의 객체로 역직렬화, 직렬화 해주는 자바 라이브러리
// EX) body.items[item.rnum]

data class ItemRetrofitModel(
    var rnum: Long = 0,
    var productGb: String? = null,
    var prdlstNm: String? = null,
    var rawmtrl: String? = null,
    var capacity: String? = null,
    var imgurl1: String? = null
)

data class MyItem(val item:ItemRetrofitModel)
data class MyItems(val items:MutableList<MyItem>)
data class MyModel(val body:MyItems)