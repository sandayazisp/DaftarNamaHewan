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
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.sandayazisp.daftarnamahewan.databinding.FragmentWordListBinding

/**
 * membuat class WordListFragment untuk menampilkan kata dengan tombol pencarian untuk mencarinya.
 */
class WordListFragment : Fragment() {

    /**
     * kode dibawah digunakan untuk menyediakan akses global ke variabel-variabel dari aplikasi
     * melalui DetailListFragment tanpa perlu membuatnya
     */
    companion object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    // kode dibawah kita membuat view binding yang digunakan sebagai refrensi ke FragmentLetterListBinding dan memiliki nilai null
    private var _binding: FragmentWordListBinding? = null

    // kode dibawah kita membuat kode binding yang memiliki properti get(). properti get() digunakan untuk mendapatkan nilai
    private val binding get() = _binding!!

    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // kode dibawah digunakan untuk mengambil LETTER dari argument Fragment
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // kode dibawah digunakan untuk mengambil layout untuk fragment
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    /**
     * kode dibawah digunakan untuk menyetel ulang properti _binding ke null karena tampilan sudah tidak ada.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
