package com.example.githubclient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener{
            presenter.counterClick(MainPresenter.FIRST_ITEM)
        }
        vb?.btnCounter2?.setOnClickListener{
            presenter.counterClick(MainPresenter.SECOND_ITEM)
        }
        vb?.btnCounter3?.setOnClickListener{
            presenter.counterClick(MainPresenter.THIRD_ITEM)
        }
    }

    override fun setButtonText(index: Int, text: String) {
        when(index){
            MainPresenter.FIRST_ITEM -> vb?.btnCounter1?.text = text
            MainPresenter.SECOND_ITEM -> vb?.btnCounter2?.text = text
            MainPresenter.THIRD_ITEM -> vb?.btnCounter3?.text = text
            else -> Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }
}