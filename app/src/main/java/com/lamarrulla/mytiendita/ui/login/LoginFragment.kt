package com.lamarrulla.mytiendita.ui.login

import android.content.ContentValues.TAG
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.lamarrulla.mytiendita.databinding.FragmentLoginBinding

import com.lamarrulla.mytiendita.R
import com.lamarrulla.mytiendita.api.data_source.firebase.DsLoginFirebase
import com.lamarrulla.mytiendita.api.repo_imp.firebase.RepoLoginFirebaseImpl
import com.lamarrulla.mytiendita.ui.menu.MenuViewModel
import com.lamarrulla.mytiendita.utils.Utils
import com.lamarrulla.mytiendita.utils.VMFatory

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    private val menuViewModel by viewModels<MenuViewModel>(){
        VMFatory(RepoLoginFirebaseImpl(DsLoginFirebase(this.requireActivity())))
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //utils.fullScreen(this.requireActivity().window)
        val utils = Utils(this.requireContext())
        utils.fullScreen(this.requireActivity().window)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(
            this.requireActivity()
        )).get(LoginViewModel::class.java)

        menuViewModel.getValidaLogin()

        menuViewModel.validaLogin.observe(this.requireActivity(), Observer { result ->
            result ?: return@Observer
            result.error?.let {
                Log.d(TAG, result.error.toString())
                //navController.popBackStack(R.id.mobile_navigation, true)
                //navController.navigate(R.id.loginFragment)

            }
            result.success?.let {
                if(it.displayName==""){
                    val action = LoginFragmentDirections.actionLoginFragmentToCreateUserFragment()
                    Navigation.findNavController(this.requireView()).navigate(action);
                }
                Log.d(TAG, "usuario se logeo correctamente")
                Log.d(TAG, it.displayName.toString());
            }
        })

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading
        val register = binding.txtRegister

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                register.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        loginViewModel.registerResult.observe(viewLifecycleOwner, Observer {
            registerResult ->
            registerResult?: return@Observer
            loadingProgressBar.visibility = View.GONE
            registerResult.error?.let {
                showLoginFailed(it)
            }
            registerResult.success?.let {
                updateRegisterOk(it)
            }
        }
        )

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
        register.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.register(usernameEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
        loginViewModel.registerResult
        val action:NavDirections =
        if(model.displayName == "null"){
            LoginFragmentDirections.actionLoginFragmentToCreateUserFragment()
        }else{
            LoginFragmentDirections.actionLoginFragmentToMobileNavigation()
        }
        Navigation.findNavController(this.requireView()).navigate(action);
    }

    private fun updateRegisterOk(model: LoggedInUserView){
        //val appContext = context?.applicationContext?: return
        val action = LoginFragmentDirections.actionLoginFragmentToCreateUserFragment()
        Navigation.findNavController(this.requireView()).navigate(action);
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}