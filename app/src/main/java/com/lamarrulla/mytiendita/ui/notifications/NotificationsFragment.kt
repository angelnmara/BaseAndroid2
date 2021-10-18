package com.lamarrulla.mytiendita.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.lamarrulla.mytiendita.R
import com.lamarrulla.mytiendita.api.data_source.firebase.DsLoginFirebase
import com.lamarrulla.mytiendita.api.repo_imp.firebase.RepoLoginFirebaseImpl
import com.lamarrulla.mytiendita.databinding.FragmentNotificationsBinding
import com.lamarrulla.mytiendita.utils.VMFatory

class NotificationsFragment : Fragment() {

    private val TAG = javaClass.name

    private val notificationsViewModel by viewModels<NotificationsViewModel>() {
        VMFatory(RepoLoginFirebaseImpl(DsLoginFirebase(this.requireActivity())))
    }
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var logoutButton:Button

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)*/

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textNotifications
        logoutButton = binding.btnLogout
        loadingProgressBar = binding.loading

        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val navController = this.findNavController()

        notificationsViewModel.logout.observe(viewLifecycleOwner, Observer {
            result ->
            result.error?.let {
                Log.d(TAG, result.error.toString())
                loadingProgressBar.visibility = View.GONE
            }
            result.success?.let {
                Log.d(TAG, "Usuario deslogeado")
                loadingProgressBar.visibility = View.GONE
                navController.popBackStack(R.id.mobile_navigation, true)
                navController.navigate(R.id.loginFragment)
            }
        })


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            notificationsViewModel.getLogout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}