package com.learner.cleanarchitecture.presentation.ui.bs

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.learner.cleanarchitecture.R
import com.learner.cleanarchitecture.databinding.BsCoinDetailsBinding
import com.learner.cleanarchitecture.databinding.ItemTagBinding
import com.learner.cleanarchitecture.databinding.ItemTeamMemberBinding
import com.learner.cleanarchitecture.domain.model.CoinDetail
import com.learner.cleanarchitecture.utils.MyGlobalAdapter


class CoinDetailsDialog(private val coinDetail: CoinDetail) :
    BottomSheetDialogFragment(R.layout.bs_coin_details) {
    lateinit var binding: BsCoinDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = BsCoinDetailsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        coinDetail.apply {
            binding.txtNameSymb.text = "$name ($symbol)"
            binding.txtId.text = id
            binding.txtDescription.text = desc
            binding.txtIsActive.text = if (isActive) "active" else "inActive"
            binding.rvTagList.adapter =
                object : MyGlobalAdapter<ItemTagBinding>(ItemTagBinding::inflate, tags.size) {
                    override fun bind(binder: ItemTagBinding, position: Int) {
                        binder.txtTagName.text = tags[position]
                    }
                }
            binding.rvTeamList.adapter = object :
                MyGlobalAdapter<ItemTeamMemberBinding>(ItemTeamMemberBinding::inflate, team.size) {
                override fun bind(binder: ItemTeamMemberBinding, position: Int) {
                    binder.txtName.text = "${team[position].id}. ${team[position].name}"
                    binder.txtPosition.text = team[position].position
                }
            }
        }
    }


    //Full screen
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { showedDialog: DialogInterface ->
            (showedDialog as BottomSheetDialog).let { bs ->
                bs.behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
                bs.behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            }
        }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }
}