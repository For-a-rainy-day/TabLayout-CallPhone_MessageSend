package com.example.classwork17

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.fragment_second.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    lateinit var root:View
    lateinit var adapter: RvAdapter
    lateinit var list: ArrayList<RvModel>


    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_first, container, false)
        list = ArrayList()

//animatsiya
  //        val constraintLayout = view?.findViewById<ConstraintLayout>(R.id.layout_cons)
        val animationDrawable = root.layout_cons.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(3000)
        animationDrawable.start()



        root.btn1.setOnClickListener {

            val name = edt1.text.toString().trim()
            val number = edt2.text.toString().trim()

            if (name != "" && number != "" ) {
                val list = MySharedPreference.objectString
                list.add(RvModel(name, number))
                MySharedPreference.objectString = list
                Toast.makeText(root.context, "Save", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, "Ma'lumot yetarli emas", Toast.LENGTH_SHORT).show()
            }

        }
        MySharedPreference.init(root.context)




    return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}