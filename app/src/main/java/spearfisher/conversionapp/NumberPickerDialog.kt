package spearfisher.conversionapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class NumberPickerDialog: DialogFragment(), NumberPicker.OnValueChangeListener  {

    private lateinit var listener: NoticeDialogListener // 親に渡すためのリスナー定義
    private var selectedItem: Int = 0 // 選択したアイテム格納

    interface NoticeDialogListener {
        fun onNumberPickerDialogPositiveClick(dialog: DialogFragment, selectedItem: Int)
        fun onNumberPickerDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        Log.d("DebugLog", "onAttach")
        super.onAttach(context)
        try {
            this.listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.number_picker_dialog, null)!!
        val builder = AlertDialog.Builder(context)

        // Dialogの設定
        builder.setView(dialogView)
        builder.setPositiveButton("OK") { _, _ ->
            this.listener.onNumberPickerDialogPositiveClick(this, this.selectedItem) //
        }
        builder.setNegativeButton("CANCEL") { _, _ ->
            this.listener.onNumberPickerDialogNegativeClick(this)
        }

        // NumberPickerの設定
        val np = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        np.setOnValueChangedListener(this)
        np.minValue = 1920  // NumberPickerの最小値設定
        np.maxValue = 2030 // NumberPickerの最大値設定
        np.value = 1980     // NumberPickerの初期値
        this.selectedItem = 1980

        return builder.create()
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        this.selectedItem = newVal
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}