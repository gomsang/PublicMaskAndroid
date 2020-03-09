package com.gomsang.lab.publicmask.libs.datas

import android.os.Parcelable
import com.gomsang.lab.publicmask.libs.datas.mask.Store
import kotlinx.android.parcel.Parcelize

@Parcelize
class Stock(
    var dealerName: String? = null,
    var dealerAddress: String? = null,
    var dealerLatitude: Double? = null,
    var dealerLongitude: Double? = null,
    var dealerType: String? = null,
    var updatedTime: String? = null,
    var stockTime: String? = null,
    var remainStat: String? = null
    /* var stockTotalCount: Int = 0,
     var soldCount: Int = 0,
     var isClosed: Boolean = false*/
) : Parcelable {

    companion object {
        fun convert(store: Store): Stock {
            val stock = Stock()
            stock.dealerName = store.name
            stock.dealerAddress = store.addr
            stock.dealerLatitude = store.lat
            stock.dealerLongitude = store.lng
            stock.dealerType = store.type
            stock.updatedTime = store.createdAt
            stock.stockTime = store.stockAt
            stock.remainStat = store.remainStat
            /* Deprecated
            stock.stockTime = store.stockT
            stock.stockTotalCount = store.stockCnt
            stock.soldCount = store.soldCnt
            stock.remainCount = store.remainCnt
            stock.isClosed = store.soldOut*/
            return stock
        }
    }
}