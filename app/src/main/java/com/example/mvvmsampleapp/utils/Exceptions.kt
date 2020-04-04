package com.example.mvvmsampleapp.utils

import java.io.IOException

class ApiExceptions(message : String) : IOException(message){

}
class NoInternetAvailable(message : String) : IOException(message){

}