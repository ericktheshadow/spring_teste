package br.com.erick.forum.exception

import br.com.erick.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)//NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: MethodArgumentNotValidException,//NotFoundException,
                       request:HttpServletRequest
    ): ErrorView {
        val errorMessage = HashMap<String,String?>()
        exception.bindingResult.fieldErrors.forEach{
            it -> errorMessage.put(it.field,it.defaultMessage)
        }
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),//INTERNAL_SERVER_ERROR.value()
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath

        )
    }
}