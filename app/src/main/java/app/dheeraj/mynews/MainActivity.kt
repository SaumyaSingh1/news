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
        val fragmentlist=  arrayListOf<Fragment>()
        fragmentlist.add(NewsFragment())
        viewPager.adapter=Pageradapter( fragmentlist ,supportFragmentManager)

    }


 class Pageradapter ( val fragment: ArrayList< Fragment> ,fm :FragmentManager?): FragmentPagerAdapter(fm) {

     override fun getItem(positon: Int): Fragment? {
    return fragment.get(positon)
     }

     override fun getCount() = fragment.size
 }
}
