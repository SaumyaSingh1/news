package app.dheeraj.mynews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter=Pageradapter(supportFragmentManager)
    }


 class Pageradapter (fm :FragmentManager?): FragmentPagerAdapter(fm) {
     override fun getItem(positon: Int): Fragment? {
         when(positon){
             0 ->  {return NewsFragment.Newinstance("sports")}
             1 -> {return  NewsFragment.Newinstance("technology")}
             2 -> {return NewsFragment.Newinstance("entertainment")}

         }
            return  null

     }

     override fun getCount() =3
 }
}
