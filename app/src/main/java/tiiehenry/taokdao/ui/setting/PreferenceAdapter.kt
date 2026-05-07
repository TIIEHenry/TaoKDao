package tiiehenry.taokdao.ui.setting

import android.view.View
import taokdao.api.main.IMainContext
import taokdao.api.setting.preference.*
import taokdao.api.setting.preference.base.IPreference
import taokdao.api.setting.preference.base.PreferenceType.*
import taokdao.api.setting.preference.wrapped.ClickablePreference
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class PreferenceAdapter(val main: IMainContext, dataList: MutableList<IPreference<*>>, val showCategory: (MutableList<IPreference<*>>) -> Unit) : BaseIdRecyclerAdapter<IPreference<*>>(dataList) {
    init {
        setOnItemClickListener { itemView, item, _ ->
            when (item) {
                is IViewPreference -> showView(item)
                is ISwitchPreference -> onToggle(item)
                is ICheckboxPreference -> onToggle(item)
                is ITextPreference -> {
                    onChange(item)
                }
                is IEditTextPreference -> {
                    showEditText(item)
                }
                is ISingleChoicePreference -> {
                    showSingleChoice(item)
                }
                is IMultiChoicePreference -> {
                    showMultiChoice(item)
                }
                is ICategoryPreference -> {
                    showCategory(item.numberList)
                }
                is ITitlePreference -> {

                }
                is IClickablePreference -> {
                    item.onClick(itemView)
                }
                else -> {
                }
            }
        }
        setOnItemLongClickListener { itemView, item, position ->
            when (item) {
                is IClickablePreference -> {
                    item.onLongClick(itemView)
                }
                else -> {
                }
            }
        }
    }

    private fun showMultiChoice(item: IMultiChoicePreference) {
        val value = item.loadValue()
        val list = mutableListOf<Int>()
        item.itemList.forEachIndexed { index, s ->
            if (value.contains(s)) {
                list.add(index)
            }
        }
        Dialogs.global
                .asList()
                .typeMultiChoice()
                .apply {
                    item.icon?.let { icon(it) }
                }
                .title(item.title)
                .items(*item.itemList)
                .itemsCallbackMultiChoice(list.toIntArray()) { dialog, indices, items ->
                    val map = mutableMapOf<Int, String>()
                    for (i in indices.indices) {
                        map[i] = items[i].toString()
                    }
                    item.onChosen(map)
                    dialog.dismiss()
                    true
                }
                .positiveText()
                .dismissListener {
                    notifyDataSetChanged()
                }
                .show()
    }

    private fun showSingleChoice(item: ISingleChoicePreference) {
        Dialogs.global
                .asList()
                .typeSingleChoice()
                .apply {
                    item.icon?.let { icon(it) }
                }
                .title(item.title)
                .items(*item.itemList)
                .itemsCallbackSingleChoice(item.loadValue()) { dialog, which, text ->
                    item.onChosen(which, text.toString())
                    dialog.dismiss()
                    true
                }
                .negativeText()
                .positiveText()
                .dismissListener {
                    notifyDataSetChanged()
                }
                .show()
    }

    private fun onChange(item: ITextPreference) {
        item.onChanged(item.loadValue())
        notifyDataSetChanged()
    }

    private fun showEditText(item: IEditTextPreference) {
        Dialogs.global
                .asInput()
                .apply {
                    item.icon?.let { icon(it) }
                }
                .title(item.title)
                .input(item.loadValue(), item.loadValue()) { dialog, input ->
                    item.onChanged(input.toString())
                    dialog.dismiss()
                }
                .negativeText()
                .positiveText()
                .dismissListener {
                    notifyDataSetChanged()
                }
                .show()
    }

    private fun showView(item: IViewPreference) {
        val view = item.onCreateView(main)
        Dialogs.global
                .asCustom()
                .apply {
                    item.icon?.let { icon(it) }
                }
                .title(item.title)
                .customView(view, true)
                .negativeText()
                .positiveText()
                .dismissListener {
                    item.onDestroyView(view)
                    notifyDataSetChanged()
                }
                .show()
    }

    private fun onToggle(item: ISwitchPreference) {
        item.onToggle(!item.loadValue())
        notifyDataSetChanged()
    }

    private fun onToggle(item: ICheckboxPreference) {
        item.onToggle(!item.loadValue())
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return getData(position).preferenceType.id
    }

    override fun getItemLayoutId(viewType: Int): Int {
        return when (viewType) {
            TYPE_VIEW.id -> R.layout.setting_preference_item_view
            TYPE_SWITCH.id -> R.layout.setting_preference_item_switch
            TYPE_CHECKBOX.id -> R.layout.setting_preference_item_checkbox
            TYPE_TEXT.id -> R.layout.setting_preference_item_text
            TYPE_EDIT_TEXT.id -> R.layout.setting_preference_item_edittext
            TYPE_CHOICE_SINGLE.id -> R.layout.setting_preference_item_choice
            TYPE_CHOICE_MULTI.id -> R.layout.setting_preference_item_choice
            TYPE_CATEGORY.id -> R.layout.setting_preference_item_category
            TYPE_TITLE.id -> R.layout.setting_preference_item_title
            TYPE_CLICKABLE.id -> R.layout.setting_preference_item_clickable
            else -> R.layout.setting_preference_item_title
        }
    }

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: IPreference<*>) {
        holder.getImageView(R.id.iv_icon).let {
            if (item.icon == null) {
                it.visibility = View.GONE
            } else {
                it.visibility = View.VISIBLE
                it.setImageDrawable(item.icon)
            }
        }
        holder.getTextView(R.id.tv_title).text = item.title
        if (item !is ITitlePreference) {
            holder.getTextView(R.id.tv_description).let {
                if (item.description == null) {
                    it.visibility = View.GONE
                } else {
                    it.visibility = View.VISIBLE
                    it.text = item.description
                }
            }
        }
        if (item is ITitlePreference && !item.isEnabled) {
//            for (iPreference in item.numberList) {
////                iPreference.isEnabled=item.isEnabled
////            }
        } else {
            holder.itemView.isEnabled = item.isEnabled
        }
        when (item) {
            is IViewPreference -> {

            }
            is ISwitchPreference -> {
                holder.image(R.id.iv_switch, if (item.loadValue()) R.drawable.setting_preference_on else R.drawable.setting_preference_off)
            }
            is ICheckboxPreference -> {
                holder.image(R.id.iv_checkbox, if (item.loadValue()) R.drawable.setting_preference_checkedon else R.drawable.setting_preference_checkedoff)
            }
            is ITextPreference -> {
                holder.text(R.id.tv_text, item.loadValue())
            }
            is IEditTextPreference -> {
                holder.text(R.id.tv_text, item.loadValue())
            }
            is ISingleChoicePreference -> {
            }
            is IMultiChoicePreference -> {
            }
            is ICategoryPreference -> {

            }
            is ITitlePreference -> {

            }
            is ClickablePreference -> {

            }
            else -> {
            }
        }
    }
}