package com.example.dialoglibrari

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.dialoglibrari.databinding.CustomListDialogBinding
import com.example.dialoglibrari.databinding.CustomSimpleDialogBinding

class DialogBuilder(private val context: Context) {
    private var title: String? = "Default title"
    private var titleSize: Float? = 14F
    private var titleColor: Int? = Color.BLACK

    private var description: String? = "Here will be your description, please set one"
    private var descriptionSize: Float? = 14F

    private var positiveButtonText: String? = "Yes"
    private var positiveButtonTextSize: Float? = 14F
    private var positiveButtonColor: Int? = Color.WHITE
    private var positiveButtonClickListener: (() -> Unit)? = null

    private var negativeButtonText: String? = null
    private var negativeButtonTextSize: Float? = 14F
    private var negativeButtonColor: Int? = Color.WHITE
    private var negativeButtonClickListener: (() -> Unit)? = null

    private var listItems: List<String>? = null
    private var onItemClickListener: ((String) -> Unit)? = null

    private var dialog: AlertDialog? = null

    private var backgroundColor:Int? = Color.WHITE
    private var backgroundBorderColor: Int? = Color.GRAY
    private var backgroundBorderWidth: Int? = 2

    private var closeTouchOutSide: Boolean? = true

    fun setTitleStyle(title: String? = this.title, size: Float? = this.titleSize, color: Int? = this.titleColor) = apply {
        this.title = title
        this.titleSize = size
        this.titleColor = color
    }

    fun setDescription(description: String? = this.description, size: Float? = this.descriptionSize) = apply {
        this.description = description
        this.descriptionSize = size
    }

    fun setPositiveButton(text: String? = this.positiveButtonText, size: Float? = this.positiveButtonTextSize, color: Int? = this.positiveButtonColor, listener: () -> Unit) = apply{
        this.positiveButtonText = text
        this.positiveButtonTextSize = size
        this.positiveButtonColor = color
        this.positiveButtonClickListener = listener
    }

    fun setNegativeButton(text: String? = this.negativeButtonText, size: Float? = this.negativeButtonTextSize, color: Int? = this.negativeButtonColor, listener: () -> Unit) = apply{
        this.negativeButtonText = text
        this.negativeButtonTextSize = size
        this.negativeButtonColor = color
        this.negativeButtonClickListener = listener
    }

    fun setListItems(items: List<String>) = apply { this.listItems = items }

    fun seOnItemClickListener(listener: (String) -> Unit) = apply { this.onItemClickListener = listener }

    fun setBackgroundColor(colorBackground: Int? = this.backgroundColor, colorBorder: Int? = this.backgroundBorderColor, borderWidth: Int? = this.backgroundBorderWidth) = apply {
        this.backgroundColor = colorBackground
        this.backgroundBorderColor = colorBorder
        this.backgroundBorderWidth = borderWidth
    }

    fun isEasilyCloseAble(close: Boolean?= null) = apply { this.closeTouchOutSide = close }

