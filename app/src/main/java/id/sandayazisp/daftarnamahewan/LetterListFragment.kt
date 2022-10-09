/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.sandayazisp.daftarnamahewan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.sandayazisp.daftarnamahewan.LetterAdapter
import id.sandayazisp.daftarnamahewan.R
import id.sandayazisp.daftarnamahewan.databinding.FragmentLetterListBinding

/**
 * membuat class letterlistfragment
 */
class LetterListFragment : Fragment() {

    // kode dibawah kita membuat view binding yang digunakan sebagai refrensi ke FragmentLetterListBinding dan memiliki nilai null
    private var _binding: FragmentLetterListBinding? = null

    // kode dibawah kita membuat kode binding yang memiliki properti get(). properti get() digunakan untuk mendapatkan nilai
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    // kode dibawah digunakan untuk melacak layout yang akan digunakan di recyclerview
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // kode dibawah digunakan untuk meng-inflate tampilan, menyetel nilai _binding, dan menampilkan tampilan root.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // kode dibawah digunakan untuk mengambil layout untuk fragment
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        // kode dibawah digunakan untuk mengatur layout dari recyclerview
        // dan saat pertama menjalankan kali menjalankan aplikasi akan menjadi layout
        chooseLayout()
    }

    /**
     * kode dibawah digunakan untuk menyetel ulang properti _binding ke null karena tampilan sudah tidak ada.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    /**
     * kode dibawah digunakan untuk mengatur layout untuk recyclerview berdasarkan daftar yang diinginkan
     */
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    /**
     * kode dibawah digunakan untuk menentukan pilihan dengan menu item yang dipilih
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {

                isLinearLayoutManager = !isLinearLayoutManager

                chooseLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
