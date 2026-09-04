/**
 * Kotlin Features for Android Development
 *
 * A collection of useful Kotlin features that can make
 * Android development cleaner, shorter and easier to maintain.
 *
 * Features covered:
 *
 * 1.  Null Safety
 * 2.  Extension Functions
 * 3.  Data Classes
 * 4.  Coroutines
 * 5.  Smart Casts & Type Inference
 * 6.  Higher-Order Functions & Lambdas
 * 7.  Sealed Classes
 * 8.  Scope Functions
 * 9.  Named & Default Arguments
 * 10. Destructuring Declarations
 * 11. Delegated Properties
 * 12. Operator Overloading
 */


// ============================================================
// 1. Null Safety
// ============================================================

/*
 * Kotlin's type system helps us handle nullable values safely.
 *
 * A variable that can contain null must explicitly use "?".
 */

var username: String? = null

// Safe call operator "?."
val length = username?.length

// Elvis operator "?:" lets us provide a default value.
val name = username ?: "Guest"

println(name)
// Output:
// Guest


/*
 * Instead of manually checking for null:
//
// if (username != null) {
//     println(username.length)
// }
//
// We can safely write:
//
// println(username?.length)
// */



// ============================================================
// 2. Extension Functions
// ============================================================

/*
 * Extension Functions allow us to add a new function to an
 * existing class without modifying that class.
 *
 * This is very useful for creating reusable utility functions
 * in Android projects.
 */

fun String.toSlug(): String {
    return trim()
        .lowercase()
        .replace(" ", "-")
}


// Example:

val title = "Hello Kotlin World"

println(title.toSlug())

// Output:
// hello-kotlin-world



// ============================================================
// 3. Data Classes
// ============================================================

/*
 * Data classes are designed to hold data.
 *
 * Kotlin automatically generates useful functions such as:
 *
 * - equals()
 * - hashCode()
 * - toString()
 * - copy()
 */

data class User(
    val name: String,
    val age: Int
)

val user1 = User("Matin", 21)

println(user1)

// Example of copy():

val user2 = user1.copy(age = 22)

println(user2)

// Output:
// User(name=Matin, age=22)



// ============================================================
// 4. Coroutines
// ============================================================

/*
 * Coroutines make asynchronous programming easier.
 *
 * They are commonly used in Android for:
 *
 * - Network requests
 * - Database operations
 * - Background work
 *
 * Example:
 *
 * coroutineScope {
 *     launch {
 *         val result = loadData()
 *         println(result)
 *     }
 * }
 *
 * Coroutines allow us to write asynchronous code in a
 * sequential-looking style.
 *
 * Note:
 * The following example requires kotlinx-coroutines.
 */

// import kotlinx.coroutines.*

/*
 * suspend fun loadData(): String {
 *     delay(1000)
 *     return "Data loaded"
 * }
 *
 * suspend fun example() {
 *
 *     coroutineScope {
 *
 *         launch {
 *             val result = loadData()
 *             println(result)
 *         }
 *     }
 * }
 */



// ============================================================
// 5. Smart Casts & Type Inference
// ============================================================

/*
 * Kotlin can often determine the type of a variable
 * automatically.
 *
 * This is called Type Inference.
 */

val age = 21

// Kotlin knows that "age" is an Int.
// We don't need to write:
//
// val age: Int = 21


/*
 * Smart Casts allow Kotlin to automatically treat a variable
 * as a more specific type after checking its type.
 */

fun printLength(value: Any) {

    if (value is String) {

        // Kotlin automatically knows that "value" is String here.

        println(value.length)
    }
}


// Example:

printLength("Kotlin")

// Output:
// 6



// ============================================================
// 6. Higher-Order Functions & Lambdas
// ============================================================

/*
 * A Lambda is a function without a name.
 *
 * A Higher-Order Function is a function that accepts another
 * function as a parameter or returns a function.
 */

// Lambda:

val sayHello = { name: String ->
    println("Hello $name")
}

sayHello("Matin")


// Higher-Order Function:

fun calculate(
    a: Int,
    b: Int,
    operation: (Int, Int) -> Int
): Int {

    return operation(a, b)
}


// We can pass different behaviors to the same function.

val sum = calculate(10, 5) { a, b ->
    a + b
}

val multiplication = calculate(10, 5) { a, b ->
    a * b
}

println(sum)
// 15

println(multiplication)
// 50


/*
 * Android example:
 *
 * button.setOnClickListener {
 *     println("Button clicked")
 * }
 *
 * The code inside { } is a Lambda.
 */



// ============================================================
// 7. Sealed Classes
// ============================================================

/*
 * Sealed classes are useful when a value can have a limited
 * number of known states.
 *
 * A common Android example is representing UI or API states.
 */

sealed class UiState {

    data object Loading : UiState()

    data class Success(
        val data: String
    ) : UiState()

    data class Error(
        val message: String
    ) : UiState()
}


// Example:

fun handleState(state: UiState) {

    when (state) {

        UiState.Loading -> {
            println("Loading...")
        }

        is UiState.Success -> {
            println("Data: ${state.data}")
        }

        is UiState.Error -> {
            println("Error: ${state.message}")
        }
    }
}


handleState(UiState.Loading)

handleState(
    UiState.Success("User loaded")
)

handleState(
    UiState.Error("Network error")
)



// ============================================================
// 8. Scope Functions
// ============================================================

/*
 * Kotlin provides five Scope Functions:
 *
 * let
 * run
 * with
 * apply
 * also
 *
 * They allow us to work with an object in a cleaner way.
 *
 * "apply" is especially useful when configuring objects.
 */

data class Product(
    var name: String = "",
    var price: Double = 0.0
)

val product = Product().apply {

    name = "Laptop"
    price = 1000.0
}

println(product)


/*
 * "let" is commonly used when working with nullable values.
 */

val email: String? = "matin@example.com"

email?.let {

    println("Email: $it")
}


/*
 * "also" is useful when we want to perform an additional
 * action while keeping the original object.
 */

val newProduct = Product().also {

    println("Creating product...")
}



/*
 * The important point is not just using Scope Functions
 * everywhere, but choosing the appropriate one for the job.
 */



// ============================================================
// 9. Named & Default Arguments
// ============================================================

/*
 * Kotlin allows us to define default values for parameters.
 *
 * This can reduce the need for multiple overloaded functions.
 */

fun createUser(
    name: String,
    age: Int = 18,
    isAdmin: Boolean = false
) {

    println(
        "Name: $name, Age: $age, Admin: $isAdmin"
    )
}


// We can use the default values:

createUser(
    name = "Matin"
)


// Or specify only the parameters we need:

createUser(
    name = "Matin",
    age = 21,
    isAdmin = true
)


/*
 * Named arguments also make function calls easier to read.
 */



// ============================================================
// 10. Destructuring Declarations
// ============================================================

/*
 * Destructuring allows us to extract multiple values
 * from an object into separate variables.
 */

data class Coordinate(
    val x: Int,
    val y: Int
)

val point = Coordinate(10, 20)

val (x, y) = point

println(x)
// 10

println(y)
// 20


/*
 * This can be especially convenient when working with
 * data classes, Pair and Triple.
 */

val userInfo = Triple(
    "Matin",
    21,
    "Android Developer"
)

val (userName, userAge, job) = userInfo

println(userName)
println(userAge)
println(job)



// ============================================================
// 11. Delegated Properties
// ============================================================

/*
 * Delegated Properties allow another object to handle
 * the logic of a property's value.
 *
 * "lazy" is one of the simplest examples.
 *
 * The value is calculated only when it is accessed
 * for the first time.
 */

val expensiveData: String by lazy {

    println("Calculating data...")

    "Data loaded"
}


// The code above is not executed yet.
//
// It executes when we first access:

println(expensiveData)


// Accessing it again uses the already calculated value:

println(expensiveData)


/*
 * In Android, delegated properties are also widely used
 * by Kotlin and Android libraries.
 */



// ============================================================
// 12. Operator Overloading
// ============================================================

/*
 * Kotlin allows us to define how operators such as "+"
 * behave with our own classes.
 */

data class Point(
    val x: Int,
    val y: Int
)

operator fun Point.plus(
    other: Point
): Point {

    return Point(
        x = this.x + other.x,
        y = this.y + other.y
    )
}


// Now we can use "+" with Point objects:

val point1 = Point(10, 20)

val point2 = Point(5, 15)

val result = point1 + point2

println(result)

// Output:
// Point(x=15, y=35)


/*
 * Operator overloading can make certain domain-specific
 * operations more natural to read.
 *
 * However, it should be used carefully.
 * Overusing custom operators can make code harder to understand.
 */



// ============================================================
// Summary
// ============================================================

/*
 *
 * 1. Null Safety
 *    -> Safer handling of nullable values.
 *
 * 2. Extension Functions
 *    -> Add functionality to existing classes.
 *
 * 3. Data Classes
 *    -> Create data-holding classes with less boilerplate.
 *
 * 4. Coroutines
 *    -> Make asynchronous programming easier.
 *
 * 5. Smart Casts & Type Inference
 *    -> Reduce unnecessary type declarations and casts.
 *
 * 6. Higher-Order Functions & Lambdas
 *    -> Pass behavior/functions as values.
 *
 * 7. Sealed Classes
 *    -> Represent a limited number of states.
 *
 * 8. Scope Functions
 *    -> Make object-related code cleaner.
 *
 * 9. Named & Default Arguments
 *    -> Make function calls cleaner and reduce overloads.
 *
 * 10. Destructuring Declarations
 *     -> Extract multiple values easily.
 *
 * 11. Delegated Properties
 *     -> Delegate property behavior to another object.
 *
 * 12. Operator Overloading
 *     -> Define custom behavior for operators.
 *
 */