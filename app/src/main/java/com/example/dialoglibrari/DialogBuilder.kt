package com.example.dialoglibrari

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.icu.text.CaseMap.Title
import android.icu.text.ListFormatter.Width
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.dialoglibrari.databinding.CustomListDialogBinding
import com.example.dialoglibrari.databinding.CustomSimpleDialogBinding
import com.example.dialoglibrari.R.drawable.dialog_background
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import org.w3c.dom.Text

class DialogBuilder(private val context: Context) {
    private var title: String? = null
    private var titleSize: Float? = null

    private var description: String? = null
    private var positiveButtonText: String? = null
    private var negativeButtonText: String? = null
    private var positiveButtonClickListener: (() -> Unit)? = null
    private var negativeButtonClickListener: (() -> Unit)? = null
    private var listItems: List<String>? = null
    private var dialog: AlertDialog? = null

    private var backgroundColor:Int? = null
    private var backgroundBorderColor: Int? = null
    private var backgroundBorderWidth: Int? = null

    fun setTitleStyle(title: String?, size: Float?) = apply {
        if (!title.isNullOrEmpty()){
            this.title = title
        }
        if (size != null) {
            this.titleSize = size
        }
    }
    fun setDescription(description: String) = apply { this.description = description }
    fun setPositiveButton(text: String, listener: () -> Unit) = apply{
        this.positiveButtonText = text
        this.positiveButtonClickListener = listener
    }
    fun setNegativeButton(text: String, listener: () -> Unit) = apply{
        this.negativeButtonText = text
        this.negativeButtonClickListener = listener
    }
    fun setListItems(items: List<String>) = apply { this.listItems = items }


    fun setBackgroundColor(colorBackground: String? = null, colorBorder: String? = null, borderWidth: Int? = null) = apply {
        if (colorBackground != null) {
            this.backgroundColor = Color.parseColor(colorBackground)
        }
        if (colorBorder != null) {
            this.backgroundBorderColor = Color.parseColor(colorBorder)
        }
        if (borderWidth != null) {
            this.backgroundBorderWidth = borderWidth
        }
    }

    fun build() {
        if (listItems.isNullOrEmpty()) {
            buildSimpleDialog()
        } else {
            buildListDialog()
        }
    }

    fun setStyleBackground(bindingList: CustomListDialogBinding?, bindingSimple: CustomSimpleDialogBinding?){
        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.dialog_background)
        if (backgroundDrawable is GradientDrawable) {
            backgroundColor?.let {
                backgroundDrawable.setColor(it)
                backgroundBorderColor?.let { it1 -> backgroundBorderWidth?.let { it2 ->
                    backgroundDrawable.setStroke(
                        it2, it1)
                } }
                if (bindingSimple != null) {
                    bindingSimple.root.background = backgroundDrawable
                } else if (bindingList != null) {
                    bindingList.root.background = backgroundDrawable
                }
            }
        }
    }



    private fun buildSimpleDialog() {
        val binding = CustomSimpleDialogBinding.inflate(LayoutInflater.from(context))
        binding.dialogTitle.text = title?: "Default Title"
        binding.dialogDescription.text = description?: "Default description, please set your own description"

        setStyleBackground(null,binding)

        dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        binding.dialogTitle.textSize = titleSize ?: 8F
        binding.dialogButton.text = positiveButtonText ?: "Aceptar"
        binding.dialogButton.setOnClickListener {
            positiveButtonClickListener?.invoke()
        }
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()
    }

    private fun buildListDialog() {
        val binding = CustomListDialogBinding.inflate(LayoutInflater.from(context))
        binding.dialogTitle.text = title?: "Default Title"
        binding.dialogDescription.text = description?: "Default description, please set your own description"

        setStyleBackground(binding,null)

        listItems?.let {
            val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, it)
            binding.dialogList.adapter = adapter
            if (adapter.count > 4) {
                 val itemView = adapter.getView(0, null, binding.dialogList)
                itemView.measure(0,0)
                val itemHeight = itemView.measuredHeight

                val maxHeight = (4.3 * itemHeight).toInt()
                binding.dialogList.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    maxHeight
                )
            }
        }

        binding.dialogList.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = listItems?.get(position)
            Toast.makeText(context, "Empresa seleccionada: $selectedItem", Toast.LENGTH_LONG).show()
        }

        dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        binding.dialogButtonYes.text = positiveButtonText ?: "Aceptar"
        binding.dialogButtonYes.setOnClickListener {
            positiveButtonClickListener?.invoke()
        }

        if (negativeButtonText != null) {
            binding.dialogButtonNo.text = negativeButtonText
            binding.dialogButtonNo.setOnClickListener {
                negativeButtonClickListener?.invoke()
            }
            binding.dialogButtonNo.visibility = View.VISIBLE
        } else {
            binding.dialogButtonNo.visibility = View.GONE
        }
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()
    }
    fun dismissDialog(){
        dialog?.dismiss()
    }
}
