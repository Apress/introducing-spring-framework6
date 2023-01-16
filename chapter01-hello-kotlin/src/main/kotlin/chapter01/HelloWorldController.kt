package chapter01

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@EnableAutoConfiguration
class HelloWorldController {
  @get:ResponseBody
  @get:GetMapping(path = ["/"], produces = ["text/plain"])
  val message: String
    get() = "Hello, Kotlin!"

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      SpringApplication.run(HelloWorldController::class.java, *args)
    }
  }
}
