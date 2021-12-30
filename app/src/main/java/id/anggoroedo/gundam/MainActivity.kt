package id.anggoroedo.gundam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.anggoroedo.gundam.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    lateinit var gundamAdapter: GundamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView() {
        gundamAdapter = GundamAdapter(arrayListOf(), object : GundamAdapter.OnAdapterListener {
            override fun onClick(result: MainModel.Result) {
                Toast.makeText(applicationContext, result.title,
                    Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = gundamAdapter
        }
    }

    private fun getDataFromApi(){

        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel> {
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        showData( response.body()!! )
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    printLog( "onFailure: $t" )
                }
            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData(data: MainModel){
        val results = data.result
        gundamAdapter.setData( results )
    }
}