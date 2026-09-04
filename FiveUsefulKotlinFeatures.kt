/**
 * 5 Useful Kotlin Features for Android Development
 *
 * These features can help make Android code cleaner,
 * shorter, and easier to maintain.
 */


// ============================================================
// 1. Extension Functions
// ============================================================

/*
 * Extension Functions allow us to add new functions to an
 * existing class without modifying the original class.
 *
 * This is very useful in Android for creating reusable
 * utility functions.
 */

fun String.toSlug(): String {
    return this
        .trim()
        .lowercase()
        .replace(" ", "-")
}

// Example:
// val title = "Hello Kotlin World"
// println(title.toSlug())
//
// Output:
// hello-kotlin-world



// ============================================================
// 2. Sealed Classes
// ============================================================

/*
 * Sealed Classes are useful when a value can have a limited
 * number of known states.
 *
 * A common Android example is representing the state of a
 * screen or an API request:
 *
 * Loading → Success → Error
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
//
// fun handleState(state: UiState) {
//     when (state) {
//
//         UiState.Loading -> {
//             println("Loading...")
//         }
//
//         is UiState.Success -> {
//             println(state.data)
//         }
//
//         is UiState.Error -> {
//             println(state.message)
//         }
//     }
// }



// ============================================================
// 3. Scope Functions
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
 * They help us write code that works with an object
 * in a cleaner and more readable way.
 *
 * "apply" is especially useful when creating and
 * configuring an object.
 */

data class User(
    var name: String = "",
    var age: Int = 0
)

val user = User().apply {

    name = "Matin"
    age = 21
}

// Instead of:
//
// val user = User()
// user.name = "Matin"
// user.age = 21
//
// We can configure the object using "apply".


// Some other common examples:
//
// user?.let {
//     println(it.name)
// }
//
// user.run {
//     println(name)
// }
//
// user.also {
//     println("User created: $it")
// }



// ============================================================
// 4. Delegated Properties
// ============================================================

/*
 * Delegated Properties allow us to delegate the logic of
 * getting or setting a property to another object.
 *
 * "lazy" is one of the simplest examples.
 *
 * With lazy, the value is calculated only when it is
 * actually needed for the first time.
 */

val expensiveData: String by lazy {

    println("Calculating...")

    "Data loaded"
}


// The code inside "lazy" is NOT executed immediately.
//
// It runs when we first access:
//
// println(expensiveData)
//
// After the first access, the calculated value is reused.
//
// Delegated properties are also used extensively by
// Kotlin and Android libraries.



// ============================================================
// 5. Inline + Reified
// ============================================================

/*
 * "reified" allows us to access the actual generic type
 * inside an inline function.
 *
 * This can be useful when creating generic utility
 * functions where we need to know the type at runtime.
 */

inline fun <reified T> isType(value: Any): Boolean {

    return value is T
}


// Example:
//
// println(isType<String>("Kotlin"))
// Output: true
//
// println(isType<Int>("Kotlin"))
// Output: false


/*
 * Normally, generic type information is erased at runtime.
 *
 * Using "reified" with "inline" allows Kotlin to keep
 * access to the actual type inside this function.
 */



// ============================================================
// Summary
// ============================================================

/*
 *
 * 1. Extension Functions
 *    → Add useful functions to existing classes.
 *
 * 2. Sealed Classes
 *    → Represent a limited set of states safely.
 *
 * 3. Scope Functions
 *    → Make object-related code cleaner.
 *
 * 4. Delegated Properties
 *    → Delegate property behavior to another object.
 *
 * 5. Inline + Reified
 *    → Create powerful generic utility functions.
 *
 */