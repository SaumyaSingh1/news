package app.dheeraj.mynews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_layout.*
import okhttp3.*
import java.io.IOException

class NewsFragment : Fragment() {
    var newslist = arrayListOf<News>()
    private  val okHttpClient=OkHttpClient.Builder().build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcNews.layoutManager = LinearLayoutManager(requireContext())
        val newsadapter = NewsAdapter(newslist)
        rcNews.adapter = newsadapter

        // val endpoint = arguments?.getString("Endpoint")
        val url =
            "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=21bb241788a24064917fffc087581b4c"


        val gson = Gson()
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                val responsebody = response.body()?.string()
                val parsedobject = gson.fromJson(responsebody, Newsresponse::class.java)
                newslist.addAll(parsedobject.articles)
                //  Log.e("tag",parsedobject.title)
                activity?.runOnUiThread {
                    newsadapter.notifyDataSetChanged()
                }
            }
        })

    }
}