package com.example.tutorial04mad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.tutorial04mad.models.FormData
import com.example.tutorial04mad.models.validations.ValidationResults
import java.time.Year

class MainActivity : AppCompatActivity() {
    lateinit var edtStudentId:EditText
    lateinit var spnYear: Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox
    lateinit var tvYear: TextView
    lateinit var tvSemester:TextView
    private var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtStudentId=findViewById(R.id.edtStudentId)
        spnYear=findViewById(R.id.spnYear)
        spnSemester=findViewById(R.id.spnSemester)
        cbAgree=findViewById(R.id.cbAgree)
        tvYear=findViewById(R.id.tvYear)
        tvSemester=findViewById(R.id.tvSemester)
    }
    fun displayAlert(title:String,message:String){
        val builder=AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("ok"){dialog,which->}
        val dialog=builder.create()
        dialog.show()
    }
    fun submit(V: View){
        val myForm= FormData(
            edtStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
        )
        val studentValidation=myForm.validateStudentID()
        val spnYearValidation=myForm.validateYear()
        val spnSemesterValidation=myForm.validateSemester()
        val cbAgreeValidation=myForm.validateAgree()

        when(studentValidation){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                edtStudentId.error=studentValidation.errorMessage
            }
            is ValidationResults.Empty->{
                edtStudentId.error=studentValidation.errorMessage
            }
        }

        when(spnYearValidation){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                val tv:TextView=spnYear.selectedItem as TextView
                tv.error=""
                tv.text=spnYearValidation.errorMessage
            }
            is ValidationResults.Empty->{
                val tv:TextView=spnYear.selectedItem as TextView
                tv.error=""
                tv.text=spnYearValidation.errorMessage
            }
        }

        when(spnSemesterValidation){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                val tv:TextView=spnSemester.selectedItem as TextView
                tv.error=""
                tv.text=spnSemesterValidation.errorMessage
            }
            is ValidationResults.Empty->{
                val tv:TextView=spnSemester.selectedItem as TextView
                tv.error=""
                tv.text=spnSemesterValidation.errorMessage
            }
        }

        when (cbAgreeValidation){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                displayAlert("Error",cbAgreeValidation.errorMessage)
            }
            is ValidationResults.Empty->{

            }
        }
        if(count==4){
            displayAlert("success","You have successfully registered")
        }
    }
}