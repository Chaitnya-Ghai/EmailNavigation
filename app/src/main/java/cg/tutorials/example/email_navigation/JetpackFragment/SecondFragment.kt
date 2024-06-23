package cg.tutorials.example.email_navigation.JetpackFragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import cg.tutorials.example.email_navigation.R
import cg.tutorials.example.email_navigation.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var email:String?=null
    var otp :String?=null
private lateinit var binding: FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString("email")?:""
            otp = it.getString("otp")?:""
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.tvEmail.setText(email)
        binding.otpEt1.doOnTextChanged { _, _, _, _ ->
            val enteredText = binding.otpEt1.text.toString()
            if (enteredText.length==1){
                binding.otpEt2.requestFocus()
            }
        }
        binding.otpEt2.doOnTextChanged { _, _, _, _ ->
            val enteredText = binding.otpEt2.text.toString()
            if (enteredText.length==1){
                binding.otpEt3.requestFocus()
            }
        }
        binding.otpEt3.doOnTextChanged { _, _, _, _ ->
            val enteredText = binding.otpEt3.text.toString()
            if (enteredText.length==1){
                binding.otpEt4.requestFocus()
            }
        }
        binding.otpEt4.doOnTextChanged { _, _, _, _ ->
            val enteredText = binding.otpEt4.text.toString()
            if (enteredText.length==1){
                binding.otpEt4.requestFocus()
            }
        }

        binding.checkBtn.setOnClickListener {
            if (binding.otpEt1.text.toString() == otp!![0].toString() &&
                (binding.otpEt2.text.toString() == otp!![1].toString())&&
                (binding.otpEt3.text.toString() == otp!![2].toString())&&
                (binding.otpEt4.text.toString() == otp!![3].toString())
                ) {
                findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
            }
            else{
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Invalid Otp")
                    setCancelable(false)
                    setPositiveButton("OK"){_,_ ->
                        setCancelable(true)
                    }
                show()}
                Toast.makeText(requireContext(), "otp missMatch", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}