    private fun setStyleBackground(binding: Any?) {
        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.dialog_background) as? GradientDrawable
        backgroundDrawable?.let { drawable ->
            backgroundColor?.let { drawable.setColor(it) }
            if (backgroundBorderColor != null && backgroundBorderWidth != null) {
                drawable.setStroke(backgroundBorderWidth!!, backgroundBorderColor!!)
            }
            when (binding) {
                is CustomListDialogBinding -> binding.root.background = drawable
                is CustomSimpleDialogBinding -> binding.root.background = drawable
            }
        }
    }

    private fun setTitleStyleBuild(binding: Any?){
        val defaultTitle: String = "Default Title"
        val defaultSizeTitle: Float = 8F

        when (binding) {
            is CustomListDialogBinding -> {
                binding.dialogTitle.text = title?: defaultTitle
                binding.dialogTitle.textSize = titleSize ?: defaultSizeTitle
                titleColor?.let {
                    binding.dialogTitle.setTextColor(it)
                }
            }
            is CustomSimpleDialogBinding -> {
                binding.dialogTitle.text = title?: defaultTitle
                binding.dialogTitle.textSize = titleSize ?: defaultSizeTitle
                titleColor?.let {
                    binding.dialogTitle.setTextColor(it)
                }
            }
        }
    }


    private fun setDescriptionStyle(binding: Any?) {
        val defaultDescription = "Default description, please set your own description"
        val defaultSize = 8F

        when (binding) {
            is CustomListDialogBinding -> {
                binding.dialogDescription.text = description ?: defaultDescription
                binding.dialogDescription.textSize = descriptionSize ?: defaultSize
            }
            is CustomSimpleDialogBinding -> {
                binding.dialogDescription.text = description ?: defaultDescription
                binding.dialogDescription.textSize = descriptionSize ?: defaultSize
            }
        }
    }

    private fun setPositiveButtonStyleActions(binding: Any?) {
        val defaultTextYes: String = "Yes"
        val defaultSize: Float = 10F
        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.rounded_button_positive) as? GradientDrawable

        backgroundDrawable?.let {
            positiveButtonColor?.let { backgroundDrawable.setColor(it) }
        }

        when(binding) {
            is CustomSimpleDialogBinding -> {
                binding.dialogButtonYes.text = positiveButtonText ?: defaultTextYes
                binding.dialogButtonYes.textSize = positiveButtonTextSize ?: defaultSize
                binding.dialogButtonYes.setOnClickListener {
                    positiveButtonClickListener?.invoke()
                }
                binding.dialogButtonYes.background = backgroundDrawable
            }
            is CustomListDialogBinding -> {
                binding.dialogButtonYes.text = positiveButtonText ?: defaultTextYes
                binding.dialogButtonYes.textSize = positiveButtonTextSize ?: defaultSize
                binding.dialogButtonYes.setOnClickListener {
                    positiveButtonClickListener?.invoke()
                }
                binding.dialogButtonYes.background = backgroundDrawable
            }
        }
    }

    private fun setNegativeButtonStyleActions(binding: Any?) {
        val defaultTextNo: String = "No"
        val defaultSize: Float = 10F
        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.rounded_button_negative) as? GradientDrawable

        backgroundDrawable?.let {
            negativeButtonColor?.let { backgroundDrawable.setColor(it) }
        }

        when(binding) {
            is CustomSimpleDialogBinding -> {
                binding.dialogButtonNo.text = negativeButtonText ?: defaultTextNo
                binding.dialogButtonNo.textSize = negativeButtonTextSize ?: defaultSize
                binding.dialogButtonNo.setOnClickListener {
                    negativeButtonClickListener?.invoke()
                }
                binding.dialogButtonNo.background = backgroundDrawable
            }
            is CustomListDialogBinding -> {
                binding.dialogButtonNo.text = negativeButtonText ?: defaultTextNo
                binding.dialogButtonNo.textSize = negativeButtonTextSize ?: defaultSize
                binding.dialogButtonNo.setOnClickListener {
                    negativeButtonClickListener?.invoke()
                }
                binding.dialogButtonNo.background = backgroundDrawable
            }
        }
    }

    fun build() {
//        if (listItems.isNullOrEmpty()) {
//            buildSimpleDialog()
//        } else {
//            buildListDialog()
//        }
        buildDialog()
    }

//    private fun buildSimpleDialog() {
//        val binding = CustomSimpleDialogBinding.inflate(LayoutInflater.from(context))
//        setTitleStyleBuild(binding)
//        setStyleBackground(binding)
//        setDescriptionStyle(binding)
//
////        binding.dialogButtonYes.text = positiveButtonText ?: "Aceptar"
////        binding.dialogButtonYes.setOnClickListener {
////            positiveButtonClickListener?.invoke()
////        }
//
//        setPositiveButtonStyleActions(binding)
//
//        dialog = AlertDialog.Builder(context)
//            .setView(binding.root)
//            .create()
//
//        dialog?.setCanceledOnTouchOutside(false)
//        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        dialog?.show()
//    }



    private fun buildDialog() {
        val binding = CustomListDialogBinding.inflate(LayoutInflater.from(context))

        setTitleStyleBuild(binding)
        setStyleBackground(binding)
        setDescriptionStyle(binding)
        setPositiveButtonStyleActions(binding)

        if (listItems != null) {
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
                selectedItem?.let {
                    onItemClickListener?.invoke(it)
                }
            }
        } else {
            binding.dialogList.visibility = View.GONE
        }

        if (negativeButtonText != null) {
            setNegativeButtonStyleActions(binding)
        } else {
            binding.dialogButtonNo.visibility = View.GONE
        }


        dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        dialog?.setCanceledOnTouchOutside(closeTouchOutSide ?: false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()
    }

    fun dismissDialog(){
        dialog?.dismiss()
    }
}
