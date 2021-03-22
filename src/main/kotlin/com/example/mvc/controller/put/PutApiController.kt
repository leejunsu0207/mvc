package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String{
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMappint(): String {
        return "request-mapping - put method"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingRequest(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String> {

        if(bindingResult.hasErrors()){
            // 500 error
                val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append(field.field + " : " + message + "\n")
//                msg.append("$field : $message\n")
            }

            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")
  /*      // 0. Response
       return UserResponse().apply {

            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }

        }.apply {
            // 2. descrition
            this.description = "!~~~~~~~~~~~~~~~~~!"

        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "test@gmail.com"
                this.address ="a address"
                this.phoneNumber = "01000000000"
            })
            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 11
                this.email = "test@naver.com"
                this.address ="b address"
                this.phoneNumber = "01011111111"
            })

            this.userRequest = userList

        }*/
    }
}