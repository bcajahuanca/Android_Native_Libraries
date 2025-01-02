package com.example.dialoglibrary

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.dialoglibrari.databinding.CustomSimpleDialogBinding
import com.example.dialoglibrari.databinding.CustomListDialogBinding

object DialogManager {

    fun showSimpleDialog(context: Context, title: String, message: String) {
        val binding = CustomSimpleDialogBinding.inflate(LayoutInflater.from(context))
        binding.dialogTitle.text = title
        binding.dialogDescription.text = message

        AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
            .show()
    }

    fun showListDialog(context: Context, title: String, items: List<String>, itemClickListener: (String) -> Unit) {
        val binding = CustomListDialogBinding.inflate(LayoutInflater.from(context))
        binding.dialogTitle.text = title
        binding.dialogList.adapter = ArrayAdapter(context, R.layout.simple_list_item_1, items)

        val dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding.dialogList.setOnItemClickListener { _, _, position, _ ->
            itemClickListener(items[position])
            dialog.dismiss()
        }

    }
}
