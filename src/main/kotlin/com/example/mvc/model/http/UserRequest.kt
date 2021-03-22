package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

data class UserRequest (

    @field:Size(min = 2, max = 5)
    @field:NotEmpty
    var name:String?=null,

    @field:PositiveOrZero // 0 < num 숫자를 검증 0도 포함(양수)
    var age:Int?=null,

    @field:Email
    var email:String?=null,

    @field:NotBlank
    var address:String?=null,

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") // 정규식 검증
    var phoneNumber:String?=null,    // phone_number

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createdAt:String?=null  // yyyy-MM-dd HH:mm:ss

){
  /*  @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
    private fun isValidCreatedAt():Boolean{ // 정상 true, 비정상 false
        return try {
            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e:Exception){
            false

        }
    }*/
}


/*

{
    "result" :{
    "result_code" : "OK",
    "resut_message" : "성공"
    },
    "description" : "~~~~~~",
    "user" : [
        {
            "name" : "",
            "age" : "",
            "email": ""
            "phoneNumber" :""
        },
        {
            "name" : "",
            "age" : "",
            "email": ""
            "phoneNumber" :""
        },
        {
            "name" : "",
            "age" : "",
            "email": ""
            "phoneNumber" :""
        }
    ]
}

*/
