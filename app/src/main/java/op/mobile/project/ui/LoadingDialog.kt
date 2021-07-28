/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui

import android.app.Activity
import android.app.AlertDialog
import op.mobile.project.R

class LoadingDialog(private val mActivity: Activity) {

    private lateinit var isDialog: AlertDialog

    fun startLoading() {
        // set view
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)

        // set Dialog
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }

    fun isDismiss() {
        isDialog.dismiss()
    }
}