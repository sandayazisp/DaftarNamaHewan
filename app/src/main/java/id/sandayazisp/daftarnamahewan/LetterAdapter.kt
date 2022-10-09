package id.sandayazisp.daftarnamahewan

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

/**
 * membuat class adaptor untuk recyclerview dan letterviewholder
 */
class LetterAdapter :
    RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    // Kode dibawah digunakan untuk menghasilkan huruf dari 'A' sampai 'Z' dan mengubahnya menjadi daftar
    private val list = ('A').rangeTo('Z').toList()

    /**
     * kode dibawah digunakan untuk menampilkan item dalam daftar
     */
    class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * kode dibawah digunakan untuk membuat tampilkan baru dari R.layout.item_view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        // kode dibawah digunakan untuk membatu aksesibilitas untuk mengatur pembacaan teks
        layout.accessibilityDelegate = Accessibility
        return LetterViewHolder(layout)
    }

    /**
     * kode dibawah digunakan untuk mengganti tampilan dengan data yang baru
     */
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()

        // kode dibawah digunakan untuk membuat fungsi tombol yang jika ditekan akan menampilkan viewholder
        holder.button.setOnClickListener {
            // kode dibawah kita membuat variabel dengan nama action yang mempunyai value aksi dari wordlistfragment ke letterlistfragment
            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(letter = holder.button.text.toString())
            holder.view.findNavController().navigate(action)
        }
    }

    // kode dibawah digunakan untuk menyiapkan aksesibilitas untuk mengatur teks yang dibaca
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfo
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // kode dibawah kita membuat variabel dengan nama customString dan CustomClik
            // jika variabel customclik diklik dua kali akan menjalankan variabel customstring dan nilai accesibility bernilai null
            val customString = host.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }
}