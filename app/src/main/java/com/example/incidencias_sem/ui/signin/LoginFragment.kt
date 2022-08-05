package com.example.incidencias_sem.ui.signin



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.incidencias_sem.R
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.databinding.FragmentLoginBinding
import com.example.incidencias_sem.ui.activities.Navigation_Drawer
import com.example.incidencias_sem.ui.viewModels.UsersViewModels
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController



        val data = arguments?.let {
            LoginFragmentArgs.fromBundle(it).data
        }
        data.let {
            binding.mainTieUser.setText(it?.get(0))
            binding.mainTiePassword.setText(it?.get(1))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainTiePassword.setOnFocusChangeListener { _, hasFocus ->
            run {
                if (hasFocus) {
                    binding.mainTilPassword.error = ""
                }
            }
        }
        binding.mainTiePassword.addTextChangedListener {

            val size = it!!.length
            if (size < 5) {
                binding.mainTilPassword.error = "Caracteres $size/5"
            } else {
                binding.mainTilPassword.error = ""
            }
        }
        binding.mainTiePassword.setOnClickListener {
            val textInputEditText = it as TextInputEditText// Casteo de la variable
            //Recuperamos el texto del edittext lo convertimos a string y de este String comprobamos su longitud
            val size = textInputEditText.text.toString().length
            if (size < 5) {
                binding.mainTilPassword.error = "Caracteres $size/5"
            } else {
                binding.mainTilPassword.error = ""
            }

        }
        binding.mainBtnLogin.setOnClickListener {
            val email = binding.mainTieUser
            val editPassword = binding.mainTiePassword
            val password = editPassword.text


            if (password.isNullOrBlank()) {
                binding.mainTilPassword.error = getString(R.string.error_blank_pasword)
                return@setOnClickListener
            }
            if (email.text.isNullOrBlank()) {

                return@setOnClickListener
            }
            val userViewModel: UsersViewModels by viewModels()
            userViewModel.login(email.text.toString()).observe(viewLifecycleOwner) {
                if (it != null) {

                    if (it.password == password.toString()) {
                        App.saveUser(it.id, it.name, true)
                        val intent = Intent(requireContext(), Navigation_Drawer::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                        Toast.makeText(
                            requireContext(),
                            "Bienvenido ${it.name}\t ${it.surname}",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "El usuario/contraseña no es correcto",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "El usuario/contraseña no es correcto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        binding.mainBtnRegistrate.setOnClickListener {
            val actions = LoginFragmentDirections.toRegisterFragment()
            Navigation.findNavController(view)
                .navigate(actions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}











