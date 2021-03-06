package com.bcebhagalpur.welcomeslider.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_class12thor11th.view.*
import kotlinx.android.synthetic.main.fragment_class6th_to10th.*
import kotlinx.android.synthetic.main.fragment_class6th_to10th.view.*
import kotlinx.android.synthetic.main.fragment_class6th_to10th.view.btnStartLearning
import kotlinx.android.synthetic.main.fragment_class6th_to10th.view.txtCBSE
import kotlinx.android.synthetic.main.fragment_class6th_to10th.view.txtICSE
import kotlinx.android.synthetic.main.fragment_class6th_to10th.view.txtSTATE

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Claas6thTo10thFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Claas6thTo10thFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var studentBoard:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_class6th_to10th, container, false)
        view.txtCBSE.setOnClickListener(){
            studentBoard = txtCBSE.text.toString()
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtICSE.setOnClickListener(){
            studentBoard = txtICSE.text.toString()
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtSTATE.setOnClickListener(){
            studentBoard = txtSTATE.text.toString()
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        var studentClass= (activity as ChooseClassActivity?)?.getResult()

        view.btnStartLearning.setOnClickListener(){
            try {
                val intent = Intent(activity, RegistrationActivity::class.java)
                intent.putExtra("studentClass",studentClass )
                intent.putExtra("studentBoard", studentBoard)
                startActivity(intent)
            }catch (e:UninitializedPropertyAccessException){
                Toast.makeText(context,"Please select Board",Toast.LENGTH_SHORT).show()
            }

        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Claas6thTo10thFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Claas6thTo10thFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}