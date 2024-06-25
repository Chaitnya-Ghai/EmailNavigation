package cg.tutorials.example.email_navigation.JetpackFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cg.tutorials.example.email_navigation.MainActivity
import cg.tutorials.example.email_navigation.R
import cg.tutorials.example.email_navigation.databinding.FragmentFirstBinding

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
    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentFirstBinding
    private val tag = "FirstFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFrag1.setOnClickListener {
            val regex = Regex("[a-z]@gmail\$")
            val isMatch = regex.containsMatchIn(binding.etFragment.text.toString())
            if(isMatch){
                val randomFourDigitNo : Int=(1000..9999).random()
                println("$randomFourDigitNo")
                Log.e(tag, " randomFourDigitNo $randomFourDigitNo")
                val bundle = Bundle()
                bundle.putString("email", binding.etFragment.text?.toString())
                bundle.putString("otp", "$randomFourDigitNo")
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)
            }
           else
                binding.etFragment.error = resources.getString(R.string.enter_your_email)
                Toast.makeText(requireContext(), "Enter Valid email", Toast.LENGTH_SHORT).show()
            }
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
