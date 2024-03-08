package com.astrick.compose.annotations

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Annotation classes basically allow us to define custom metadata that can be attached
 * to various elements in out code and retrieved at runtime using reflection.
 *
 * annotation class MyAnnotation(val name: String)
 *
 * @MyAnnotation("ExampleClass")
 * class ExampleClass {
 *     @MyAnnotation("exampleFunction")
 *     fun exampleFunction() {
 *         // Function implementation
 *     }
 * }
 *
 * fun main() {
 *     val classAnnotation = ExampleClass::class.java.getAnnotation(MyAnnotation::class.java)
 *     val functionAnnotation = ExampleClass::class.java.getDeclaredMethod("exampleFunction")
 *             .getAnnotation(MyAnnotation::class.java)
 *
 *     println("Class annotation: ${classAnnotation?.name}")
 *     println("Function annotation: ${functionAnnotation?.name}")
 * }
 */

/**
 * AnnotationRetention: tells us how long this annotation sticks around.
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
/**
 * AnnotationTarget.ANNOTATION_CLASS: can only be applied to other annotation classes / extendable
 * or it can be applied to:
 * AnnotationTarget.FUNCTION: apply to composables etc
 */
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview(name = "Blank", device = Devices.DEFAULT, showBackground = true)
@Preview(name = "Blank_Font_140%", device = Devices.DEFAULT, showSystemUi = true, fontScale = 1.4f)
@Preview(name = "Small", device = Devices.NEXUS_5, showSystemUi = true)
@Preview(name = "Small_Font_140%", device = Devices.NEXUS_5, showSystemUi = true, fontScale = 1.4f)
@Preview(name = "Tablet", device = Devices.TABLET, showSystemUi = true)
@Preview(name = "Tablet_Font_140%", device = Devices.TABLET, showSystemUi = true, fontScale = 1.4f)
annotation class SandboxPreviews

// AnnotationTarget.ANNOTATION_CLASS: allows us to do this:
@SandboxPreviews
@Preview(name = "Tablet_Font_160%", device = Devices.TABLET, showSystemUi = true, fontScale = 1.4f)
annotation class MorePreviews
