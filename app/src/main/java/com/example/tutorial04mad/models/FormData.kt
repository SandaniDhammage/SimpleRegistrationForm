package com.example.tutorial04mad.models

import com.example.tutorial04mad.models.validations.ValidationResults

class FormData(private var studentID:String,private var year:String,private var semester:String, private var agree:Boolean) {

    fun validateStudentID():ValidationResults{
        return if(studentID.isEmpty()){
            ValidationResults.Empty("StudentID is empty")
        }
        else if(!studentID.startsWith("IT")){
            ValidationResults.Invalid("Should starts with IT")
        }
        else if(studentID.length<10){
            ValidationResults.Invalid("StudentID should be 10")
        }
        else if(studentID.length>10){
            ValidationResults.Invalid("StudentID should be 10")
        }
        else{
            ValidationResults.Valid
        }
    }

    fun validateYear():ValidationResults{
        return if(year.isEmpty()){
            ValidationResults.Empty("Year is empty")
        }
        else{
            ValidationResults.Valid
        }
    }

    fun validateSemester():ValidationResults{
        return if(semester.isEmpty()){
            ValidationResults.Empty("Semester is empty")
        }
        else{
            ValidationResults.Valid
        }
    }

    fun validateAgree():ValidationResults{
        return if(!agree){
            ValidationResults.Invalid("You must agree to the terms and conditions")
        }
        else{
            ValidationResults.Valid
        }
    }
}