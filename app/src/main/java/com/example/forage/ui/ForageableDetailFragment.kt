/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.forage.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forage.R
import com.example.forage.databinding.FragmentForageableDetailBinding
import com.example.forage.model.Forageable
import com.example.forage.ui.viewmodel.ForageableViewModel

/**
 * A fragment to display the details of a [Forageable] currently stored in the database.
 * The [AddForageableFragment] can be launched from this fragment to edit the [Forageable]
 */
class ForageableDetailFragment : Fragment() {

    private val navigationArgs: ForageableDetailFragmentArgs by navArgs()

    // TODO: Refactor the creation of the view model to take an instance of
    //  ForageableViewModelFactory. The factory should take an instance of the Database retrieved
    //  from BaseApplication
    private val viewModel: ForageableViewModel by activityViewModels()

    private lateinit var forageable: Forageable

    private var _binding: FragmentForageableDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForageableDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        // TODO: Observe a forageable that is retrieved by id, set the forageable variable,
        //  and call the bind forageable method
    }

    private fun bindForageable() {
        binding.apply {
            name.text = forageable.name
            location.text = forageable.address
            notes.text = forageable.notes
            if (forageable.inSeason) {
                season.text = getString(R.string.in_season)
            } else {
                season.text = getString(R.string.out_of_season)
            }
            editForageableFab.setOnClickListener {
                val action = ForageableDetailFragmentDirections
                    .actionForageableDetailFragmentToAddForageableFragment(forageable.id)
                findNavController().navigate(action)
            }

            location.setOnClickListener {
                launchMap()
            }
        }
    }

    private fun launchMap() {
        val address = forageable.address.let {
            it.replace(", ", ",")
            it.replace(". ", " ")
            it.replace(" ", "+")
        }
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}
