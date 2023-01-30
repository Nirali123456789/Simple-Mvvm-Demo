package com.example.myapplication.Repository

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Models.Category
import com.example.myapplication.RoomDatabase.CategoryDao
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONException
import org.json.JSONObject
import kotlinx.coroutines.flow.Flow

class CategoryRepo(private val foodItemDao: CategoryDao, var page: Int = 1) {

    val TAG = javaClass.simpleName
    var mutableList: MutableLiveData<List<Category>> = MutableLiveData()
    var arrayList: ArrayList<Category> = arrayListOf()
    val alFoodItems: Flow<MutableList<Category>> = foodItemDao.getFoodItems()
    var emptyResponse = false

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(foodItem: Category) {
        foodItemDao.insert(foodItem)
    }

    fun getresponse(): Boolean {
        return emptyResponse
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(foodItem: ArrayList<Category>) {
        foodItemDao.insertAll(foodItem)
    }

    fun InsertData(page: Int) {
        //   arrayList= arrayListOf()
        RetrofitCall(page)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getCallData() {
        Log.d(TAG, "getCallData: " + arrayList.size)


        if (arrayList.size == 0) {
            emptyResponse = true
           // var li:ArrayList<Category> = arrayListOf()
        //    li.add(Category())
        }else
        {
            foodItemDao.insertAll(arrayList)
        }
    }

    var baseUrl1 = "http://139.59.74.86/color_call_era/v1/list_theme"
    fun RetrofitCall(albumid: Int): RequestHandle? {


        val client: SyncHttpClient = SyncHttpClient()
        client.addHeader("id", App.context!!.packageName);
        val jsonObject1 = JSONObject()
        // val jsonObject2 = JSONObject()
        try {
            jsonObject1.put("page_no", albumid)
            jsonObject1.put("limit", 50)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Log.d("TAG", "RetrofitCall: " + App.context!!.packageName)
        val cryptLib = CryptLib()
        val rating = cryptLib.encryptPlainTextWithRandomIV(
            jsonObject1.toString(),
            App.context!!.getString(R.string.encryption_key)
        )

        val decryptedString = cryptLib.decryptCipherTextWithRandomIV(
            rating,
            App.context!!.resources.getString(R.string.encryption_key)
        )
        Log.d("TAG", "fetchCategory: " + decryptedString)
        val entity = StringEntity(rating)

        return client.post(
            App.context, baseUrl1,
            entity, "application/json", GetFeedbackUrl(App.context!!)
        )
    }

    inner class GetFeedbackUrl(activity1: Context) :
        AsyncHttpResponseHandler() {


        var activity = activity1
        override fun onStart() {
            super.onStart()
            arrayList = arrayListOf()
        }

        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            try {

                val response = String(responseBody!!)

                val cryptLib = CryptLib()
                val decryptedString = cryptLib.decryptCipherTextWithRandomIV(
                    response,
                    activity.resources.getString(R.string.encryption_key)
                )
                Log.d("TAG", "onSucces: " + decryptedString)
                // setbackList(decryptedString)

                var jsonObject: JSONObject = JSONObject(decryptedString)
                var jsonArray = jsonObject.getJSONArray("list")
                if (jsonArray.length() == 0) {
                    emptyResponse = true
                    arrayList = arrayListOf()
                    return
                } else {
                    emptyResponse = false
                }
                var FILE_BASE_URL = jsonObject.getString("FILE_BASE_URL")
                for (i in 0 until jsonArray.length()) {
                    var objectJson: JSONObject = jsonArray.getJSONObject(i)
                    var t_id = objectJson.getString("t_id")
                    var t_name = objectJson.getString("t_name")
                    var t_video = objectJson.getString("t_video")
                    var t_thumb = objectJson.getString("t_thumb")
                    var cat: Category = Category()
                    cat.t_video = FILE_BASE_URL + t_video
                    cat.t_name = t_name
                    cat.t_id = t_id
                    cat.t_thumb = FILE_BASE_URL + t_thumb
                    arrayList.add(cat)
                    //   Log.d("TAG", "onSucces: " + objectJson)
                }
                Log.d("TAG", "onSucces: " + arrayList.size)
                // GlobalScope.launch {
                //   mutableList.value = (arrayList)
                //}


            } catch (e: JSONException) {
                e.printStackTrace()
                Log.d("TAG", "fetchCategory: " + e.message)
                val cryptLib = CryptLib()
                val decryptedString = cryptLib.decryptCipherTextWithRandomIV(
                    e.message,
                    App.context!!.resources.getString(R.string.encryption_key)
                )
                Log.d("TAG", "fetchCategory: " + decryptedString)

            }


        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            try {
            } catch (Th: Exception) {

            }

        }
    }
}