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

class NewsFragment : Fragment()
{
    var newslist = arrayListOf<News>()
     companion object {
        fun Newinstance(Endpiont :String) : NewsFragment
        {   val newfragment = NewsFragment()

            val arguments =Bundle()
            arguments.putString("Endpoint",Endpiont)
            newfragment.arguments=arguments
            return newfragment
        }
     }
    val client : OkHttpClient =OkHttpClient()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       val endpoint = arguments?.getString("Endpoint")
        val url =
            "https://newsapi.org/v2/top-headlines?country=us&category= $endpoint&apiKey=201baf6282664af088ec46f6b95f6ecf"



        rcNews.layoutManager= LinearLayoutManager(requireContext())
        val newsadapter = NewsAdapter(newslist)
        rcNews.adapter=newsadapter


        val gson = Gson()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responsebody = response.body()?.string()
                val parsedobject = gson.fromJson(responsebody,Newsresponse::class.java)
                newslist.addAll(parsedobject.articles)
              //  Log.e("tag",parsedobject.title)
                activity?.runOnUiThread {
                    newsadapter.notifyDataSetChanged()
                }


            }
        })

    }
}