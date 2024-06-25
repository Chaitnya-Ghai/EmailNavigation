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
    private var email: String? = null
    private var otp: String? = null
    private lateinit var binding: FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString("email") ?: ""
            otp = it.getString("otp") ?: ""
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    private fun emailWithNo(email:String,num:String): String {
        val atIndex = email.indexOf('@')
        return if (atIndex != -1) {
            val localPart = email.substring(0, atIndex)
            val domainPart = email.substring(atIndex)
            "$localPart$num$domainPart"
        } else {
            return email
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvEmail.text= emailWithNo(email.toString(),otp.toString())
        binding.apply {
            otpEt1.doOnTextChanged {  _, _, _, _ ->
                val enteredText = otpEt1.text.toString()
                if (enteredText.isNotEmpty()) {
                    otpEt2.requestFocus()
                }
            }
            otpEt2.doOnTextChanged {  text, _, _, _ ->
                if (!text.isNullOrBlank()) {
                   otpEt3.requestFocus()
                }
                else {
                    otpEt1.requestFocus()
                }
            }
            otpEt3.doOnTextChanged {  text, _, _, _ ->
                if (!text.isNullOrBlank()) {
                    otpEt4.requestFocus()
                }
                else {
                   otpEt2.requestFocus()
                }
            }
           otpEt4.doOnTextChanged {  text, _, _, _ ->
                if (!text.isNullOrBlank()) {
                    checkBtn.requestFocus()
                }
                else {
                    otpEt3.requestFocus()
                }
            }
        }
        binding.checkBtn.setOnClickListener {
            if (binding.otpEt1.text.toString() == otp!![0].toString() &&
                (binding.otpEt2.text.toString() == otp!![1].toString()) &&
                (binding.otpEt3.text.toString() == otp!![2].toString()) &&
                (binding.otpEt4.text.toString() == otp!![3].toString())
            ) {
                findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
            } else {
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Invalid Otp")
                    setCancelable(false)
                    setPositiveButton("OK") { _, _ ->
                        setCancelable(true)
                    }
                    show()
                }
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