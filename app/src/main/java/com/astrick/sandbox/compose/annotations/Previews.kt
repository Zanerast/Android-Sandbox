package com.astrick.sandbox.compose.annotations

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/*
 * Annotations allow us to define custom metadata that can be attached to various elements in our code.
 * Which can be retrieved at runtime via reflection or be used for other purposes
 * such as code generation or tool processing.
 *
 * annotation class MyAnnotation(val name: String)
 *
 * @MyAnnotation("ExampleClass")
 * class SomeClass {
 *     @MyAnnotation("exampleFunction")
 *     fun someFunction() {
 *         // Function implementation
 *     }
 * }
 *
 * fun main() {
 *     val classAnnotation = SomeClass::class.java.getAnnotation(MyAnnotation::class.java)
 *     val functionAnnotation = SomeClass::class.java.getDeclaredMethod("someFunction")
 *             .getAnnotation(MyAnnotation::class.java)
 *
 *     println("Class annotation: ${classAnnotation?.name}") // prints: Class annotation: ExampleClass
 *     println("Function annotation: ${functionAnnotation?.name}") // prints: Function annotation: exampleFunction
 * }
 */

/*
 * AnnotationRetention: tells us how long this annotation sticks around.
 *
 * AnnotationRetention.BINARY: retained in the compiled binary output of the code.
 *      (e.g., a .jar file in Java or .class files in Kotlin)
 *      Sticks around after the code is compiled, which allows tools/frameworks (like dependency
 *      injection frameworks or runtime reflection) to access and use this annotation.
 * AnnotationRetention.SOURCE: only available during the source code compilation phase.
 *       Typically used by tools or processors that generate additional code based on the
 *       source code, like code generators or documentation tools.
 * AnnotationRetention.RUNTIME: available during runtime when the program is executed.
 *       Allows the program to inspect its own structure and behavior.
 *       Can be used for: dependency injection, data validation, or custom runtime behaviors etc
 */
@Retention(AnnotationRetention.BINARY)
/*
 * AnnotationTarget.ANNOTATION_CLASS: can only be applied to other annotation classes / extendable
 * or it can be applied to:
 * AnnotationTarget.FUNCTION: apply to composables etc
 */
@Target(
    AnnotationTarget.ANNOTATION_CLASS, // Allows the annotation to target other annotations
    AnnotationTarget.FUNCTION // Allows the annotation to target functions (e.g., composables)
)
@Preview(name = "Blank", device = Devices.DEFAULT, showBackground = true)
@Preview(name = "Blank_Font_140%", device = Devices.DEFAULT, showSystemUi = true, fontScale = 1.4f)
@Preview(name = "Small", device = Devices.NEXUS_5, showSystemUi = true)
@Preview(name = "Small_Font_140%", device = Devices.NEXUS_5, showSystemUi = true, fontScale = 1.4f)
@Preview(name = "Tablet", device = Devices.PIXEL_TABLET, showSystemUi = true)
annotation class SandboxPreviews

// Example of inheriting from SandboxPreviews annotation to combine with additional @Preview annotations:
@SandboxPreviews
@Preview(name = "Tablet_Font_200%", device = Devices.PIXEL_TABLET, showSystemUi = true, fontScale = 2.0f)
annotation class MorePreviews
